package com.ema.jannik.logbook

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.ema.jannik.logbook.model.DetailsDriveRepository
import com.ema.jannik.logbook.model.database.Drive
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_details_drive.*

class DetailsDriveActivity : AppCompatActivity(), OnMapReadyCallback{

    val TAG = "DetailsDriveActivity"

    companion object {
        /**
         * to identify the extra from the intent.
         */
        const val EXTRA__ID = "id"

        /**
         * to identify the error dialog
         */
        const val ERROR_DIALOG_REQUEST = 9001
    }

    /**
     * represent the map
     */
    private lateinit var map: GoogleMap

    /**
     * represent the DetailsDriveRepository to get the entry from the db.
     */
    private lateinit var detailsDriveRepository: DetailsDriveRepository

    /**
     * entry from db.
     */
    private var drive: Drive? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_drive)

        //--set ActionBar--
        this.supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)    //set back button in the left top corner
        title = getString(R.string.menu_details)  //Text for action bar

        if (isServiceAvailable()){
            initMap()
            initRepository()
        }
    }

    /**
     * init the DetailsDriveRepository and get drive by id.
     */
    private fun initRepository() {
        detailsDriveRepository = DetailsDriveRepository(application)
        val id: Int = intent.getIntExtra(EXTRA__ID, -1)
        if(id >= 0){
            drive = detailsDriveRepository.getById(id)
            setTextViews()
        } else {
            Toast.makeText(this, getString(R.string.toast_notFound), Toast.LENGTH_LONG).show()
            finish()
        }

    }

    /**
     * set the text view.
     */
    private fun setTextViews(){

        Log.i(TAG, "setTextViews()")

        val category = Utils.getCategory(drive!!.category)

        if(category != 0){  //category == 0 -> not found
            textView_category.text = getString(category)
        }

        textView_purpose.text = drive!!.purpose
        textView_startAddress.text = drive!!.start.address
        textView_destinationAddress.text = drive!!.destination.address
    }

    /**
     * set the menu on top.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_drive_menu, menu)    //tell system to use add note menu
        return true //when true Menu will shown
    }

    /**
     * call the function saveDrive() when save in the right top corner is clicked.
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.edit_drive) {
            //TODO open edit
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

    /**
     * Add markers for the start address and the destination.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        Toast.makeText(this, "map is ready", Toast.LENGTH_SHORT).show() //TODO for development
        map = googleMap

        if (drive != null){
            val start = drive!!.start
            val startLatLng = LatLng(start.latitude, start.longitude)
            val destination = drive!!.destination
            val destinationLatLng = LatLng(destination.latitude, destination.longitude)

            //add marker for start address
            map.addMarker(MarkerOptions().position(startLatLng).title(getString(R.string.editText_startAddress) + ": " + start.address))
            map.moveCamera(CameraUpdateFactory.newLatLng(startLatLng))
            //add marker for destination address
            map.addMarker(MarkerOptions().position(destinationLatLng).title(getString(R.string.editText_destinationAddress) + ": " + destination.address))
        }
    }

    /**
     * This function return true when google play service is available.
     */
    private fun isServiceAvailable():Boolean{
        val available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(applicationContext)

        if (available == ConnectionResult.SUCCESS){
            return true     //service is available
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){   //fixable error
            val dialog: Dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, available, ERROR_DIALOG_REQUEST)
            dialog.show()
        } else {
            Toast.makeText(this, "you can make no requests", Toast.LENGTH_SHORT).show() //TODO Toast die map funktioniert nicht richtig, in string.xml
        }
        return false
    }

    /**
     * initialize the map fragment
     */
    private fun initMap(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this@DetailsDriveActivity)
    }
}
