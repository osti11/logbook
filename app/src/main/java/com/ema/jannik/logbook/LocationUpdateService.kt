package com.ema.jannik.logbook

import android.Manifest
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.ema.jannik.logbook.App.Companion.CHANNEL_FORGROUNDSERVICE_ID
import android.app.NotificationManager
import android.location.Location
import android.os.Handler
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.Manifest.permission
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.R.string.ok
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import com.ema.jannik.logbook.R.layout.activity_main
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction




class LocationUpdateService : Service() {

    private val PACKAGE_NAME = "com.ema.jannik.logbook"

    private val TAG = LocationUpdateService::class.java.simpleName


    val ACTION_BROADCAST = "$PACKAGE_NAME.broadcast"

    val EXTRA_LOCATION = "$PACKAGE_NAME.location"
    private val EXTRA_STARTED_FROM_NOTIFICATION = "$PACKAGE_NAME.started_from_notification"

    /**
     * The desired interval for location updates. Inexact. Updates may be more or less frequent.
     */
    private val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 10000

    /**
     * The fastest rate for active location updates. Updates will never be more frequent
     * than this value.
     */
    private val FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2

    /**
     * Used to check whether the bound activity has really gone away and not unbound as part of an
     * orientation change. We create a foreground service notification only if the former takes
     * place.
     */
    private val mChangingConfiguration = false

    private val mNotificationManager: NotificationManager? = null

    /**
     * Contains parameters used by [com.google.android.gms.location.FusedLocationProviderApi].
     */
    private val mLocationRequest: LocationRequest? = null

    /**
     * Provides access to the Fused Location Provider API.
     */
    private val mFusedLocationClient: FusedLocationProviderClient? = null

    /**
     * Callback for changes in location.
     */
    private val mLocationCallback: LocationCallback? = null

    private val mServiceHandler: Handler? = null

    /**
     * The current location.
     */
    private val mLocation: Location? = null



    var notification :Notification = Notification()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    /**
     * Called by the system when the service is first created.  Do not call this method directly.
     */
    override fun onCreate() {
        super.onCreate()

        //--build notification--
        val notificationIntent = Intent(this, RecordDriveActivity::class.java)  //To click on the notification
        val pendingIntent = PendingIntent.getActivity(this,
            0, notificationIntent, 0)   //flag was happend by update intent

        notification = NotificationCompat.Builder(this, CHANNEL_FORGROUNDSERVICE_ID)
            .setContentTitle("GPS")    //TODO in string.xml
            .setContentText("run location service")
            .setSmallIcon(R.drawable.ic_location)
            .setContentIntent(pendingIntent)
            .build()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        startForeground(1, notification)

        return START_REDELIVER_INTENT   //kann man auslager in OnCreate
    }



    override fun onDestroy() {
        super.onDestroy()
    }
}

/* TODO buluetooth nfc -> to start automaticly call startFourgroundService -> strat also when the app is not in the forground
 * lower api -> prüft dabei -> ContextCompat.startForegroundService(this, serviceIntent)
 */
