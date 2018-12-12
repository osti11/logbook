package com.ema.jannik.logbook.model

import android.app.Application
import android.os.AsyncTask
import com.ema.jannik.logbook.model.database.AppDatabase
import com.ema.jannik.logbook.model.database.Drive
import com.ema.jannik.logbook.model.database.DriveDao

class DetailsDriveRepository(application: Application) {
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

    companion object {
        /**
         * get drive by id async
         */
        private class GetByIdDriveAsyncTask(private val driveDao: DriveDao) : AsyncTask<Int, Void, Drive>(){
            override fun doInBackground(vararg params: Int?): Drive? {
                return driveDao.getById(params[0]!!)
            }
        }

    }
}