package com.ema.jannik.logbook.activity

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.ema.jannik.logbook.R
import com.ema.jannik.logbook.fragment.DatePickerFragment
import com.ema.jannik.logbook.fragment.SettingFragment
import com.ema.jannik.logbook.fragment.TimePickerFragmentStart
import com.ema.jannik.logbook.helper.Utils
import com.ema.jannik.logbook.model.EditDriveRepository
import com.ema.jannik.logbook.model.database.Drive
import com.ema.jannik.logbook.model.database.Stage
import com.ema.jannik.logbook.view.ExplanationDialogAddDrive
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import kotlinx.android.synthetic.main.activity_add_drive.*
import java.lang.NumberFormatException
import java.text.DateFormat
import java.util.*

/**
 * This activity modify an entry.
 * The layout is the same as from the addDriveActivity.
 */
class EditDriveActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, View.OnClickListener {

    val TAG = this::class.java.name

    companion object {
        /**
         * to identify th placeAutocomplete request.
         */
        const val PLACE_AUTOCOMPLETE_REQUEST_CODE = 1

        const val EXTRA_ID = "id_edit"
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
     * represent the start address, to get later the latitude and longitude of the chosen address.
     */
    var startAddress: Stage? = null

    /**
     * represent the destination address, to get later the latitude and longitude of the chosen address.
     */
    var destinationAddress: Stage? = null

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
    var endTime: Calendar = Calendar.getInstance()

    /**
     * The drive object from the DetailsDriveActivity which will be updated.
     */
    var drive: Drive? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_drive)

