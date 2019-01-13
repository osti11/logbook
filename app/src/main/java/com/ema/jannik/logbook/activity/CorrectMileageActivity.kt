package com.ema.jannik.logbook.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ema.jannik.logbook.R
import com.ema.jannik.logbook.helper.Utils
import com.ema.jannik.logbook.model.AddDriveRepository
import com.ema.jannik.logbook.model.database.Drive
import kotlinx.android.synthetic.main.activity_correct_mileage.*
import java.lang.Exception
import java.util.*

/**
 *
 */
class CorrectMileageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_correct_mileage)

        this.supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)    //set back button in the left top corner
        title = getString(R.string.textView_correctMileage)                 //Text for action bar
    }

    /**
     *
     */
    fun onClickSave(view: View) {
        var mileage: Int
        try {
            mileage = editText_correctMileage.text.toString().toInt()
        } catch (e: NumberFormatException) {    //string is empty
            mileage = 0
        }

        if (mileage < 1) {
            Toast.makeText(this, R.string.toast_incorrectMileage, Toast.LENGTH_SHORT).show()
        } else {    //save
            save(mileage)
            finish()
        }
    }

    /**
     *
     */
    private fun save(mileage: Int){
        val repository = AddDriveRepository(application)
        repository.insert(Drive(
            category = 4,
            start_timestamp = Calendar.getInstance(),
            destination_timestamp = Calendar.getInstance(),
            mileageStart = mileage,
            mileageDestination = mileage,
            distance = 0,
            duration = Calendar.getInstance(),
            purpose = getString(Utils.getCategory(4)),
            start = null,
            destination = null
        ))
    }
}
