package com.ema.jannik.logbook.fragment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.ema.jannik.logbook.receiver.AlertReciever
import com.ema.jannik.logbook.R
import kotlinx.android.synthetic.main.fragment_setting.*
import java.util.*
import android.bluetooth.BluetoothAdapter
import com.ema.jannik.logbook.view.ExplanationDialogSettings
import java.lang.NullPointerException
import kotlin.collections.ArrayList


class SettingFragment : Fragment(), AdapterView.OnItemSelectedListener, View.OnClickListener {
    val TAG = this::class.java.name

    companion object {
        /**
         * Name of the shared preferences.
         */
        const val SHARED_PREFERENCES: String = "sharedPreferences"

        /**
         * shared preference storage the value of spinner_notificationInterval.
         */
        const val NOTIFICATION_INTERVAL: String = "notificationInterval"

        const val LAYOUT_ONE: String = "layout1"

        const val LAYOUT_TWO: String = "layout2"

        const val LAYOUT_THREE: String = "layout3"

        const val LAYOUT_FOUR: String = "layout4"

        const val DELETE_MODIFY: String = "deleteModify"

        const val NOTIFICATION_DAY: String = "notificationDay"

        const val BLUETOOTH_CONNECTION: String = "useBluetoothConnection"

        const val BLUETOOTH_CONNECTION_NAME: String = "bluetoothConnectionName"

        /**
         * request code for the pendingIntent to copy the entries in 'a real logbook'.
         */
        const val PENDING_INTENT_REQUEST_CODE_COPY = 10
    }

    /**
     * Diese Variabel wird von den shared preferneces gestzt //TODO kommentare
     */
    private lateinit var spinnerDayValue: String    //TODO hier witer werte setzen speichern werte benutzen.
    private lateinit var spinnerNotificationValue: String
    private var switchDeleteValue: Boolean = false
    private var switchBluetoothValue: Boolean = false
    private lateinit var spinnerBluetoothValue: String
    private lateinit var spinnerLayout1Value: String
    private lateinit var spinnerLayout2Value: String
    private lateinit var spinnerLayout3Value: String
    private lateinit var spinnerLayout4Value: String

    /**
     * TRUE wenn Bluetooth eingeschaltet wird.
     */
    private var bluetoothWasDisabled: Boolean = false

    /**
     * Called to do initial creation of a fragment.  This is called after [.onAttach] and before [.onCreateView].
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);    //enable optionMenu
    }

    /**
     * Diese Funktion wird ausgelößt wenn das Fragment durch ein anderes ersetzt wird.
     * Wenn Bluetooth beim öffnen dieses Fragmentes aktiviert wurde, wird Bluetooth beim verlassen wieder deaktiviert.
     */
    override fun onPause() {
        super.onPause()

        if(bluetoothWasDisabled) {
            val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            mBluetoothAdapter.disable()

            Toast.makeText(context, R.string.toast_bluetoothDisabled, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Inflate the layout for this fragment.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "onCreateView()")
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    /**
     * Called when the fragment's activity has been created and this
     * fragment's view hierarchy instantiated.
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //enable bluetooth for settings
        Log.i(TAG, "Bluetooth adapter")
        val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (!mBluetoothAdapter.isEnabled) {
            mBluetoothAdapter.enable()

            Toast.makeText(context, R.string.toast_bluetoothEnabled, Toast.LENGTH_SHORT).show()

            Log.i(TAG, "bluetooth enabled")
            bluetoothWasDisabled = true

            val dialog = ExplanationDialogSettings(R.string.alertDialog_messageEnableBluetooth)
            dialog.show(activity!!.supportFragmentManager, "info dialog")
        }

        Log.i(TAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)

        //--set onClick Listener--
        button_setDefault.setOnClickListener(this)

        Log.i(TAG, "setSpinner")
        //--set spinner--
        setSpinnerDay()
        setSpinnerNotification()
        setSpinnerLayoutOne()
        setSpinnerLayoutTwo()
        setSpinnerLayout3()
        setSpinnerLayout4()
        setSpinnerBluetooth()

        //--load and set data--
        loadData()
        updateViews()
    }

    /**
     * Callback method to be invoked when the selection disappears from this
     * view. The selection can disappear for instance when touch is activated
     * or when the adapter becomes empty.
     *
     * @param parent The AdapterView that now contains no selected item.
     */
    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.i(TAG, "onNothingSelected")
    }

    /**
     * Callback method to be invoked when an item in this view has been selected. This callback is invoked only when
     * the newly selected position is different from the previously selected position or if there was no selected item.
     *
     * @param parent The AdapterView where the selection happened
     * @param view The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id The row id of the item that is selected
     */
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.i(TAG, "inItemSelected")
        parent!!.getItemAtPosition(position)
        Log.i(TAG, "inItemSelected finish")
    }

