package com.ema.jannik.logbook.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

/**
 * This Repository class add an extra layer between the ViewModel and Data for abstraction purpose
 */
class Repository {
    private var driveDao: DriveDao? = null
    private var routeDao: RouteDao? = null
    private var stageDao: StageDao? = null
    private var allDrives: LiveData<List<Drive>>? = null

    constructor(application: Application){
        val database: AppDatabase = AppDatabase.getInstance(application.applicationContext)!!
        driveDao = database.driveDao()
        routeDao = database.routeDao()
        stageDao = database.stageDao()
        allDrives = driveDao?.getAll()  //TODO need other db?
    }


    fun insert(drive: Drive){
        InsertDriveAsyncTask(driveDao).execute(drive)
    }

    fun insertAll(vararg drive: Drive){

    }

    fun getAll(): LiveData<List<Drive>>?{
        return allDrives
    }

    fun delete(drive: Drive){

    }

    companion object {
        private class InsertDriveAsyncTask(private val driveDao: DriveDao?) : AsyncTask<Drive, Void, Void>(){
            override fun doInBackground(vararg params: Drive): Void? {
                driveDao!!.insert(params[0])
                return null
            }

        }
    }
}