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
    fun insert(drive: Drive): Int{
        return InsertDriveAsyncTask(driveDao).execute(drive).get().toInt()
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

    fun getLastDrive(): Drive{  //TODO was wenn kein EIntrag exestiert
        return GetLastDriveAsyncTask(driveDao).execute().get()
    }

    companion object {
        private class InsertDriveAsyncTask(private val driveDao: DriveDao) : AsyncTask<Drive, Void, Long>(){
            override fun doInBackground(vararg params: Drive): Long{
                return driveDao.insertResult(params[0])
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

        private class  GetLastDriveAsyncTask(private val driveDao: DriveDao) : AsyncTask<Void, Void, Drive>(){
            /**
             * Override this method to perform a computation on a background thread. The
             */
            override fun doInBackground(vararg params: Void?): Drive {
                return driveDao.getLastDrive()
            }
        }
    }
}