package com.ema.jannik.logbook

import android.Manifest
import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.*
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.app.NotificationCompat
import com.ema.jannik.logbook.activity.RecordDriveActivity
import com.ema.jannik.logbook.helper.App
import com.ema.jannik.logbook.model.RecDriveRepository
import com.ema.jannik.logbook.model.database.Drive
import com.ema.jannik.logbook.model.database.Route
import com.ema.jannik.logbook.model.database.Stage
import com.google.android.gms.location.*
import java.lang.Exception
import java.sql.Date
import java.util.*

class LocationUpdateService : Service() {

    companion object {
        var isRunning: Boolean = false
    }

    init {
        isRunning = true
    }

    private val TAG = this::class.java.name

    /**
     * every foreground task need an notification
     */
    private var notification: Notification = Notification()

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback

    private var locations: MutableList<Location> = mutableListOf()

    private lateinit var timeStart: Calendar
    private lateinit var timeEnd: Calendar

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


        //TODO get time
        timeStart = Calendar.getInstance()

        if (ActivityCompat.checkSelfPermission( //TODO check SDK Level by appcombat?
                applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.i(TAG, "requestLocationUpdates")
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
        }

        Log.i(TAG, "onStartCommand finish")
        return START_NOT_STICKY//kann man auslager in OnCreate
    }

    /**
     * This function is called when the service is finished or stopped and create database entries for drive and route.
     * Remove the location updates and save the data in the db.
     */
    override fun onDestroy() {
        super.onDestroy()

        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        saveInDb()

        isRunning = false
    }

    /**
     * save location in db.
     */
    private fun saveInDb() {
        val repository = RecDriveRepository(application)

        val start = locations.first()
        val destination = locations.last()
        val distance = getDistance(locations)
        val timeEnd = Calendar.getInstance()
        val duration = Calendar.getInstance()
        duration.timeInMillis = timeEnd.timeInMillis - timeStart.timeInMillis   //TODO calc time
        var mileagestart: Int
        try {
            mileagestart = repository.getLastDrive().mileageDestination
        } catch (e: Exception) {    //lastDrive don't exist
            mileagestart = 0
        }

        val last = repository.getLastDrive()

        val drive = Drive(
            purpose = "",
            duration = duration,
            distance = distance,
            start_timestamp = timeStart,
            destination_timestamp = timeEnd,
            mileageStart = mileagestart,
            mileageDestination = mileagestart + distance,
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

        Log.i(TAG, "insertDriveToDB")
        val result = repository.insert(drive)

        Log.i(TAG, "insertRouteToDB")
        locations.forEach { l: Location ->
            repository.insert(
                Route(
                    driveId = result,
                    latitude = l.latitude,
                    longitude = l.longitude
                )
            )
        }
    }

    /**
     * calculate the distance betwwen the locations
     */
    private fun getDistance(locations: MutableList<Location>): Int{
        var distance = 0F
        for (l in locations.indices){
            if(l + 1 < locations.size)
                distance += locations[l].distanceTo(locations[l+1])
        }
        return distance.toInt()/1000 //meter to kilometer
    }

    /**
     * create LocationCallback, this handle what to do when the location change.
     * When the location change, add the location tto the list locations.
     */
    private fun buildLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                //super.onLocationResult(p0)  //todo das hier nötig
                for (location: Location in p0!!.locations) {
                    Log.i(TAG, location.latitude.toString() + " / " + location.longitude.toString())
                    Toast.makeText(
                        applicationContext,
                        location.latitude.toString() + " / " + location.longitude.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    locations.add(location)
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
    }
}