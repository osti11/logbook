package com.ema.jannik.logbook.model.database

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
     * Get all entries from the table drive.
     */
    @Query("SELECT * FROM drive")
    fun getAll(): LiveData<List<Drive>>

    /**
     * Query: SELECT * FROM drive where dr_id = id
     * Get entry by id.
     */
    @Query("SELECT * FROM drive where dr_id = (:id) " )
    fun getById(id: Int): Drive

    /**
     * insert an entry in the table drive
     */
    @Insert
    fun insert(drive: Drive)
    /**
     * delete an entry in the table drive
     */
    @Delete
    fun delete(drive: Drive)
}