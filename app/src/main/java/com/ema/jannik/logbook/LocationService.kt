package com.ema.jannik.logbook

import android.annotation.SuppressLint
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.location.Location
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat
import com.ema.jannik.logbook.activity.RecordDriveActivity
import com.ema.jannik.logbook.helper.App
import com.ema.jannik.logbook.model.RecDriveRepository
import com.ema.jannik.logbook.model.database.Route
import com.google.android.gms.location.*
import java.sql.Date

class LocationService : Service() {


    private val TAG = this::class.java.name

    /**
     * every foreground task need an notification
     */
    private var notification: Notification = Notification()

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback

    private lateinit var locations: List<Location>

    private lateinit var timeStart: java.util.Date
    private lateinit var timeEnd: Date

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    /**
     * Called by the system when the service is first created.  Do not call this method directly.
     */
    override fun onCreate() {
        Log.i(TAG, "onCreate()")
        super.onCreate()
    }

    /**
     * Called after onCreate.
     * This function set an notification, start the service in foreground and request locations updates.
     */
    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand")

        Log.i(TAG, "Pending Intent")
        val notificationIntent = Intent(this, RecordDriveActivity::class.java)  //To click on the notification
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )   //flag was happend by update intent

        Log.i(TAG, "notification")
        notification = NotificationCompat.Builder(this, App.CHANNEL_FORGROUNDSERVICE_ID)    //set notification
            .setContentTitle("GPS")    //TODO in string.xml
            .setContentText("run location service")
            .setSmallIcon(R.drawable.ic_location)
            .setContentIntent(pendingIntent)
            .build()

        Log.i(TAG, "startForeground")
        startForeground(
            1,
            notification
        )    //start service as foreground service, without this the service is killed after 5 sec.


        Log.i(TAG, "buildLocationRequest()")
        buildLocationRequest()
        Log.i(TAG, "buildLocationCallback")
        buildLocationCallback()

        Log.i(TAG, "getFusedLocationProviderClient")
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        /*
        timeStart = Calendar.getInstance().time
        Toast.makeText(this, DateFormat.getDateTimeInstance()
            .format(Calendar.getInstance().time), Toast.LENGTH_LONG).show()
        */

        Log.i(TAG, "requestLocationUpdates")
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())

        Log.i(TAG, "onStartCommand finish")
        return START_REDELIVER_INTENT   //kann man auslager in OnCreate
    }

    /**
     * This function is called when the service is finished or stopped and create database entries for drive and route.
     */
    override fun onDestroy() {
        Log.i(TAG, "onDestroy()")
        super.onDestroy()   //TODO wieklich hier
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)

        //save in db
        val start = locations.first()
        val destination = locations.last()

        val repository = RecDriveRepository(application)

        /*
        val drive = Drive(  //TODO entfernung
            purpose = "",
            duration = Date(99),
            start_timestamp = Date(999),
            destination_timestamp = Date(1800),
            mileageStart = 99.9,
            mileageDestination = 99.9,
            category = 0,
            start = Stage(
                latitude = start.latitude,
                longitude = start.longitude,
                address = repository.getAddress(start)
            ),
            destination = Stage(
                latitude = destination.latitude,
                longitude = destination.longitude,
                address = repository.getAddress(destination)
            )
        )
        */

        Log.i(TAG, "insertDriveToDB")
        //repository.insert(drive)

        Log.i(TAG, "insertRouteToDB")
        var x: Int = 1

        locations.forEach { l: Location ->
            repository.insert(
                Route(
                    driveId = 1,//drive.id,
                    latitude = l.latitude,
                    longitude = l.longitude
                    //, counter = x
                )
            )
            x++
        }

        Log.i(TAG, "onDestroy finish")
    }

    /**
     * create LocationCallback, this handle what to do when the location change.
     * When the location change, add the location tto the list locations.
     */
    private fun buildLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                for (location: Location in p0!!.locations) {
                    Log.i(TAG, location.latitude.toString() + " / " + location.longitude.toString())
                    locations += location
                }
            }
        }
    }

    /**
     * Erstellt LocationRequest, diese gibt an in welchen Abständen und mit welcher Priorität die Position bestimmt wird.
     */
    private fun buildLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 3000
        locationRequest.smallestDisplacement = 10.0F
    }


//TODO stop request
}