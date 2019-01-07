package com.ema.jannik.logbook.fragment

import android.content.Context.MODE_PRIVATE
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.ema.jannik.logbook.R
import kotlinx.android.synthetic.main.fragment_setting.*


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
        const val NOTIFICATIN: String = "notificationInterval"

        /**
         * shared preferences speichert den Zustand von switch_purpose.
         * Dieser legt fest es eine Benachrichtigung gibt wenn 'Zweck der Fahrt' nicht angegeben wird.
         */
        const val PURPOSE: String = "purpose"

        const val LAYOUT_ONE: String = "layout1"

        const val LAYOUT_TWO: String = "layout2"

        const val LAYOUT_THREE: String = "layout3"

        const val LAYOUT_FOUR: String = "layout4"
    }

    /**
     * Diese Variabel wird von den shared preferneces gestzt //TODO kommentare
     */
    private lateinit var spinnerNotificationValue: String
    private var switchPurposeValue: Boolean = false
    private lateinit var spinnerLayout1Value: String
    private lateinit var spinnerLayout2Value: String
    private lateinit var spinnerLayout3Value: String
    private lateinit var spinnerLayout4Value: String

    /**
     * Called to do initial creation of a fragment.  This is called after [.onAttach] and before [.onCreateView].
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);    //enable optionMenu
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
        Log.i(TAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)

        //--set onClick Listener--
        button_setDefault.setOnClickListener(this)

        Log.i(TAG, "setSpinner")
        //--set spinner--
        setSpinnerNotification()
        Log.i(TAG, "onActivityCreated finish")
        setSpinnerLayoutOne()
        setSpinnerLayoutTwo()
        setSpinnerLayout3()
        setSpinnerLayout4()

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
        inflater!!.inflate(R.menu.setting_menu, menu)    //tell system to use add note menu     //TODO share button entfernen
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * call the function saveSettings() when save in the right top corner is clicked.
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.save_setting) {
            saveSettings()
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

        //--save settings--
        editor.putString(NOTIFICATIN, notification[1])
        editor.putBoolean(PURPOSE, false)
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
        editor.putString(NOTIFICATIN, spinner_notificationInterval.selectedItem.toString())
        editor.putBoolean(PURPOSE, switch_purpose.isChecked)
        editor.putString(LAYOUT_ONE, spinner_layoutOne.selectedItem.toString())
        editor.putString(LAYOUT_TWO, spinner_layoutTwo.selectedItem.toString())
        editor.putString(LAYOUT_THREE, spinner_layout3.selectedItem.toString())
        editor.putString(LAYOUT_FOUR, spinner_layout4.selectedItem.toString())

        editor.apply()

        Toast.makeText(context, R.string.toast_saveSettings, Toast.LENGTH_SHORT).show()
    }

    private fun loadData() {
        val sharedPreferences = activity!!.getSharedPreferences(
            SHARED_PREFERENCES, //name of shared preferences
            MODE_PRIVATE        //just this application can change the preferences
        )
        spinnerNotificationValue = sharedPreferences.getString(
            NOTIFICATIN,    //Key
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
        switchPurposeValue = sharedPreferences.getBoolean(
            PURPOSE,
            false
        )
    }

    /**
     * updated the view with the stored data from the shared preferences.
     * Use this function after you call loadData().
     */
    private fun updateViews() {
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
        switch_purpose.isChecked = switchPurposeValue
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
}
