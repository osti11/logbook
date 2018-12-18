package com.ema.jannik.logbook.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_record_drive.*
import com.ema.jannik.logbook.LocationUpdateService
import com.ema.jannik.logbook.R


class RecordDriveActivity : AppCompatActivity() {

    companion object {
        private const val PERMISSION_LOCATION_REQUEST = 1000
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
        title = "aufzeichnen"   //TODO in STring.xml

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!checkPermission(permissions)) {
                requestPermissions(permissions,
                    PERMISSION_LOCATION_REQUEST
                )
            }
        }
    }

    /**
     * This onClickListener starts or ends the LocationsService and change the text of the button
     */
    fun onClickForegroundService(view: View) {
        if (button_startEnd.text == getString(R.string.button_recStart)) {
            button_startEnd.text = getString(R.string.button_recEnd)
            val serviceIntent = Intent(this, LocationUpdateService::class.java)
            ContextCompat.startForegroundService(this, serviceIntent)

        } else if (button_startEnd.text == getString(R.string.button_recEnd)) {
            val serviceIntent = Intent(this, LocationUpdateService::class.java)
            stopService(serviceIntent)
            //TODO back to main activity    //StopSelf
            //TODO heavy work in thread
        }
    }

    private fun checkPermission(permissionArray: Array<String>): Boolean {
        var allSuccess = true
        for (i in permissionArray.indices) {
            if (checkCallingOrSelfPermission(permissionArray[i]) == PackageManager.PERMISSION_DENIED)
                allSuccess = false
        }
        return allSuccess
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

