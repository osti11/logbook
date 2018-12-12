package com.ema.jannik.logbook

import android.app.Activity
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.ema.jannik.logbook.fragment.TimePickerFragmentStart
import kotlinx.android.synthetic.main.activity_add_drive.*
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import android.content.Intent
import android.location.Address
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import java.text.DateFormat
import java.util.*


/**
 * in this Activity the user can add a past ride to the db.
 */
class AddDriveActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

    companion object {
        /**
         * to identify th placeAutocomplete request.
         */
        const val PLACE_AUTOCOMPLETE_REQUEST_CODE = 1
    }

    /**
     * This boolean is true when onClickStartAddressAutoComplete() start the autocomplete Activity.
     * To know where to set the result
     */
    var startIntentAutoComplete: Boolean? = null

    /**
     * This boolean is true when the onClickTimePicker() start the startTimePickerFragment().
     */
    var startFragmentTimePicker: Boolean? = null

    /**
     * represent the start address, to get later the latitude and longitude of the chosen address.
     */
    var startAddress: Address? = null

    /**
     * represent the destination address, to get later the latitude and longitude of the chosen address.
     */
    var destinationAddress: Address? = null

    /**
     * represent the chosen imageButton
     */
    var category: Int = 0

    /**
     * represent the start time to calculate the duration.
     */
    var startTime: Calendar = Calendar.getInstance()

    /**
     * represent the end time to calculate the duration.
     */
    lateinit var endTime: Calendar

    /**
     * This function is called when the activity is created to set the ActionBar, TimePicker and the imageButtons.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_drive)

        //--set ActionBar--
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)    //set back button
        title = getString(R.string.menu_addDrive)  //Text for action bar

        //--set Numberpicker--
        //TODO set this whit db help
        number_picker_mileageStart.maxValue = 200000
        number_picker_mileageStart.minValue = 300000

        numberPicker_odometerStart.minValue = 200
        numberPicker_odometerStart.maxValue = 300

        //--set Button--
        resetImageButtonBackgroundColor()
        imageButton_noCategory.setBackgroundColor( ContextCompat.getColor(applicationContext , R.color.colorPrimaryDark) )


    }

    /**
     * set the menu on top.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_drive_menu, menu)    //tell system to use add note menu
        return true //when true Menu will shown
    }

    /**
     * call the function saveDrive() when save in the right top corner is clicked.
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.save_drive) {
            saveDrive()
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

    /**
     * save the input in the database
     */
    private fun saveDrive(){    //TODO hier weiter
        val purpose = edit_text_purpose.text.toString()
        val startAddress = startAddress
        val destinationAddress = destinationAddress
        val startMilage = number_picker_mileageStart.value
        val distance = numberPicker_odometerStart.value
        val endMilage = startMilage + distance
        //val startTime = startTime
        //val endTime = endTime

        if (purpose.trim().isEmpty()) {
            Toast.makeText(this, "Zweck der fahrt eintragen", Toast.LENGTH_SHORT).show()    //TODO string xml
            return
        } //else if ()  //TODO handle when entry is incomplete

        //TODO save in db

        finish()
    }

    /**
     * this function set the result from the TimePickerFragment.
     */
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        //if (startFragmentTimePicker == false){
            endTime = Calendar.getInstance()
            endTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            endTime.set(Calendar.MINUTE, minute)
            endTime.set(Calendar.SECOND, 0)
            setTime(endTime, editText_endTime)
        //}
        /*
        else if (startFragmentTimePicker == true){Calendar.getInstance()
            startTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            startTime.set(Calendar.MINUTE, minute)
            startTime.set(Calendar.SECOND, 0)
            editText_start_time.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(startTime))
        }
        startFragmentTimePicker = null

        /*
        //set duration
        if (endTime.compareTo(startTime) == 1){     //when EndTime bigger then startTime
            val time = endTime
            time.add(Calendar.HOUR_OF_DAY, - startTime.get(Calendar.HOUR_OF_DAY))
            time.add(Calendar.MINUTE, - startTime.get(Calendar.MINUTE))
            editText_time.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(time))
        }
        */*/
    }

    /**
     * set the time in editText
     */
    private fun setTime(calendar: Calendar?, editText: EditText?) {
        val text: String = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar)    //TODO errror
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
        editText!!.setText(text)
    }

    /**
     * This function is called when the user click on an imageButton.
     * The imageButton who call this function become a different background colour, show a toast message and set an value to the integer category
     */
    fun onClickCategory(view: View){

        resetImageButtonBackgroundColor()

        var message: String = getString(R.string.category)

        when (view.id){
            R.id.imageButton_noCategory -> {
                imageButton_noCategory.setBackgroundColor( ContextCompat.getColor(applicationContext , R.color.colorPrimaryDark) )
                category = 0    //TODO welche werte?
                message += getString(R.string.category_0)
            }
            R.id.imageButton_private -> {
                imageButton_private.setBackgroundColor( ContextCompat.getColor(applicationContext , R.color.colorPrimaryDark) )
                category = 1
                message += getString(R.string.category_1)
            }
            R.id.imageButton_work -> {
                imageButton_work.setBackgroundColor( ContextCompat.getColor(applicationContext , R.color.colorPrimaryDark) )
                category = 2
                message += getString(R.string.category_2)

            }
            R.id.imageButton_way -> {
                imageButton_way.setBackgroundColor( ContextCompat.getColor(applicationContext , R.color.colorPrimaryDark) )
                category = 3
                message += getString(R.string.category_3)
            }
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * This function set the background of all imageButton elments to the default value
     */
    private fun resetImageButtonBackgroundColor(){
        imageButton_noCategory.setBackgroundColor( ContextCompat.getColor(applicationContext , R.color.button_material_light))
        imageButton_private.setBackgroundColor( ContextCompat.getColor(applicationContext , R.color.button_material_light))
        imageButton_work.setBackgroundColor( ContextCompat.getColor(applicationContext , R.color.button_material_light))
        imageButton_way.setBackgroundColor( ContextCompat.getColor(applicationContext , R.color.button_material_light))
    }

    /**
     * set the boolean startFragmentTimePicker to true and call the function startTimePickerFragment()
     */
    fun onClickTimePicker(view: View) {  //TODO mehre Listener
        startFragmentTimePicker = false
        startTimePickerFragment()
    }

    /**
     * set the boolean startFragmentTimePicker to false and call the function startTimePickerFragment()
     */
    fun onClickTimePickerStart(view: View) {  //TODO mehre Listener
        startFragmentTimePicker = true
        startTimePickerFragment()
    }

    /**
     * This function start the TimePickerFragment to choose a time
     */
    private fun startTimePickerFragment(){
        val timePicker: DialogFragment = TimePickerFragmentStart()
        timePicker.show(supportFragmentManager, "time picker")
    }

    /**
     * set the boolean startIntentAutoComplete to true and call the function buildPlaceAutocompleteIntent()
     */
    fun onClickStartAddressAutoComplete(view: View) {
        startIntentAutoComplete = true
        buildPlaceAutocompleteIntent()
    }

    /**
     * set the boolean startIntentAutoComplete to false and call the function buildPlaceAutocompleteIntent()
     */
    fun onClickDestinationAddressAutoComplete(view: View) {
        startIntentAutoComplete = false
        buildPlaceAutocompleteIntent()
    }

    /**
     * start an PlaceAutocompleteActivity
     */
    private fun buildPlaceAutocompleteIntent() {
        try {
            val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                .build(this)
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE)
        } catch (e: GooglePlayServicesRepairableException) {
            // TODO: Handle the error.
        } catch (e: GooglePlayServicesNotAvailableException) {
            // TODO: Handle the error.
        }
    }

    /**
     * set the result of the PlaceAutocompleteActivity.
     * When the boolean startIntentAutoComplete is true the result is set to textView_startAddress otherwise to textView_destinationAddress.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val placeResult = PlaceAutocomplete.getPlace(this, data!!)
                if (startIntentAutoComplete == true) {
                    textView_startAddress.setText(placeResult!!.address, TextView.BufferType.EDITABLE)
                } else if (startIntentAutoComplete == false) {
                    textView_destinationAddress.setText(placeResult!!.address, TextView.BufferType.EDITABLE)
                }
                startIntentAutoComplete = null
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                val status = PlaceAutocomplete.getStatus(this, data!!)
                // TODO: Handle the error.
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

}
