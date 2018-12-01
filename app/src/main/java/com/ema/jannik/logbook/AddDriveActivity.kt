package com.ema.jannik.logbook

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.ema.jannik.logbook.fragment.TImePickerFragmentStart
import com.ema.jannik.logbook.fragment.TimePickerFragment
import kotlinx.android.synthetic.main.activity_add_drive.*

class AddDriveActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_drive)

        //--set ActionBar--
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)    //set back button
        title = getString(R.string.menu_addDrive)  //Text for action bar

        //--set Numberpicker--
        //TODO set this whit db help
        number_picker_mileageStart.maxValue = 500000
        number_picker_mileageStart.minValue = 50000

        number_picker_odometer.minValue = 0
        number_picker_odometer.maxValue = 2000

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_drive_menu, menu)    //tell system to use add note menu
        return true //when true Menu will shown
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.save_note) {
            //call function to save
            return true
        }
        else
        {
            return super.onOptionsItemSelected(item)
        }
    }


    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        editText_time.setText(hourOfDay.toString() + ":" + minute.toString())   //TODO auslagern
    }


    fun onClickTimePicker(view: View){  //TODO mehre Listener

        TimePickerDialog.OnTimeSetListener{view: TimePicker?, hourOfDay: Int, minute: Int ->
            editText_time.setText(hourOfDay.toString() + ":" + minute.toString())
        }

        val timePicker:DialogFragment = TimePickerFragment()
        timePicker.show(supportFragmentManager, "time picker")

    }

    fun onClickTimePickerStart(view: View){  //TODO mehre Listener
        TimePickerDialog.OnTimeSetListener{view: TimePicker?, hourOfDay: Int, minute: Int ->
            editText_start_time.setText(hourOfDay.toString() + ":" + minute.toString())
        }
        val timePicker:DialogFragment = TImePickerFragmentStart()
        timePicker.show(supportFragmentManager, "time picker")

    }
}
