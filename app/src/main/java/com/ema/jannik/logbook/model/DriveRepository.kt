package com.ema.jannik.logbook.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.ema.jannik.logbook.model.database.AppDatabase
import com.ema.jannik.logbook.model.database.Drive
import com.ema.jannik.logbook.model.database.DriveDao

/**
 * This Repository class add an extra layer between the ViewModel and Data for abstraction purpose
 */
class DriveRepository(application: Application) {
    private var driveDao: DriveDao
    private var allDrives: LiveData<List<Drive>>

    init {
        val database: AppDatabase = AppDatabase.getInstance(application.applicationContext)!!
        driveDao = database.driveDao()
        allDrives = driveDao.getAll()  //TODO need other db?
    }

    /**
     * run an async task to insert an entry into table "drive"
     */
    fun insert(drive: Drive){
        InsertDriveAsyncTask(driveDao).execute(drive)
    }

    fun delete(drive: Drive){
        DeleteDriveAsyncTask(driveDao).execute(drive)
    }

    fun getAll(): LiveData<List<Drive>>{
        return allDrives
    }

    companion object {
        private class InsertDriveAsyncTask(private val driveDao: DriveDao) : AsyncTask<Drive, Void, Void>(){
            override fun doInBackground(vararg params: Drive): Void? {
                driveDao.insert(params[0])
                return null
            }
        }

        private class DeleteDriveAsyncTask(private val driveDao: DriveDao) : AsyncTask<Drive, Void, Void>(){
            override fun doInBackground(vararg params: Drive): Void? {
                driveDao.delete(params[0])
                return null
            }
        }
    }
}