package com.ema.jannik.logbook.Model

import android.arch.persistence.room.Database

/**
 * Created by Jannik on 11/24/2018.
 */

@Database(entities = arrayOf(Drive::class), version = 1)
abstract class DriveDatabase {
    abstract fun driveDao(): DriveDao
}