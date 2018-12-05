package com.ema.jannik.logbook.model

import android.app.Application
import android.location.Location
import android.os.AsyncTask
import com.ema.jannik.logbook.model.location.Geocoding

class LocationRepository(application: Application) {
    private var geocoding: Geocoding = Geocoding(application)

    /**
     * run the AsyncTask to get the address from the location
     */
    fun getAddress(location: Location){
        GetAddressAsyncTask(geocoding).execute(location)
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
    }
}