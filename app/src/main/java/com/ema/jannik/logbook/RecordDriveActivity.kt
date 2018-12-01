package com.ema.jannik.logbook


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_record_drive.*
import android.app.PendingIntent
import android.util.Log
import androidx.fragment.app.FragmentActivity


class RecordDriveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_drive)

        //--set ActionBar--
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)
        title = "aufzeichnen"   //TODO in STring.xml
    }

    /**
     * This onClickListener starts or ends the LocationsService and change the text of the button
     */
    fun onClickForegroundService(view: View){
        if (button_startEnd.text == getString(R.string.button_recStart)) {
            button_startEnd.text = getString(R.string.button_recEnd)
            val serviceIntent = Intent(this, LocationUpdateService::class.java)
            //TODO put to Service
            startService(serviceIntent)
        }
        else if (button_startEnd.text == getString(R.string.button_recEnd)) {
            val serviceIntent = Intent(this, LocationUpdateService::class.java)
            stopService(serviceIntent)
            //TODO back to main activity    //StopSelf
            //TODO heavy work in thread
        }
    }
}