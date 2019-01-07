package com.ema.jannik.logbook.activity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.ema.jannik.logbook.R
import com.ema.jannik.logbook.helper.Utils
import com.ema.jannik.logbook.model.DetailsDriveRepository
import com.ema.jannik.logbook.model.database.Drive
import com.ema.jannik.logbook.model.database.Route
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.android.synthetic.main.activity_details_drive.*
import java.text.DateFormat

class DetailsDriveActivity : AppCompatActivity(), OnMapReadyCallback {

    val TAG = "DetailsDriveActivity"

    companion object {
        /**
         * to identify the extra from the intent.
         */
        const val EXTRA_ID = "id"

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

    /**
     *
     */
    private var routes: List<Route>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_drive)

        //--set ActionBar--
        this.supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)    //set back button in the left top corner
        title = getString(R.string.menu_details)  //Text for action bar

        if (isServiceAvailable()) {
            initMap()
            initRepository()
        }
    }

    /**
     * init the DetailsDriveRepository and get drive by id.
     */
    private fun initRepository() {
        detailsDriveRepository = DetailsDriveRepository(application)
        val id: Int = intent.getIntExtra(EXTRA_ID, -1)
        if (id >= 0) {
            drive = detailsDriveRepository.getById(id)
            setTextViews()

            routes = detailsDriveRepository.getRouteById(id)
            Log.i(TAG, "gerRouteByID")
        } else {
            Toast.makeText(this, getString(R.string.toast_notFound), Toast.LENGTH_LONG).show()
            finish()
        }

    }

    /**
     * set the text view.
     */
    private fun setTextViews() {

        Log.i(TAG, "setTextViews()")

        val category = Utils.getCategory(drive!!.category)
        val imageRessource = Utils.getCategoryDrawableId(drive!!.category)

        if (category != 0 && imageRessource != 0) {  //category == 0 -> not found
            textView_category.text = getString(category)
            imageView_category.setImageResource(imageRessource)
        }

        textView_purpose.text = drive!!.purpose
        textView_startAddress.text = drive!!.start.address
        textView_destinationAddress.text = drive!!.destination.address
        textView_startTime.text = DateFormat.getDateTimeInstance().format(drive!!.start_timestamp.time)
        textView_duration.text = DateFormat.getTimeInstance().format(drive!!.duration.time)
        textView_endTime.text = DateFormat.getDateTimeInstance().format(drive!!.destination_timestamp.time)
        textView_mileageStart.text = String.format("%.2f km", drive!!.mileageStart)//TODO einheit
        textView_mileageDestination.text = String.format("%.2f km", drive!!.mileageDestination)//TODO einheit
        textView_distance.text = String.format("%.2f km", drive!!.distance)//TODO einheit
    }

    /**
     * set the menu on top.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_drive_menu, menu)    //tell system to use add note menu
        return true //when true Menu will shown
    }

    /**
     * start the EditDriveActivity when the edit button in the right top corner is clicked.
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.edit_drive) {
            val intent = Intent(this, EditDriveActivity::class.java)
            intent.putExtra(EditDriveActivity.EXTRA_ID, drive!!.id)
            startActivity(intent)
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

        if (drive != null) {
            val start = drive!!.start
            val startLatLng = LatLng(start.latitude, start.longitude)
            val destination = drive!!.destination
            val destinationLatLng = LatLng(destination.latitude, destination.longitude)

            //add marker for start address
            map.addMarker(MarkerOptions().position(startLatLng).title(getString(R.string.editText_startAddress) + ": " + start.address))
            map.moveCamera(CameraUpdateFactory.newLatLng(startLatLng))
            //add marker for destination address
            map.addMarker(MarkerOptions().position(destinationLatLng).title(getString(R.string.editText_destinationAddress) + ": " + destination.address))

            //add polyline when route exists
            if (routes != null) {
                val locations = mutableListOf<LatLng>()
                routes!!.forEach {
                    locations.add(LatLng(it.latitude, it.longitude))
                }

                val options = PolylineOptions().width(8f)
                    .color(ContextCompat.getColor(applicationContext, R.color.colorPrimaryDark))

                locations.forEach {
                    options.add(it)
                }
                map.addPolyline(options)
            }
        }
    }

    /**
     * This function return true when google play service is available.
     */
    private fun isServiceAvailable(): Boolean {
        val available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(applicationContext)

        if (available == ConnectionResult.SUCCESS) {
            return true     //service is available
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {   //fixable error
            val dialog: Dialog = GoogleApiAvailability.getInstance().getErrorDialog(
                this, available,
                ERROR_DIALOG_REQUEST
            )
            dialog.show()
        } else {
            Toast.makeText(this, "you can make no requests", Toast.LENGTH_SHORT)
                .show() //TODO Toast die map funktioniert nicht richtig, in string.xml
        }
        return false
    }

    /**
     * initialize the map fragment
     */
    private fun initMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this@DetailsDriveActivity)
    }
}
