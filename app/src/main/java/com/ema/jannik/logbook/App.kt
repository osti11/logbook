package com.ema.jannik.logbook

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

/**
 * This class is a helper class to create the NotificationChannel
 */
class App : Application() {
    companion object {
        val CHANNEL_UPDATEDRIVE_ID: String = "updateDrive"
        val CHANNEL_FORGROUNDSERVICE_ID: String = "updatePosition"
    }

    /**
     * call  createNotifocationChannels() when the app is started
     */
    override fun onCreate() {   //set the channel by creation of the app
        super.onCreate()
        createNotifocationChannels()
    }

    /**
     *  this function instance an NotificationManager and create the Notification Channel when the SDK is 26 or higher
     */
    private fun createNotifocationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //class can just created when Android version Oreo oder higher
            //UpdateNotification
            val channelUpdate =  NotificationChannel(
                CHANNEL_UPDATEDRIVE_ID,
                getString(R.string.channel_description),    //just to identifie
                NotificationManager.IMPORTANCE_HIGH     //How important, wie laut, wies aussieht    //TODO nachen welche wichtigkeit verwenden
            )
            channelUpdate.description = getString(R.string.channel_description)

            //FourgroundService Notification
            val channelForground = NotificationChannel(
                CHANNEL_FORGROUNDSERVICE_ID,
                "Get position",  //TODO in string.xml,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channelUpdate )
            manager.createNotificationChannel(channelForground)
        }
    }
}