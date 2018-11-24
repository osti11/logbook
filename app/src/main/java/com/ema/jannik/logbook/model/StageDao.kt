package com.ema.jannik.logbook.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * This interface contains queries and other operations on the table stage.
 */
@Dao
interface StageDao {
    @Query("SELECT * FROM stage")
    fun getAll(): List<Stage>

    @Insert
    fun insert(stage: Stage)

    @Insert
    fun insertAll(vararg stage: Stage)

    @Delete
    fun delete(stage: Stage)
}