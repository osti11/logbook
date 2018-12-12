package com.ema.jannik.logbook.model

import android.app.Application
import android.location.Location
import android.os.AsyncTask
import com.ema.jannik.logbook.model.location.Geocoding
import com.ema.jannik.logbook.model.location.LocationUpdates

class LocationRepository(application: Application) {
    private val geocoding = Geocoding(application)
    private val locationUpdates = LocationUpdates(application)

    /**
     * run the AsyncTask to get the address from the location
     */
    fun getAddress(location: Location){
        GetAddressAsyncTask(geocoding).execute(location)
    }

    fun getLocationUpdates(){
        GetLocationUpdatesAsyncTask(locationUpdates)
    }

    companion object {
        /**
         * run the function getAddressFromLocation as AsyncTask
         */
        private class GetAddressAsyncTask(private val geocoding: Geocoding) :AsyncTask<Location, Void, Void>(){
            override fun doInBackground(vararg params: Location?): Void? {
                geocoding.getAddressFromLocation(params[0]!!)   //ToDo error als return
                return null
            }
        }

        private class GetLocationUpdatesAsyncTask(private val locationUpdates: LocationUpdates): AsyncTask<Void, Void, Void>(){
            override fun doInBackground(vararg params: Void?): Void? {
                locationUpdates.startLocationUpdate()
                return null
            }
        }

    }
}