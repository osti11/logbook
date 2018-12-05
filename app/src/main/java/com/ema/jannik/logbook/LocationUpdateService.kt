package com.ema.jannik.logbook

import android.Manifest
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.nfc.Tag
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.ema.jannik.logbook.App.Companion.CHANNEL_FORGROUNDSERVICE_ID
import com.google.android.gms.location.*


class LocationUpdateService : Service() {


    private val TAG = "com.ema.jannik.logbook"

    private var notification: Notification = Notification()


    private lateinit var locations: List<Location>


    companion object {
        const val REQUEST_CODE = 1000   //to identify request
    }

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    /**
     * Called by the system when the service is first created.  Do not call this method directly.
     */
    override fun onCreate() {
        super.onCreate()

        Log.i(TAG, "onCreate")

        //--build notification--
        val notificationIntent = Intent(this, RecordDriveActivity::class.java)  //To click on the notification
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )   //flag was happend by update intent

        notification = NotificationCompat.Builder(this, CHANNEL_FORGROUNDSERVICE_ID)
            .setContentTitle("GPS")    //TODO in string.xml
            .setContentText("run location service")
            .setSmallIcon(R.drawable.ic_location)
            .setContentIntent(pendingIntent)
            .build()

        Log.i(TAG, "onCreate Location")
        //--Location--
        startLocationUpdate()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand")

        //startLocationUpdate()

        //startForeground(1, notification)

        return START_REDELIVER_INTENT   //kann man auslager in OnCreate
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun startLocationUpdate() {
        //locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        //hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        //hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        //if (hasGps || hasNetwork) {
        Log.i(TAG, "buildLocationRequest")
        buildLocationRequest()
        Log.i(TAG, "buildLocationCallback")
        buildLocationCallback()

        Log.i(TAG, "init FusedLocationProviderClient")
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)


        Log.i(TAG, "request Location Updates")
        requestLocationUpdates()

        //} else {    //TODO check if GPS is enabled
        //    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        //}
    }

    private fun requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //ActivityCompat.requestPermissions(, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
            return
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
    }

    private fun buildLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                for (location: Location in p0!!.locations) {
                    locations += location
                    Log.i(TAG, "get Location: " + location.latitude.toString() + " / " + location.longitude.toString())
                }
            }
        }
    }

    private fun buildLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 3000
        locationRequest.smallestDisplacement = 10.0F

    }
}

/* TODO buluetooth nfc -> to start automaticly call startFourgroundService -> strat also when the app is not in the forground
 * lower api -> prüft dabei -> ContextCompat.startForegroundService(this, serviceIntent)
 */