        Log.i(TAG, "set ActionBar")
        //--set ActionBar--
        this.supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)    //set back button in the left top corner
        title = getString(R.string.menu_edit)  //Text for action bar

        Log.i(TAG, "set NumberPicker")
        //--set Numberpicker--
        //TODO set this whit db help
        numberPicker_odometerStart.maxValue = 400000
        numberPicker_odometerStart.minValue = 0

        numberPicker_distance.minValue = 0
        numberPicker_distance.maxValue = 1000

        numberPicker_odometer_end.minValue = 0
        numberPicker_odometer_end.maxValue = 400000

        Log.i(TAG, "set Button")
        //--set Button--
        resetImageButtonBackgroundColor()
        imageButton_noCategory.setBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.colorPrimaryDark
            )
        )

        Log.i(TAG, "set View with drive")
        //--set View with drive--
        getDrivefromDetailsDriveActivity()

        setOnClickNumberPicker()
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
            updateDrive()
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.numberPicker_odometerStart ->{
                numberPicker_odometer_end.value = numberPicker_odometerStart.value + numberPicker_distance.value
            }
            R.id.numberPicker_odometer_end -> {
                numberPicker_distance.value = numberPicker_odometerStart.value - numberPicker_odometer_end.value
            }
            R.id.numberPicker_distance -> {
                numberPicker_odometer_end.value = numberPicker_distance.value + numberPicker_odometerStart.value
            }
        }
    }

    private fun setOnClickNumberPicker() {
        numberPicker_odometerStart.setOnClickListener(this)
        numberPicker_odometer_end.setOnClickListener(this)
        numberPicker_distance.setOnClickListener(this)
    }

    /**
     * get id from DetailsDriveActivity and search db entry with this id. Sollte die ID kleiner 0 sein gibt es eine Toast
     * benachrichtigung und man wird zur DetailsDriveActivity zurück geleitet.
     * @return gibt den gefunden  "drive" aus der Datenbank zurück.
     * ruft setView auf.
     */
    private fun getDrivefromDetailsDriveActivity() {
        Log.i(TAG, "getDrivefromDetailsDriveActivity()")

        val repository = EditDriveRepository(application)
        val id: Int = intent.getIntExtra(EXTRA_ID, -1)
        if (id >= 0) {
            drive = repository.getById(id)
            setView(drive!!)
        } else {    //id not found in db
            Toast.makeText(this, getString(R.string.toast_notFound), Toast.LENGTH_LONG).show()
            finish()
        }

        Log.i(TAG, "finish getDrivefromDetailsDriveActivity()")
    }

    /**
     * Setzt die Werte in die View ein.
     * @param drive //TODO Kommentare
     */
    private fun setView(drive: Drive) {    //TODO was wen Werte nicht gestzt
        //--set View--
        onClickCategory(Utils.getImageButtonByCategory(drive.category))
        textView_startAddress.setText(drive.start!!.address)
        textView_destinationAddress.setText(drive.destination!!.address)
        edit_text_purpose.setText(drive.purpose)
        editText_start_time.setText(DateFormat.getDateTimeInstance().format(drive.start_timestamp.time))
        editText_endTime.setText(DateFormat.getDateTimeInstance().format(drive.destination_timestamp.time))
        editText_timeHour.setText(drive.duration.get(Calendar.HOUR_OF_DAY).toString()) //TODO hour oder hour_of_day
        editText_timeMinute.setText(drive.duration.get(Calendar.MINUTE).toString())
        numberPicker_odometerStart.value = drive.mileageStart.toInt()
        numberPicker_distance.value = drive.distance.toInt()
        numberPicker_odometer_end.value = drive.mileageDestination.toInt()
        //TODO set global variabels TODO weiter //TODO update funktioniert nicht
        //--set global variables, they are used  to save the entry in the db--
        startTime = drive.start_timestamp
        endTime = drive.destination_timestamp
        startAddress = drive.start
        destinationAddress = drive.destination
    }

    /**
     * save the input in the database
     */
    private fun updateDrive() {    //TODO hier weiter

        Log.i(TAG, "Minute.tostring " + editText_timeMinute.text.toString())

        var minute: Int?

        try {
            minute = Integer.parseInt(editText_timeMinute.text.toString())
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
        Log.i(TAG, "distance $startMilage")

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
        val repository = EditDriveRepository(application)

        drive!!.purpose = purpose
        drive!!.start = startAddress!!
        drive!!.destination = destinationAddress!!
        drive!!.mileageDestination = endMilage
        drive!!.mileageStart = startMilage
        drive!!.distance = distance
        drive!!.start_timestamp = startTime
        drive!!.destination_timestamp = endTime
        drive!!.category = category
        drive!!.duration = duration

        repository.update(drive!!)

        Toast.makeText(this, R.string.toast_addSuccessfully, Toast.LENGTH_LONG).show()

        finish()
        //finishActivity()  //TODO finish DetailsDriveActivity
    }

    /**
     * this function set the result from the TimePickerFragment.
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

        /*
        //set duration
        if (endTime.compareTo(startTime) == 1){     //when EndTime bigger then startTime
            val time = endTime
            time.add(Calendar.HOUR_OF_DAY, - startTime.get(Calendar.HOUR_OF_DAY))
            time.add(Calendar.MINUTE, - startTime.get(Calendar.MINUTE))
            editText_time.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(time))
        }
        */
    }

    /**
     * @param view the picker associated with the dialog
     * @param year the selected year
     * @param month the selected month (0-11 for compatibility with
     * [Calendar.MONTH])
     * @param dayOfMonth th selected day of the month (1-31, depending on
     * month)
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
     * This function is called to set the category
     * @param id the Id of the imageButton which you get from the function
     * Utils.getCategoryDrawableId() when you pass the category.
     */

    fun onClickCategory(id: Int) {

        resetImageButtonBackgroundColor()

        var message: String = getString(R.string.category)

        when (id) {
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
                    startAddress = Stage(
                        latitude = placeResult.latLng.latitude,
                        longitude = placeResult.latLng.longitude,
                        address = placeResult.address.toString()
                    )
                    textView_startAddress.setText(placeResult!!.address, TextView.BufferType.EDITABLE)
                } else if (startIntentAutoComplete == false) {  //Event wurd durch klick auf ZielAdresse ausgelöst
                    destinationAddress = Stage(
                        latitude = placeResult.latLng.latitude,
                        longitude = placeResult.latLng.longitude,
                        address = placeResult.address.toString()
                    )
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
