package com.ema.jannik.logbook.activity


import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.ema.jannik.logbook.R
import com.ema.jannik.logbook.model.LocationRepository
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_location.*


class LocationActivity : AppCompatActivity() {
    //GEOCODING
    private lateinit var locationRepository: LocationRepository


    companion object {
        const val REQUEST_CODE = 1000   //to identify request
    }

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback

    private fun buildLocationCallback() {
        locationCallback = object: LocationCallback(){
            override fun onLocationResult(p0: LocationResult?) {
                for (location :Location in p0!!.locations){
                    textView.append(location.latitude.toString() + " / " + location.longitude.toString() +"\n")


                    locationRepository.getAddress(location)
                    //TODO here test geocoding
                    //textView.append(geocoding.getAddressFromLocation(location))
                }
            }
        }
    }

    private fun buildLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 3000
        locationRequest.smallestDisplacement = 10.0F

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty()){
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    }
                    else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {

                    }
                }
            }
        }
    }

    //Geocoding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        //check Permission runtime
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE
            )
        }
        else {
            //if granted

            //GEOCODING
            locationRepository = LocationRepository(application)


            buildLocationRequest()
            buildLocationCallback()

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

            //set Event for Button
            button_start.setOnClickListener(View.OnClickListener {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)  {
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_CODE
                    )
                    return@OnClickListener
                }
                fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
            })

            button_end.setOnClickListener(View.OnClickListener {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)  {
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_CODE
                    )
                    return@OnClickListener
                }
                fusedLocationProviderClient.removeLocationUpdates(locationCallback)
            })
        }
    }
}