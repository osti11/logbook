package com.ema.jannik.logbook

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.GravityCompat
import com.ema.jannik.logbook.App.Companion.CHANNEL_UPDATEDRIVE_ID
import com.ema.jannik.logbook.fragment.ImprintFragment
import com.ema.jannik.logbook.fragment.OverviewFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_overview.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var notificationManagerCompat: NotificationManagerCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //--Navigation--
        setSupportActionBar(toolbar)    //set toolbar as actionbar
        nav_view.setNavigationItemSelectedListener(this)
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.manu_drawer_open, R.string.menu_drawer_close
        )
        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        //--Notification--
        notificationManagerCompat = NotificationManagerCompat.from(this)    //can not create channels

        //--load Fragments--
        if (savedInstanceState == null) {    //Don't load the fragment when the device is rotated
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,  //open Fragment
                OverviewFragment()
            ).commit()
            nav_view.setCheckedItem(R.id.nav_overview)
        }
    }

    /**
     * Send notification on channel
     */
    fun sendOnChannel(/*view: View*/) {     //Set the setting her for api lower then Oreo
        //wwenn man auf die notifiaction klickt
        val activityIntent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0)

        //request code -> Cancel oder update
        //flag was passiert bei recreate

        val notification: Notification = NotificationCompat.Builder(
            this,
            CHANNEL_UPDATEDRIVE_ID
        )
            .setSmallIcon(R.drawable.ic_warning)
            .setContentTitle("Eintrag unvollständig")   //TODO in string.xml aulaggern
            .setContentText("BItte vervollständige deinen Eintrag")
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(Notification.DEFAULT_ALL)
            .setContentIntent(contentIntent)
            .build()

        notificationManagerCompat!!.notify(1, notification)
        //more notifaction same time -> diffrent id
        //update or cancel them pass same id
    }


    override fun onBackPressed() {  //Pack press when navigationDrawer is open don't leav the activity
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    /**
     * Called when an item in the navigation menu is selected.
     * @param item The selected item
     * @return true to display the item as the selected item
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_overview -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container, OverviewFragment()
            ).commit()
            R.id.nav_introduction -> {
                val intent = Intent(this, LocationActivity::class.java)//TODO Einfürung view
                startActivity(intent)
            }
            R.id.nav_settings -> sendOnChannel()
            R.id.nav_impessum -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container, ImprintFragment()
            ).commit()
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    //--OnClick functions--
    fun onClickFabShow(view: View) {
        changeVisibility(fab_add, textView_fab_add_description)
        changeVisibility(fab_record, textView_fab_record_description)
    }

    private fun changeVisibility(floatingActionButton: FloatingActionButton, textView: TextView) {
        if (floatingActionButton.visibility == View.VISIBLE) {
            floatingActionButton.hide()
            textView.visibility = View.GONE
        } else if (floatingActionButton.visibility == View.GONE) {
            floatingActionButton.show()
            textView.visibility = View.VISIBLE
        }
    }

    fun onClickFabAdd(view: View) {
        val intent = Intent(this, AddDriveActivity::class.java)
        startActivity(intent)         //TODO start activity for result
    }

    fun onClickFabRec(view: View) {
        val intent = Intent(this, RecordDriveActivity::class.java)
        startActivity(intent)         //TODO start activity for result
    }
}
