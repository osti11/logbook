package com.ema.jannik.logbook.activity

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.nfc.NfcAdapter
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.getSystemService
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.checkCallingOrSelfPermission
import kotlinx.android.synthetic.main.activity_record_drive.*
import com.ema.jannik.logbook.LocationUpdateService
import com.ema.jannik.logbook.R
import java.text.DateFormat
import java.util.*


class RecordDriveActivity : AppCompatActivity() {

    companion object {
        private const val PERMISSION_LOCATION_REQUEST = 1000

        const val EXTRA_BLUETOOTH_START = "bluetoothStart"
    }

    private val permissions =
        arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_drive)

        //--set ActionBar--
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)
        title = getString(R.string.title_Record)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!checkPermission(permissions)) {
                requestPermissions(
                    permissions,
                    PERMISSION_LOCATION_REQUEST
                )
            }
        }

        //start service when reciever start this activity
        if(intent.getBooleanExtra(EXTRA_BLUETOOTH_START, false)){
            startForegroundService()
        }

        setViewWhenRunning()
    }

    private fun startForegroundService() {
        textView.visibility = View.VISIBLE

        button_stopFS.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
        button_stopFS.setTextColor(ContextCompat.getColor(this, R.color.light))

        val serviceIntent = Intent(this, LocationUpdateService::class.java)
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    /**
     * This onClickListener starts the LocationUpdates as foregroundService
     */
    fun onClickStartForegroundService(view: View) {
        startForegroundService()
    }

    /**
     * This onClickListener stops the foregroundService and remove locationUpdates.
     */
    fun onClickStopForegroundService(view: View) {
        Toast.makeText(applicationContext, R.string.toast_recDriveStop, Toast.LENGTH_SHORT).show()

        val serviceIntent = Intent(this, LocationUpdateService::class.java)
        stopService(serviceIntent)
        finish()
    }

    private fun checkPermission(permissionArray: Array<String>): Boolean {
        var allSuccess = true
        for (i in permissionArray.indices) {
            if (checkCallingOrSelfPermission(permissionArray[i]) == PackageManager.PERMISSION_DENIED)
                allSuccess = false
        }
        return allSuccess
    }

    /**
     * Überprüft ob der LocationUpdateService ausgeführt wird.
     */
    private fun isForegroundServiceRunning(): Boolean{
        return LocationUpdateService.isRunning
    }

    /**
     * Wenn der Service schon aus geführt wird, wird der passende Text angezeigt.
     */
    private fun setViewWhenRunning() {
        if(isForegroundServiceRunning()){
            onClickStartForegroundService(View(applicationContext))
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_LOCATION_REQUEST) {
            var allSuccess = true
            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    allSuccess = false
                    val requestAgain =
                        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(
                            permissions[i]
                        )
                    if (requestAgain) {
                        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Go to settings and enable the permission", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            if (!allSuccess)
                finish()
        }
    }
}