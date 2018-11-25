package com.ema.jannik.logbook.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * This interface contains queries and other operations on the table drive.
 */
@Dao
interface DriveDao {
    /**
     * Query: "SELECT * FROM drive"
     * get all entries from the table drive
     */
    @Query("SELECT * FROM drive")
    fun getAll(): LiveData<List<Drive>>
    /**
     * insert an entry in the table drive
     */
    @Insert
    fun insert(drive: Drive)
    /**
     * insert a few elements to the table drive
     */
    @Insert
    fun insertAll(vararg drive: Drive)
    /**
     * delete an entry in the table drive
     */
    @Delete
    fun delete(drive: Drive)
}