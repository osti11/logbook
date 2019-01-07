package com.ema.jannik.logbook.model

import android.app.Application
import android.location.Location
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.ema.jannik.logbook.model.database.*
import com.ema.jannik.logbook.model.location.Geocoding

/**
 * this repository is used to store the data in the db after the LocationUpdateService is finish
 */
class RecDriveRepository(application: Application) {
    private val driveDao: DriveDao
    private val routeDao: RouteDao
    private val geocoding: Geocoding

    init {
        //geocoder
        geocoding = Geocoding(application)
        //room
        val database: AppDatabase = AppDatabase.getInstance(application.applicationContext)!!
        driveDao = database.driveDao()
        routeDao = database.routeDao()
    }

    /**
     * run an async task to insert an entry into table "drive"
     */
    fun insert(drive: Drive){
        InsertDriveAsyncTask(driveDao).execute(drive)
    }

    fun insert(route: Route){
        InsertRouteAsyncTask(routeDao).execute(route)
    }

    /**
     * run GetAddressAsyncTask()
     */
    fun getAddress(location: Location) :String{
        return  GetAddressAsyncTask(geocoding).execute(location).get()
    }

    companion object {
        private class InsertDriveAsyncTask(private val driveDao: DriveDao) : AsyncTask<Drive, Void, Void>(){
            override fun doInBackground(vararg params: Drive): Void? {
                driveDao.insert(params[0])
                return null
            }
        }

        private class InsertRouteAsyncTask(private val routeDao: RouteDao) : AsyncTask<Route, Void, Void>(){
            override fun doInBackground(vararg params: Route): Void? {
                routeDao.insert(params[0])
                return null
            }
        }

        /**
         * run the function getAddressFromLocation as AsyncTask
         */
        private class GetAddressAsyncTask(private val geocoding: Geocoding) :AsyncTask<Location, Void, String>(){
            override fun doInBackground(vararg params: Location?): String{
                return geocoding.getAddressFromLocation(params[0]!!)   //ToDo error als return
            }
        }
    }
}