package com.ema.jannik.logbook.activity

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.ema.jannik.logbook.fragment.TimePickerFragmentStart
import kotlinx.android.synthetic.main.activity_add_drive.*
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import android.content.Intent
import android.location.Address
import android.util.Log
import android.widget.*
import androidx.core.content.ContextCompat
import com.ema.jannik.logbook.R
import com.ema.jannik.logbook.fragment.DatePickerFragment
import com.ema.jannik.logbook.model.AddDriveRepository
import com.ema.jannik.logbook.model.database.Drive
import com.ema.jannik.logbook.model.database.Stage
import com.ema.jannik.logbook.view.ExplanationDialogAddDrive
import com.google.android.gms.location.places.Place
import java.lang.NullPointerException
import java.lang.NumberFormatException
import java.text.DateFormat
import java.util.*


/**
 * in this Activity the user can add a past ride to the db.
 */
open class AddDriveActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    val TAG = this::class.java.name

    companion object {
        /**
         * Um die placeAutocomplete Anfrage zu identifizieren.
         */
        const val PLACE_AUTOCOMPLETE_REQUEST_CODE = 1
    }

    /**
     * This boolean is true when onClickStartAddressAutoComplete() start the autocomplete Activity.
     * To know where to set the result
     */
    var startIntentAutoComplete: Boolean? = null

    /**
     * This boolean is true when the onClickEndTime() start the startTimePickerFragment().
     */
    var startFragmentTimePicker: Boolean? = null

    /**
     * Representiert die Startadresse. Enthält lng, lat und die Adresse als String zum späteren speichern in der DB.
     */
    var startAddress: Place? = null

    /**
     * Representiert die Zieladresse. Enthält lng, lat und die Adresse als String zum späteren speichern in der DB.
     */
    var destinationAddress: Place? = null

    /**
     * Representiert die ausgewählt Katigorie in form eines Imagebuttons.
     */
    var category: Int = 0

    /**
     * Representiert den Abfahrtszeitpunkt.
     */
    var startTime: Calendar = Calendar.getInstance()

    /**
     * Ankunftszeit
     */
    var endTime: Calendar = Calendar.getInstance()

    /**
     * Diese Funktion wird beim erstellen der 'activity' aufgerufen und setzt die 'actionBar', 'imageButton' und 'numberPicker'.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_drive)

        //--set ActionBar--
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)    //set back button
        title = getString(R.string.menu_addDrive)  //Text for action bar

        //--set Numberpicker--
        //TODO set this whit db help
        numberPicker_odometerStart.maxValue = 400000
        numberPicker_odometerStart.minValue = 0

        numberPicker_distance.minValue = 0
        numberPicker_distance.maxValue = 1000

        numberPicker_odometer_end.minValue = 0
        numberPicker_odometer_end.maxValue = 400000

        setNumberpickerwithLastEntry()

        //--set Button--
        resetImageButtonBackgroundColor()
        imageButton_noCategory.setBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.colorPrimaryDark
            )
        )
    }

    /**
     *
     */
    private fun setNumberpickerwithLastEntry(){
        val repository: AddDriveRepository = AddDriveRepository(application)

        var drive: Drive?
        try {
            drive = repository.getLastDrive()
        } catch (e: Exception) {
            drive = null
        }
        if (drive != null) {
            numberPicker_odometerStart.value = drive.mileageStart
            numberPicker_odometer_end.value  = drive.mileageDestination
            numberPicker_distance.value      = drive.distance
        }
    }

    /**
     * Setzt add_drive_menu.xml als Menülayout.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_drive_menu, menu)
        return true //when true Menu will shown
    }

    /**
     * Ruft die Funktion saveDrive() auf wenn 'SPEICHERN' in der rechten oberen Ecke geklickt wird.
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
     * Speichert die Eingabe in der DB.
     */
    private fun saveDrive() {    //TODO hier weiter

        Log.i(TAG, "Minute.tostring " + editText_timeMinute.text.toString())

        var minute: Int?

        try {
            minute = Integer.parseInt(editText_timeMinute.text.toString())  //TODO nur phasen wenn was eingetragen
        } catch (e: NumberFormatException) { //string can not be past into an integer
            minute = null
        }

        Log.i(TAG, "Minute.Int " + minute)

        Log.i(TAG, "Hour.tostring " + editText_timeHour.text.toString())

        var hour: Int?
        try {
            hour = Integer.parseInt(editText_timeHour.text.toString())
        } catch (e: NumberFormatException) {    //string can not be past into an integer
            hour = null
        }

        Log.i(TAG, "Hour.Int " + hour)

        val purpose = edit_text_purpose.text.toString()
        Log.i(TAG, "purpose: " + purpose)

        val startAddress = startAddress
        Log.i(TAG, "startAddress " + startAddress)

        val destinationAddress = destinationAddress
        Log.i(TAG, "destinationAddress " + destinationAddress)

        val startMilage = numberPicker_distance.value   //TODO start milage?
        Log.i(TAG, "distance " + startMilage)

        val distance = numberPicker_odometerStart.value
        Log.i(TAG, distance.toString())

        val endMilage = numberPicker_odometer_end.value
        Log.i(TAG, endMilage.toString())

        val startTime = startTime
        Log.i(TAG, startTime.time.toString())

        val endTime = endTime
        Log.i(TAG, endTime.time.toString())

        var message: String = ""

        if (minute == null || minute > 59 || minute < 0)  //valid duration minute //TODO unsafe use
            message += getString(R.string.toast_minute) + "\n"
        if (hour == null || hour < 0 || hour > 100)  //valid duration hour
            message += getString(R.string.toast_hour) + "\n"
        if (startAddress == null)
            message += getString(R.string.toast_startAddress) + "\n"
        if (destinationAddress == null)
            message += getString(R.string.toast_destinationAddress) + "\n"  //TODO namens gebung Toast
        if (endMilage - startMilage != distance)
            message += getString(R.string.toast_mileage) + "\n"
        if (editText_start_time.text == null || editText_start_time.text == null)
            message += getString(R.string.toast_time)
        if (message != "") {   //when message has benn modified then show the message
            val dialog = ExplanationDialogAddDrive(message)
            dialog.show(supportFragmentManager, "false input")
            return
        }

        val duration: Calendar = Calendar.getInstance()
        duration.set(Calendar.HOUR_OF_DAY, hour!!)
        duration.set(Calendar.MINUTE, minute!!)

        //save in db
        val repository: AddDriveRepository = AddDriveRepository(application)
        repository.insert(
            Drive
                (
                purpose = purpose,
                start = Stage(
                    longitude = startAddress!!.latLng.longitude,
                    latitude = startAddress.latLng.latitude,
                    address = startAddress.address.toString()
                ),
                destination = Stage(
                    longitude = destinationAddress!!.latLng.longitude,
                    latitude = destinationAddress.latLng.latitude,
                    address = destinationAddress.address.toString()
                ),
                mileageDestination = endMilage,
                mileageStart = startMilage,
                distance = distance,
                start_timestamp = startTime,
                destination_timestamp = endTime,
                category = category,
                duration = duration
            )
        )

        Toast.makeText(this, R.string.toast_addSuccessfully, Toast.LENGTH_LONG).show()

        finish()
    }

    /**
     * Setzt das Ergebnis des TimePickerFragment.
     * startFragmentTimePicker == false -> Ergebnis wird in 'endTime' gespeichert.
     * startFragmentTimePicker == true -> Ergebnis wird in 'startTime' gespeichert.
     */
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        Log.i(TAG, "onTimeSet()")

        if (startFragmentTimePicker == false) {
            endTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            endTime.set(Calendar.MINUTE, minute)
            endTime.set(Calendar.SECOND, 0)
            Log.i(TAG, "setTime(start)")
            setTime(endTime, editText_endTime)
        } else if (startFragmentTimePicker == true) {
            startTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            startTime.set(Calendar.MINUTE, minute)
            startTime.set(Calendar.SECOND, 0)
            Log.i(TAG, "setTime(start)")
            setTime(startTime, editText_start_time)
        }
        startFragmentTimePicker = null
    }

    /**
     * @param view the picker associated with the dialog
     * @param year the selected year
     * @param month the selected month (0-11 for compatibility with
     * [Calendar.MONTH])
     * @param dayOfMonth th selected day of the month (1-31, depending on
     * month)
     */

    /**
     *
     */
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Log.i(TAG, "onDataSet()")
        startTime.set(year, month, dayOfMonth)
        startTimePickerFragment()
    }

    /**
     * set the time in editText
     */
    private fun setTime(calendar: Calendar, editText: EditText) {
        Log.i(TAG, "setTime()")
        val text: String = DateFormat.getDateTimeInstance().format(calendar.time)

        Log.i(TAG, "setText")
        editText.setText(text)
        Log.i(TAG, "setTime() finish")
    }

    /**
     * This function is called when the user click on an imageButton.
     * The imageButton who call this function become a different background colour, show a toast message and set an value to the integer category
     */
    fun onClickCategory(view: View) {

        resetImageButtonBackgroundColor()

        var message: String = getString(R.string.category)

        when (view.id) {
            R.id.imageButton_noCategory -> {
                imageButton_noCategory.setBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.colorPrimaryDark
                    )
                )
                category = 0    //TODO welche werte?
                message += getString(R.string.category_0)
            }
            R.id.imageButton_private -> {
                imageButton_private.setBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.colorPrimaryDark
                    )
                )
                category = 1
                message += getString(R.string.category_1)
            }
            R.id.imageButton_work -> {
                imageButton_work.setBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.colorPrimaryDark
                    )
                )
                category = 2
                message += getString(R.string.category_2)

            }
            R.id.imageButton_way -> {
                imageButton_way.setBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.colorPrimaryDark
                    )
                )
                category = 3
                message += getString(R.string.category_3)
            }
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * This function set the background of all imageButton elments to the default value
     */
    private fun resetImageButtonBackgroundColor() {
        imageButton_noCategory.setBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.button_material_light
            )
        )
        imageButton_private.setBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.button_material_light
            )
        )
        imageButton_work.setBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.button_material_light
            )
        )
        imageButton_way.setBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.button_material_light
            )
        )
    }

    /**
     * set the boolean startFragmentTimePicker to true and call the function startTimePickerFragment()
     */
    fun onClickEndTime(view: View) {  //TODO mehre Listener
        Log.i(TAG, "onClickEndTime")
        startFragmentTimePicker = false
        startDatePickerFragment()
    }

    /**
     * set the boolean startFragmentTimePicker to false and call the function startTimePickerFragment()
     */
    fun onClickStartTime(view: View) {  //TODO mehre Listener
        Log.i(TAG, "onClick start Time()")
        startFragmentTimePicker = true
        startDatePickerFragment()
    }

    /**
     * This function start the TimePickerFragment to choose a time
     */
    private fun startTimePickerFragment() {
        Log.i(TAG, "startTimePicker()")
        val timePicker: DialogFragment = TimePickerFragmentStart()
        timePicker.show(supportFragmentManager, "time picker")
    }

    private fun startDatePickerFragment() {
        Log.i(TAG, "startDatePicker()")
        val datePicker: DialogFragment = DatePickerFragment()
        datePicker.show(supportFragmentManager, "datePicker")
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
            startActivityForResult(
                intent,
                PLACE_AUTOCOMPLETE_REQUEST_CODE
            )
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
                if (startIntentAutoComplete == true) {      //Event wurd durch klick auf StartAdresse ausgelöst
                    startAddress = placeResult
                    textView_startAddress.setText(placeResult!!.address, TextView.BufferType.EDITABLE)
                } else if (startIntentAutoComplete == false) {  //Event wurd durch klick auf ZielAdresse ausgelöst
                    destinationAddress = placeResult
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
