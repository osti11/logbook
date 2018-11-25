package com.ema.jannik.logbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This class represent the table stage and contains an geo position and the address.
 * Which is used to save the start and destination of a journey.
 */
@Entity
data class Stage (@ColumnInfo(name = "st_latitude") var latitude: Double,
                  @ColumnInfo(name = "st_longitude") var  longitude: Double,
                  @ColumnInfo(name = "st_address") var address: String){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "st_id") var id: Int = 0
}