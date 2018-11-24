package com.ema.jannik.logbook.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Jannik on 11/24/2018.
 */
@Entity
data class Stage (@ColumnInfo(name = "st_latitude") var latitude: Double,
                  @ColumnInfo(name = "st_longitude") var  longitude: Double){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "st_id") var id: Int = 0
}