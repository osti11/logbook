package com.ema.jannik.logbook.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ema.jannik.logbook.model.DriveRepository
import com.ema.jannik.logbook.model.database.Drive

/**
 * This VieModel provides the data for the View of the MainActivity.
 */
class DriveViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DriveRepository
    val allDrives: LiveData<List<Drive>>

    init {
        repository = DriveRepository(application)
        allDrives = repository.getAll()
    }


    fun insert(drive: Drive) {      //TODO needs?
        repository.insert(drive)
    }

    fun delete(drive: Drive) {
        repository.delete(drive)
    }

    fun getAll(): LiveData<List<Drive>> {
        return allDrives
    }
}