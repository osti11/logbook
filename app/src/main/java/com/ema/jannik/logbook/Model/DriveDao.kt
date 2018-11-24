package com.ema.jannik.logbook.Model

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Created by Jannik on 11/24/2018.
 */
interface DriveDao {
    @Query("SELECT * FROM drive")
    fun getAll(): List<Drive>

    @Insert
    fun insert(drive: Drive)

    @Insert
    fun insertAll(vararg drive: Drive)

    @Delete
    fun delete(drive: Drive)
}