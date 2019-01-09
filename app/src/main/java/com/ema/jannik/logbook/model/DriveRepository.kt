package com.ema.jannik.logbook.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.ema.jannik.logbook.model.database.AppDatabase
import com.ema.jannik.logbook.model.database.Drive
import com.ema.jannik.logbook.model.database.DriveDao
import java.util.*

/**
 * This Repository class add an extra layer between the ViewModel and Data for abstraction purpose.
 * This class is used from the overview fragment to feed the DriveViewModel
 * Do NOT create an instace of this class on the main thread.
 */
class DriveRepository(application: Application) {
    private var driveDao: DriveDao
    private var allDrives: LiveData<List<Drive>>

    init {
        val database: AppDatabase = AppDatabase.getInstance(application.applicationContext)!!
        driveDao = database.driveDao()
        allDrives = driveDao.getAll()
    }

    fun delete(drive: Drive){
        DeleteDriveAsyncTask(driveDao).execute(drive)
    }

    /**
     * run an async task to insert an entry into table "drive"
     */
    fun insert(drive: Drive){
        InsertDriveAsyncTask(driveDao).execute(drive)
    }

    fun getAll(): LiveData<List<Drive>>{
        return allDrives
    }

    /**
     * run riveDao.getAllAfter() as AsyncTask
     * @param calendar drive.destinationTimestamp
     */
    fun getAllAfter(calendar: Calendar) : List<Drive> {
        return GetAllAfterAsyncTask(driveDao).execute(calendar.timeInMillis).get()
    }

    /**
     * use Calendar.time
     */
    fun DeleteAllBefore(calendar: Calendar){
        DeleteAllBeforeAsyncTask(driveDao).execute(calendar.timeInMillis)
    }

    /**
     * run the asyncTask UpdateDriveAsyncTask.
     */
    fun update(drive: Drive) {
        UpdateDriveAsyncTask(driveDao).execute(drive)
    }

    companion object {
        private class DeleteDriveAsyncTask(private val driveDao: DriveDao) : AsyncTask<Drive, Void, Void>(){
            override fun doInBackground(vararg params: Drive): Void? {
                driveDao.delete(params[0])
                return null
            }
        }
        private class InsertDriveAsyncTask(private val driveDao: DriveDao) : AsyncTask<Drive, Void, Void>(){
            override fun doInBackground(vararg params: Drive): Void? {
                driveDao.insert(params[0])
                return null
            }
        }
        private class DeleteAllBeforeAsyncTask(private  val driveDao: DriveDao) : AsyncTask<Long, Void, Void>(){
            /**
             *
             */
            override fun doInBackground(vararg params: Long?): Void? {
                driveDao.deleteAllBefore(params[0]!!)
                return null
            }
        }

        private class GetAllAfterAsyncTask(private val driveDao: DriveDao) : AsyncTask<Long, Void, List<Drive>>(){
            /**
             * Override this method to perform a computation on a background thread. The
             * specified parameters are the parameters passed to [.execute]
             * by the caller of this task.
             */
            override fun doInBackground(vararg params: Long?): List<Drive> {
                return driveDao.getAllAfter(params[0]!!)
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