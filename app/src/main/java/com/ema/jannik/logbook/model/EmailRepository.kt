package com.ema.jannik.logbook.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.ema.jannik.logbook.model.database.AppDatabase
import com.ema.jannik.logbook.model.database.Drive
import com.ema.jannik.logbook.model.database.DriveDao

/**
 * This class is a layer between the email export functionality and the DriveDao
 */
class EmailRepository(application: Application) {
    private var driveDao: DriveDao

    init {
        val database: AppDatabase = AppDatabase.getInstance(application.applicationContext)!!
        driveDao = database.driveDao()
    }

    /**
     * this function return a list of all entries in the dr_drive table in the database.
     */
    fun getAll(): List<Drive> {
        return GetAllAsyncTask(driveDao).execute().get()
    }

    companion object {
        private class GetAllAsyncTask(private val driveDao: DriveDao) : AsyncTask<Void, Void, List<Drive>>() {
            override fun doInBackground(vararg params: Void?): List<Drive> {
                return driveDao.getAllForEmail()
            }
        }

    }
}