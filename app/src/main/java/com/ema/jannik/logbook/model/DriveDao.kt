package com.ema.jannik.logbook.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by Jannik on 11/24/2018.
 * This interface contains queries and other operations on the table drive.
 */
@Dao
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