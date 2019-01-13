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

    /**
     * @exception NullPointerException Wenn kein EIntrag in der Datenbank vorhanden ist.
     */
    fun getLastDrive(): Drive{
        return GetLastDriveAsyncTask(driveDao).execute().get()
    }

    companion object {
        private class InsertDriveAsyncTask(private val driveDao: DriveDao) : AsyncTask<Drive, Void, Void>(){
            override fun doInBackground(vararg params: Drive): Void? {
                driveDao.insert(params[0])
                return null
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