package com.ema.jannik.logbook.model.location

import android.app.Application
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import android.view.View
import android.widget.TextView
import com.ema.jannik.logbook.R.id.textView
import com.ema.jannik.logbook.model.location.Geocoding.Constants.PACKAGE_NAME
import java.io.IOException
import java.util.*

class Geocoding(application: Application) {
    object Constants {
        const val SUCCESS_RESULT = 0
        const val FAILURE_RESULT = 1
        const val PACKAGE_NAME = "com.ema.jannik.locationbackground"
        const val RECEIVER = "$PACKAGE_NAME.RECEIVER"
        const val RESULT_DATA_KEY = "$PACKAGE_NAME.RESULT_DATA_KEY"
        const val LOCATION_DATA_EXTRA = "$PACKAGE_NAME.LOCATION_DATA_EXTRA"
    }

    private var addresses: List<Address> = emptyList()
    private val geocoder = Geocoder(application.applicationContext, Locale.getDefault())


    fun getAddressFromLocation(location: Location) {
        try {
            addresses = geocoder.getFromLocation(
                location.latitude,
                location.longitude,
                1
            )
            Log.i("address", addresses.toString())
        } catch (ioException: IOException) {
            /*
            // Catch network or other I/O problems.
            errorMessage = Settings.Global.getString(R.string.service_not_available)
            */
        } catch (illegalArgumentException: IllegalArgumentException) {
            /*
            // Catch invalid latitude or longitude values.
            errorMessage = Settings.Global.getString(R.string.invalid_lat_long_used)
            */
        }

        // Handle case where no address was found.
        if (addresses.isEmpty()) {
            /*
            if (errorMessage.isEmpty()) {
                errorMessage = Settings.Global.getString(R.string.no_address_found)
            }
            deliverResultToReceiver(Constants.FAILURE_RESULT, errorMessage)
            */
        } else {
            /*
            val address = addresses[0]
            // Fetch the address lines using getAddressLine,
            // join them, and send them to the thread.
            val addressFragments = with(address) {
                (0..maxAddressLineIndex).map { getAddressLine(it) }
            }
            deliverResultToReceiver(
                Constants.SUCCESS_RESULT,
                addressFragments.joinToString(separator = "\n")
            )
            */
        }
    }
}