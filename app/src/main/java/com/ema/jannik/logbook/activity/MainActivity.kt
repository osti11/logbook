package com.ema.jannik.logbook.activity

import android.app.DatePickerDialog
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.DialogFragment
import com.ema.jannik.logbook.R
import com.ema.jannik.logbook.fragment.*
import com.ema.jannik.logbook.helper.App.Companion.CHANNEL_UPDATE_ID
import com.ema.jannik.logbook.helper.Utils
import com.ema.jannik.logbook.model.DriveRepository
import com.ema.jannik.logbook.model.EmailRepository
import com.ema.jannik.logbook.view.DeleteAllDialog
import com.ema.jannik.logbook.view.EmailDialog
import com.ema.jannik.logbook.view.ExplanationDialogAddDrive
import com.ema.jannik.logbook.view.ExplanationDialogSettings
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.util.*

/**
 * This activity contains an burger menu an load the right fragment when chosen.
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    EmailDialog.EmailDialogListener, DatePickerDialog.OnDateSetListener {

    companion object {
        const val SHARED_PREFERENCES_FIRST_START = "firstStart"
        const val SHARED_PREFERENCES = "prefs"
    }

    private val TAG = this::class.java.name

    private var notificationManagerCompat: NotificationManagerCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.i(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i(TAG, "setNavigation")
        //--Navigation--
        setSupportActionBar(toolbar)    //set toolbar as actionbar
        nav_view.setNavigationItemSelectedListener(this)
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.manu_drawer_open, R.string.menu_drawer_close
        )
        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        Log.i(TAG, "setNotification")
        //--Notification--
        notificationManagerCompat = NotificationManagerCompat.from(this)    //can not create channels

        Log.i(TAG, "loadFragment")
        //--load Fragments--
        if (savedInstanceState == null) {    //Don't load the fragment when the device is rotated
            if (isFirstStart()) {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,  //open Fragment
                    IntroductionFragment()
                ).commit()
                nav_view.setCheckedItem(R.id.nav_introduction)
            } else {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,  //open Fragment
                    OverviewFragment()
                ).commit()
                nav_view.setCheckedItem(R.id.nav_overview)
            }

        }

        setFirstStartToFalse()

        Log.i(TAG, "onCreate finish")
    }

    /**
     * get SHARED_PREFERENCES_FIRST_START
     * @return when shared preferences not set return false
     */
    private fun isFirstStart(): Boolean {
        val pref = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        return pref.getBoolean(SHARED_PREFERENCES_FIRST_START, true)
    }

    /**
     * set shared preferences SHARED_PREFERENCES_FIRST_START to false
     */
    private fun setFirstStartToFalse() {
        val pref = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean(SHARED_PREFERENCES_FIRST_START, false)
        editor.apply()
    }

    /**
     * Don't close the activity on back press when the navigation drawer is open.
     */
    override fun onBackPressed() {
        Log.i(TAG, "onBackPressed()")
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
        Log.i(TAG, "onBackPressed() finish")
    }

    /**
     * set the menu 'overview_menu' which contains the share button in the top right corner
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overview_menu, menu)
        return true
    }

    /**
     * call sendMail() when the share button is clicked
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.share) {
            openMailDialog()
            return true
        } else if (item.itemId == R.id.delete) {
            val dialog= DeleteAllDialog()  //info dialog
            dialog.show(supportFragmentManager, "deleteAllInfo")

            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

    /**
     * result of the DataPickerFragment
     * Löscht alle Einträge die vor dem Ausgewählten Datum erstellt wurden.
     */
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Log.i(TAG, "onDataSet()")
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val driveRepository = DriveRepository(application)
        driveRepository.DeleteAllBefore(calendar)

        Toast.makeText(
            this,
            "Alle Einträge vor dem " + DateFormat.getDateInstance().format(calendar.time) + " wurden gelöscht",
            Toast.LENGTH_SHORT
        ).show()
    }

    /**
     * This function open an dialog were you can choose the layout of the mail
     * @return an int value: 0 = cancel, 1 = as list, 2 = as table to paste in excel.
     * This return value is the parameter of the function sendMail()
     */
    private fun openMailDialog() {
        val dialog = EmailDialog()  //date picker dialog
        dialog.show(supportFragmentManager, "emailDialog")
    }

    /**
     * Called when an item in the navigation menu is selected.
     * This function load the appropriate fragment.
     * @param item The selected item
     * @return true to display the item as the selected item
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_overview -> {
                title = getString(R.string.app_name)
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container, OverviewFragment()
                ).commit()
            }
            R.id.nav_introduction -> {
                title = getString(R.string.title_introduction)
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container, IntroductionFragment()
                ).commit()
            }
            R.id.nav_settings -> {
                title = getString(R.string.title_settings)
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container, SettingFragment()
                ).commit()
            }
            R.id.nav_impessum -> {
                title = getString(R.string.title_impressum)
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container, ImprintFragment()
                ).commit()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    /**
     * This function handle the result of the EmailDialog by calling the sendMail() function.
     */
    override fun onOkClickedEmailDialog(choice: Int) {
        sendMail(choice)
    }

    /**
     * This function create an intent to start the email client, to send a list of all table entries
     * @param choice when choice = 1 -> as list, choice = 2 as table
     */
    private fun sendMail(choice: Int) {
        val calander = Calendar.getInstance()
        val subject: String =
            getString(R.string.app_name) + getString(R.string.email_subject) + DateFormat.getDateInstance().format(
                calander.time
            )
        val repository: EmailRepository = EmailRepository(application)
        val drives = repository.getAll()
        var text = getString(R.string.email_text)

        if (choice == 0) {   //as list
            for (d in drives) {
                text += "\n" + getString(R.string.category) + "\t" + getString(Utils.getCategory(d.category))
                text += "\n" + getString(R.string.email_purpose) + "\t" + d.purpose
                text += "\n" + getString(R.string.email_startAddress) + "\t" + d.start?.address
                text += "\n" + getString(R.string.email_destinationAddress) + "\t" + d.destination?.address
                text += "\n" + getString(R.string.email_mileageStart) + "\t" + d.mileageStart.toString()
                text += "\n" + getString(R.string.email_mileageDestination) + "\t" + d.mileageDestination.toString()
                text += "\n" + getString(R.string.email_startTimestamp) + "\t" + DateFormat.getDateTimeInstance().format(
                    d.start_timestamp.time
                )
                text += "\n" + getString(R.string.email_destinationTimestamp) + "\t" + DateFormat.getDateTimeInstance().format(
                    d.destination_timestamp.time
                )
                text += "\n" + getString(R.string.email_duration) + "\t" + DateFormat.getTimeInstance().format(d.duration.time)
                text += "\n"
            }
        } else if (choice == 1) {   //as table
            text += String.format(
                "\n%s \t%s \t%s \t%s \t%s \t%s \t%s \t%s \t%s",
                getString(R.string.category),
                getString(R.string.email_purpose),
                getString(R.string.email_startAddress),
                getString(R.string.email_destinationAddress),
                getString(R.string.email_mileageStart),
                getString(R.string.email_mileageDestination),
                getString(R.string.email_startTimestamp),
                getString(R.string.email_destinationTimestamp),
                getString(R.string.email_duration)
            )

            for (d in drives) {
                text += "\n" +
                        d.purpose + "\t" + "\t" +
                        d.start?.address + "\t" + "\t" +
                        d.destination?.address + "\t" + "\t" +
                        d.mileageStart.toString() + "\t" + "\t" +
                        d.mileageDestination.toString() + "\t" + "\t" +
                        DateFormat.getDateTimeInstance().format(d.start_timestamp.time) + "\t" + "\t" +
                        DateFormat.getDateTimeInstance().format(d.destination_timestamp.time) + "\t" + "\t" +
                        DateFormat.getTimeInstance().format(d.duration.time)

            }
        }

        Log.i(TAG, "set Intent for email")
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)

        intent.setType("message/rfc822")    //set type as email client

        Log.i(TAG, "start Email intent")
        startActivity(
            Intent.createChooser(
                intent,
                getString(R.string.email_description)
            )
        )  //user choose between all email clients on the device
    }

    //--OnClick functions--

    /**
     * Manage the viability of the smaller floatingActionButtons when the flotingActionButton in the overview is clicked.
     */
    fun onClickFabShow(view: View) {
        var textView = findViewById<TextView>(R.id.textView_fab_add_description)
        var fab = findViewById<FloatingActionButton>(R.id.fab_add)
        changeVisibility(fab, textView)

        textView = findViewById(R.id.textView_fab_record_description)
        fab = findViewById(R.id.fab_record)
        changeVisibility(fab, textView)

        textView = findViewById(R.id.textView_fab_correctMileage_description)
        fab = findViewById(R.id.fab_correctMileage)
        changeVisibility(fab, textView)
    }

    /**
     * This function change the visibility of the passed parameters.
     * @param floatingActionButton passed a floating action button.
     * @param textView  passed the textView which contains the description of the floating action button.
     */
    private fun changeVisibility(floatingActionButton: FloatingActionButton, textView: TextView) {
        Log.i(TAG, "visibility fab:\t" + floatingActionButton.visibility)
        Log.i(TAG, "visibility tv:\t" + textView.visibility)

        if (textView.visibility == View.VISIBLE) {
            floatingActionButton.hide()
            textView.visibility= View.GONE
        } else if (textView.visibility == View.GONE) {
            floatingActionButton.show()
            textView.visibility = View.VISIBLE
        }

    }

    /**
     * this function is called when the floating action button 'fab_add' is clicked and start an AddDriveActivity.
     */
    fun onClickFabAdd(view: View) {
        val intent = Intent(this, AddDriveActivity::class.java)
        startActivity(intent)
    }

    /**
     * this function is called when the floating action button 'fab_correctMileage' is clicked and start the CorrectMileageActivity.
     */
    fun onClickFabCorrect(view: View) {
        val intent = Intent(this, CorrectMileageActivity::class.java)
        startActivity(intent)
    }

    /**
     * this function is called when the floating action button 'fab_record' is clicked and start an RecordDriveActivity.
     */
    fun onClickFabRec(view: View) {
        val intent = Intent(this, RecordDriveActivity::class.java)
        startActivity(intent)
    }

    /**
     * onClickListener for the setting fragment.
     * Open dialog with explanation to the notificationInterval settings.
     */
    fun onClickNotificationInfo(view: View) {
        Log.i(TAG, "NotificationIntervalDialogConstuctor")
        val dialog = ExplanationDialogSettings(R.string.alertDialog_messageNotification)
        Log.i(TAG, "NotificationIntervalDialogShow")
        dialog.show(supportFragmentManager, "info dialog")
    }

    /**
     * onClickListener for the setting fragment.
     * Open dialog with explanation to the deleteModify settings.
     */
    fun onClickDeleteInfo(view: View) {
        val dialog = ExplanationDialogSettings(R.string.alertDialog_messageDelete)
        dialog.show(supportFragmentManager, "info dialog")
    }

    /**
     * onClickListener for the setting fragment.
     * Open dialog with explanation to the layout settings.
     */
    fun onClickLayoutInfo(view: View) {
        val dialog = ExplanationDialogSettings(R.string.alertDialog_messageLayout)
        dialog.show(supportFragmentManager, "info dialog")
    }

    /**
     * onClickListener for the setting fragment.
     * Open dialog with explanation to the bluetooth settings.
     */
    fun onClickBluetoothInfo(view: View) {
        val dialog = ExplanationDialogSettings(R.string.alertDialog_messageBluetooth)
        dialog.show(supportFragmentManager, "info dialog")
    }
}
