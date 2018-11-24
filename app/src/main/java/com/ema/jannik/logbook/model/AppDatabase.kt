package com.ema.jannik.logbook.model

import androidx.room.Database
import com.ema.jannik.logbook.model.RouteDao
import com.ema.jannik.logbook.model.StageDao

/**
 * Created by Jannik on 11/24/2018.
 */

@Database(entities = arrayOf(Drive::class), version = 1)
abstract class AppDatabase {
    abstract fun driveDao(): DriveDao
    abstract fun routeDao(): RouteDao
    abstract fun  stageDao(): StageDao
}