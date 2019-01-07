package com.ema.jannik.logbook.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * This interface contains queries and other operations on the table route.
 */
@Dao
interface RouteDao {
    @Insert
    fun insert(route: Route)

    @Delete
    fun delete(route: Route)

    /**
     * Get rout by FOREIGN KEY ro_drid
     */
    @Query("SELECT * FROM route WHERE ro_drId = (:id)")
    fun getByDrId(id: Int): List<Route>
}