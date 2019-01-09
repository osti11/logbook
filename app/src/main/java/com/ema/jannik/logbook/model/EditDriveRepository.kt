package com.ema.jannik.logbook.model

import android.app.Application
import android.os.AsyncTask
import com.ema.jannik.logbook.model.database.AppDatabase
import com.ema.jannik.logbook.model.database.Drive
import com.ema.jannik.logbook.model.database.DriveDao
import java.util.*

class EditDriveRepository(application: Application){
    private var driveDao: DriveDao

    init {
        val database: AppDatabase = AppDatabase.getInstance(application.applicationContext)!!
        driveDao = database.driveDao()
    }

    /**
     * run an async task to getById()
     */
    fun getById(id: Int): Drive? {
        return GetByIdDriveAsyncTask(driveDao).execute(id).get()
    }

    /**
     * run the asyncTask UpdateDriveAsyncTask.
     */
    fun update(drive: Drive) {
        UpdateDriveAsyncTask(driveDao).execute(drive)
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

        /**
         * Update an drive entry async.
         */
        private class UpdateDriveAsyncTask(private val driveDao: DriveDao) : AsyncTask<Drive, Void, Void>(){
            override fun doInBackground(vararg params: Drive): Void? {
                driveDao.update(params[0])
                return null
            }
        }
    }
}