package com.ema.jannik.logbook.model

import android.app.Application
import android.os.AsyncTask
import com.ema.jannik.logbook.model.database.*

class DetailsDriveRepository(application: Application) {
    private var driveDao: DriveDao
    private var routeDao: RouteDao

    init {
        val database: AppDatabase = AppDatabase.getInstance(application.applicationContext)!!
        driveDao = database.driveDao()
        routeDao = database.routeDao()
    }

    /**
     * run an async task to getById()
     */
    fun getById(id: Int): Drive? {
        return GetByIdDriveAsyncTask(driveDao).execute(id).get()
    }

    fun getRouteById(id: Int): List<Route>? {
        return GetRouteByIdAsyncTask(routeDao).execute(id).get()
    }

    companion object {
        /**
         * get drive by id async
         */
        private class GetByIdDriveAsyncTask(private val driveDao: DriveDao) : AsyncTask<Int, Void, Drive>(){
            override fun doInBackground(vararg params: Int?): Drive? {
                return driveDao.getById(params[0]!!)
            }
        }

        private class GetRouteByIdAsyncTask(private val routeDao: RouteDao) : AsyncTask<Int, Void, List<Route>>(){
            /**
             * Override this method to perform a computation on a background thread. The
             */
            override fun doInBackground(vararg params: Int?): List<Route> {
                return routeDao.getByDrId(params[0]!!)
            }
        }
    }
}