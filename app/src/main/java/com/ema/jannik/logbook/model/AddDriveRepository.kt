package com.ema.jannik.logbook.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.ema.jannik.logbook.model.database.AppDatabase
import com.ema.jannik.logbook.model.database.Drive
import com.ema.jannik.logbook.model.database.DriveDao

class AddDriveRepository(application: Application) {
    private var driveDao: DriveDao

    init {
        val database: AppDatabase = AppDatabase.getInstance(application.applicationContext)!!
        driveDao = database.driveDao()
    }

    /**
     * run an async task to insert an entry into table "drive"
     */
    fun insert(drive: Drive){
        InsertDriveAsyncTask(driveDao).execute(drive)
    }

    companion object {
        private class InsertDriveAsyncTask(private val driveDao: DriveDao) : AsyncTask<Drive, Void, Void>(){
            override fun doInBackground(vararg params: Drive): Void? {
                driveDao.insert(params[0])
                return null
            }
        }
    }
}