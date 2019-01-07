package com.ema.jannik.logbook.model.location

import android.Manifest
import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*

class LocationUpdates(val application: Application) {

    private lateinit var locations: List<Location>


    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    fun startLocationUpdate() {
        buildLocationRequest()
        buildLocationCallback()

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(application)

        requestLocationUpdates()

        //} else {    //TODO check if GPS is enabled
        //    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        //}
    }

    private fun requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                application.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                application.applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //ActivityCompat.requestPermissions(, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
            return
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
    }

    private fun buildLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                for (location: Location in p0!!.locations) {
                    locations += location
                    Log.i(TAG, "get Location: " + location.latitude.toString() + " / " + location.longitude.toString())
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

    /**
     * This function stop the location updates and return a list with all locations since the requestLocationUpdates() was call.
     */
    fun stopLocationUpdates(): List<Location> {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback) //TODO reicht das zum stoppen?

        return locations
    }
}