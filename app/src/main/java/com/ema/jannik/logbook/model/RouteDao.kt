package com.ema.jannik.logbook.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ema.jannik.logbook.model.Route

/**
 * This interface contains queries and other operations on the table route.
 */
@Dao
interface RouteDao {
    @Query("SELECT * FROM route")
    fun getAll(): List<Route>

    @Insert
    fun insert(route: Route)

    @Insert
    fun insertAll(vararg route: Route)

    @Delete
    fun delete(route: Route)
}