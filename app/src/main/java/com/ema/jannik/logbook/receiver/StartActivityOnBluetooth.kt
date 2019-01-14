package com.ema.jannik.logbook.receiver

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.ema.jannik.logbook.LocationUpdateService
import com.ema.jannik.logbook.activity.RecordDriveActivity
import com.ema.jannik.logbook.fragment.SettingFragment


class StartActivityOnBluetooth : BroadcastReceiver() {

    val TAG = this.javaClass.name

    /**
     * This reciever is called when a bluetooth connection is started or stopped and start or stop the LocationUpdateService.
     * @param context The Context in which the receiver is running.
     * @param intent The Intent being received.
     */
    override fun onReceive(context: Context?, intent: Intent?) {

        //TODO for development
        Toast.makeText(context, "receiver is called", Toast.LENGTH_SHORT).show()

        val action = intent!!.action
        val i = Intent(context, RecordDriveActivity::class.java)

        if (getBluetoothConnectedDevice(intent) == getBluetoothSettings(context)) {
            when (action) {
                BluetoothDevice.ACTION_ACL_CONNECTED -> {
                    i.putExtra(RecordDriveActivity.EXTRA_BLUETOOTH_START, true)
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                    context!!.startActivity(i)
                }
                BluetoothDevice.ACTION_ACL_DISCONNECTED -> {
                    val serviceIntent = Intent(context, LocationUpdateService::class.java)
                    context!!.stopService(serviceIntent)
                    context.startActivity(i)
                }
            }
        }
    }

    /**
     * Get the name from the connected bluetooth device.
     * @param intent pass the intent that is passed from the onReceive function.
     * @return Gibt den Namen des verbundenen Gerätes zurück.
     */
    private fun getBluetoothConnectedDevice(intent: Intent?): String {
        val device = intent!!.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
        Log.i(TAG, "bDeviceName: " + device.name)
        return device.name
    }

    /**
     *
     */
    private fun getBluetoothSettings(context: Context?): String? {
        //get values from shared preferences
        val sharedPreferences = context!!.getSharedPreferences(
            SettingFragment.SHARED_PREFERENCES, //name of shared preferences
            Context.MODE_PRIVATE        //just this application can change the preferences
        )
        val connectionName: String = sharedPreferences.getString(
            SettingFragment.BLUETOOTH_CONNECTION_NAME,
            ""
        )!!
        val useBluetooth: Boolean = sharedPreferences.getBoolean(
            SettingFragment.BLUETOOTH_CONNECTION,
            false
        )

        if (useBluetooth) {
            if (connectionName != "")
                return connectionName
        }
        return null

    }
}
