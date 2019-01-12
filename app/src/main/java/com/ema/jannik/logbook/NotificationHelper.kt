package com.ema.jannik.logbook

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHelper(base: Context) : ContextWrapper(base) {

    private var notificationManager: NotificationManager? = null

    companion object {
        const val CHANNEL_UPDATE_ID = "channelUpdate"
        const val CHANNEL_UPDATE_NAME = "Update"
        const val CHANNEL_TRANSFER_ID = "channelTransfer"
        const val CHANNEL_TRANSFER_NAME = "Transfer"
    }

    init {

        createChannels()


    }

    /**
     * create notification channels when the sdk level is android oreo or higher.
     */
    private fun createChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelUpdate =
                NotificationChannel(CHANNEL_UPDATE_ID, CHANNEL_UPDATE_NAME, NotificationManager.IMPORTANCE_DEFAULT)

            channelUpdate.enableLights(true)
            channelUpdate.enableVibration(true)
            channelUpdate.lightColor = R.color.colorPrimaryDark
            channelUpdate.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

            getManager().createNotificationChannel(channelUpdate)

            val channelTransfer =
                NotificationChannel(CHANNEL_TRANSFER_ID, CHANNEL_TRANSFER_NAME, NotificationManager.IMPORTANCE_DEFAULT)

            channelTransfer.enableLights(true)
            channelTransfer.enableVibration(true)
            channelTransfer.lightColor = R.color.colorPrimaryDark
            channelTransfer.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

            getManager().createNotificationChannel(channelTransfer)
        }
    }

    /**
     * Return notificationManager.
     * If the manager is null create an new one.
     */
    fun getManager(): NotificationManager {
        if(notificationManager == null) {
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        return notificationManager!!
    }

    /**
     * @param title String der als Titel der Notification gestzt wird.
     * @param description String der als description der Notifdication gestzt wird.
     * @param id Die Konstante um die Notification zu identifizieren. In diesem Fall CHANNEL_UPDATE_ID oder CHANNEL_TRANSFER_ID.
     * @return
     */
    fun getChannelNotification(title: String, description: String, id: String) : NotificationCompat.Builder? {
        when(id) {
            CHANNEL_TRANSFER_ID -> {

            }
            CHANNEL_UPDATE_ID -> {

            }
        }
        return null
    }
}