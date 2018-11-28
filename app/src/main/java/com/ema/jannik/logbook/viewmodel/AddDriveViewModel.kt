package com.ema.jannik.logbook.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ema.jannik.logbook.model.DriveRepository
import com.ema.jannik.logbook.model.database.Drive

/**
 * This VieModel provides the data for the View of the AddDriveActivity
 */
class AddDriveViewModel(application: Application): AndroidViewModel(application) {
    private val repository: DriveRepository

    init {
        repository = DriveRepository(application)
    }

    fun insert(drive: Drive) {      //TODO needs?
        repository.insert(drive)
    }

    fun delete(drive: Drive) {      //TODO edit = delete + insert ?
        repository.delete(drive)
    }
}