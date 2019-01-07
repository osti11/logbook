package com.ema.jannik.logbook.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.sql.Time
import java.sql.Timestamp
import java.util.*

/**
 * This interface contains queries and other operations on the table drive.
 */
@Dao
interface DriveDao {
    /**
     * Get all entries from the table drive ordered by dr_start_timestamp.
     */
    @Query("SELECT * FROM drive ORDER BY dr_start_timestamp DESC")
    fun getAll(): LiveData<List<Drive>>

    /**
     * Query: "SELECT * FROM drive"
     * Get all entries from the table drive as normal list to create the email.
     */
    @Query("SELECT * FROM drive")
    fun getAllForEmail(): List<Drive>

    /**
     * Query: "SELECT * FROM drive ORDER BY dr_id DESC LIMIT 1"
     * Get last entry.
     */
    @Query("SELECT * FROM drive ORDER BY dr_id DESC LIMIT 1")
    fun getLastDrive() : Drive

    /**
     * Insert drive in db and return drive to get the id
     */
    @Insert
    fun insertResult(drive: Drive): Long

    /**
     * Query: SELECT * FROM drive where dr_id = id
     * Get entry by id.
     */
    @Query("SELECT * FROM drive where dr_id = (:id) " )
    fun getById(id: Int): Drive

    /**
     * get all entries after the passed timestamp.
     * This function is used to modifie the entries when an entry is deleted and the mileage of the newer entries must be modified.
     * @param time pass drive.destination_timeStamp
     * @return a list of all found entries
     */
    @Query("SELECT * FROM drive where dr_start_timestamp > (:time)")
    fun getAllAfter(time: Long): List<Drive>

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

    /**
     * update an entry in the table drive
     */
    @Update
    fun update(drive: Drive)

    @Query("DELETE FROM DRIVE WHERE dr_start_timestamp < (:time)")
    fun deleteAllBefore(time: Long)
}