    /**
     * Initialize the contents of the Fragment host's standard options menu.
     */
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(
            R.menu.setting_menu,
            menu
        )    //tell system to use add note menu     //TODO share button entfernen
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * call the function saveSettings() when save in the right top corner is clicked.
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.save_setting) {
            saveSettings()
            setAlarm(getDay()!!)    //TODO
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

    /**
     * Called when button_setDefault has been clicked.
     * @param v The view that was clicked.
     */
    override fun onClick(v: View?) {
        resetSettings()
    }

    /**
     * reset the settings to default values.
     */
    private fun resetSettings() {
        Log.i(TAG, "reset settings()")
        //--init--
        val sharedPreferences = activity!!.getSharedPreferences(
            SHARED_PREFERENCES, //name of shared preferences
            MODE_PRIVATE        //just this application can change the preferences
        )
        val editor = sharedPreferences.edit()
        Log.i(TAG, "getArray")
        val layout = resources.getStringArray(R.array.spinner_layout)
        val notification = resources.getStringArray(R.array.spinner_notificationInterval)
        val day = resources.getStringArray(R.array.spinner_day)

        //--save settings--
        editor.putBoolean(BLUETOOTH_CONNECTION, false)  //TODO connection name
        editor.putString(NOTIFICATION_INTERVAL, notification[1])
        editor.putString(NOTIFICATION_DAY, day[6])
        editor.putBoolean(DELETE_MODIFY, false)
        editor.putString(LAYOUT_ONE, layout[0])
        editor.putString(LAYOUT_TWO, layout[2])
        editor.putString(LAYOUT_THREE, layout[6])
        editor.putString(LAYOUT_FOUR, layout[1])
        editor.apply()

        Toast.makeText(context, R.string.toast_settingsReset, Toast.LENGTH_SHORT).show()

        loadData()
        updateViews()
        Log.i(TAG, "resetSetting fertig")
    }

    /**
     * Set spinner 'spinner_bluetooth'.
     */
    private fun setSpinnerBluetooth() {
        val adapter = ArrayAdapter<String>(
            context!!,
            android.R.layout.simple_spinner_item,
            getListOfAllBluetoothDevices()
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_bluetooth.adapter = adapter
        spinner_bluetooth.onItemSelectedListener = this
    }

    private fun getListOfAllBluetoothDevices(): ArrayList<String>{
        val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        val pairedDevices = mBluetoothAdapter.bondedDevices

        val s = ArrayList<String>()
        for (bt in pairedDevices)
            s.add(bt.name)

        return s
    }

    /**
     * set the spinner 'spinner_notificationDay'.
     * Here you choose how often you get a notification to update your physical drive log.
     */
    private fun setSpinnerDay() {
        val adapter = ArrayAdapter.createFromResource(
            context!!,
            R.array.spinner_day,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_day.adapter = adapter
        spinner_day.onItemSelectedListener = this
    }

    /**
     * set the spinner 'spinner_notificationInterval'.
     * Here you choose how often you get a notification to update your physical drive log.
     */
    private fun setSpinnerNotification() {
        val adapter = ArrayAdapter.createFromResource(
            context!!,
            R.array.spinner_notificationInterval,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_notificationInterval.adapter = adapter
        spinner_notificationInterval.onItemSelectedListener = this
    }

    private fun setSpinnerLayoutOne() {
        val adapter = setLayoutAdapter()
        spinner_layoutOne.adapter = adapter
        spinner_layoutOne.onItemSelectedListener = this
    }

    private fun setSpinnerLayoutTwo() {
        val adapter = setLayoutAdapter()
        spinner_layoutTwo.adapter = adapter
        spinner_layoutTwo.onItemSelectedListener = this
    }

    private fun setSpinnerLayout3() {
        val adapter = setLayoutAdapter()
        spinner_layout3.adapter = adapter
        spinner_layout3.onItemSelectedListener = this
    }

    private fun setSpinnerLayout4() {
        val adapter = setLayoutAdapter()
        spinner_layout4.adapter = adapter
        spinner_layout4.onItemSelectedListener = this
    }

    /**
     * This function set the layout of the adapter for the spinners.
     */
    private fun setLayoutAdapter(): ArrayAdapter<CharSequence> {
        val adapter = ArrayAdapter.createFromResource(
            context!!,
            R.array.spinner_layout,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return adapter
    }

    /**
     * store the current settings in shared preferences.
     */
    private fun saveSettings() {
        //--init--
        val sharedPreferences = activity!!.getSharedPreferences(
            SHARED_PREFERENCES, //name of shared preferences
            MODE_PRIVATE        //just this application can change the preferences
        )
        val editor = sharedPreferences.edit()

        //--save settings--
        editor.putString(NOTIFICATION_INTERVAL, spinner_notificationInterval.selectedItem.toString())
        editor.putString(NOTIFICATION_DAY, spinner_day.selectedItem.toString())
        editor.putBoolean(DELETE_MODIFY, switch_delete.isChecked)
        editor.putString(LAYOUT_ONE, spinner_layoutOne.selectedItem.toString())
        editor.putString(LAYOUT_TWO, spinner_layoutTwo.selectedItem.toString())
        editor.putString(LAYOUT_THREE, spinner_layout3.selectedItem.toString())
        editor.putString(LAYOUT_FOUR, spinner_layout4.selectedItem.toString())

        try {   //Can't get a list of connected bluetooth devices from the system. So there is nothing we can safe.
            editor.putString(BLUETOOTH_CONNECTION_NAME, spinner_bluetooth.selectedItem.toString())
            editor.putBoolean(BLUETOOTH_CONNECTION, switch_bluetooth.isChecked)
        } catch (e: NullPointerException) {
            Toast.makeText(context, R.string.bluetoothSaveError, Toast.LENGTH_SHORT).show()
        }

        editor.apply()

        Toast.makeText(context, R.string.toast_saveSettings, Toast.LENGTH_SHORT).show()
    }

    private fun loadData() {
        val sharedPreferences = activity!!.getSharedPreferences(
            SHARED_PREFERENCES, //name of shared preferences
            MODE_PRIVATE        //just this application can change the preferences
        )
        spinnerBluetoothValue = sharedPreferences.getString(
            BLUETOOTH_CONNECTION_NAME,
            ""
        )!!
        switchBluetoothValue = sharedPreferences.getBoolean(
            BLUETOOTH_CONNECTION,
            false
        )
        spinnerNotificationValue = sharedPreferences.getString(
            NOTIFICATION_INTERVAL,    //Key
            ""     //default Value
        )!!
        spinnerDayValue = sharedPreferences.getString(
            NOTIFICATION_DAY,    //Key
            ""     //default Value
        )!!
        spinnerLayout1Value = sharedPreferences.getString(
            LAYOUT_ONE,    //Key
            ""     //default Value //TODO oder Zahl speichern
        )!!
        spinnerLayout2Value = sharedPreferences.getString(
            LAYOUT_TWO,    //Key
            ""     //default Value
        )!!
        spinnerLayout3Value = sharedPreferences.getString(
            LAYOUT_THREE,    //Key
            ""     //default Value
        )!!
        spinnerLayout4Value = sharedPreferences.getString(
            LAYOUT_FOUR,    //Key
            ""     //default Value
        )!!
        switchDeleteValue = sharedPreferences.getBoolean(
            DELETE_MODIFY,
            false
        )
    }

    /**
     * updated the view with the stored data from the shared preferences.
     * Use this function after you call loadData().
     */
    private fun updateViews() {
        spinner_day.setSelection(
            getIndex(
                spinner_day,
                spinnerDayValue
            )
        )
        spinner_notificationInterval.setSelection(
            getIndex(
                spinner_notificationInterval,
                spinnerNotificationValue
            )
        )
        spinner_layoutOne.setSelection(
            getIndex(
                spinner_layoutOne,
                spinnerLayout1Value
            )
        )
        spinner_layoutTwo.setSelection(
            getIndex(
                spinner_layoutTwo,
                spinnerLayout2Value
            )
        )
        spinner_layout3.setSelection(
            getIndex(
                spinner_layout3,
                spinnerLayout3Value
            )
        )
        spinner_layout4.setSelection(
            getIndex(
                spinner_layout4,
                spinnerLayout4Value
            )
        )
        spinner_bluetooth.setSelection(
            getIndex(
                spinner_bluetooth,
                spinnerBluetoothValue
            )
        )
        switch_bluetooth.isChecked = switchBluetoothValue
        switch_delete.isChecked = switchDeleteValue
    }

    /**
     * This function give you the position of the value in the spinner
     * @return position of the item wit the same value as the parameter value. Return 0 when not found.
     */
    private fun getIndex(spinner: Spinner, value: String): Int {
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i).toString() == value) {
                return i
            }
        }
        return 0
    }

    /**
     * set the alarm to remember the user to copy the entries into 'a real logbook'.
     * @param c
     */
    private fun setAlarm(c: Calendar) {    //TODO modify for Day
        val alarmManager: AlarmManager = activity!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlertReciever::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, PENDING_INTENT_REQUEST_CODE_COPY, intent, 0)

        val interval: Long = getInterval() ?: return    //when interval is null then do not set Alarm

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.timeInMillis, interval, pendingIntent)
    }

    /**
     *  @return gibt Intervall in milliseconds zurück.
     */
    private fun getInterval(): Long? {
        val sharedPreferences = activity!!.getSharedPreferences(
            SHARED_PREFERENCES, //name of shared preferences
            MODE_PRIVATE        //just this application can change the preferences
        )
        val setting = sharedPreferences.getString(NOTIFICATION_INTERVAL, "")
        val array = resources.getStringArray(R.array.spinner_notificationInterval)  //get array from string.xml

        var index: Int = -1

        for (i in array.indices) {
            if (setting == array[i])
                index = i
        }

        when (index) {
            0 -> return AlarmManager.INTERVAL_DAY           //einmal pro Tag
            1 -> return 7 * 24 * 60 * 60 * 1000             //einmal pro Woche
            2 -> return 30 * 24 * 60 * 60 * 1000 as Long    //einmal pro Monat      //TODO need as long
        }
        return null
    }

    /**
     *
     */
    fun getDay(): Calendar? {
        val sharedPreferences = activity!!.getSharedPreferences(
            SHARED_PREFERENCES, //name of shared preferences
            MODE_PRIVATE        //just this application can change the preferences
        )
        val setting = sharedPreferences.getString(NOTIFICATION_DAY, "")
        val array = resources.getStringArray(R.array.spinner_day)  //get array from string.xml

        var index: Int = -1

        for (i in array.indices) {
            if (setting == array[i])
                index = i
        }

        val calendar = Calendar.getInstance()

        calendar.set(Calendar.HOUR, 12)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        when (index) {
            0 -> {
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
                return calendar
            }
            1 -> {
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY)
                return calendar
            }
            2 -> {
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY)
                return calendar
            }
            3 -> {
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY)
                return calendar
            }
            4 -> {
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)
                return calendar
            }
            5 -> {
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
                return calendar
            }
            6 -> {
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
                return calendar
            }
        }
        return null
    }
}
