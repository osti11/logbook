package com.ema.jannik.logbook

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.ema.jannik.logbook.fragment.AddDriveFragment
import com.ema.jannik.logbook.fragment.OverviewFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)    //set toolbar as actionbar

        nav_view.setNavigationItemSelectedListener(this)

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.manu_drawer_open, R.string.menu_drawer_close
        )
        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        if (savedInstanceState == null) {    //Don't kload the fragment when the device is rotated
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,  //open Fragment
                OverviewFragment()
            ).commit()
            nav_view.setCheckedItem(R.id.nav_overview)
        }
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
                R.id.fragment_container,
                OverviewFragment()
            ).commit()
            R.id.nav_introduction -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                AddDriveFragment()
            ).commit()
            //R.id.nav_settings -> setSupportActionBar()
            //R.id.nav_impessum ->
            //Hier action wenn man klickt un sich kein fragm,ent öffnet
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
