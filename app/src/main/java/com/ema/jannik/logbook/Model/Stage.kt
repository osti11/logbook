package com.ema.jannik.logbook.Model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Jannik on 11/24/2018.
 */
@Entity
data class Stage (@ColumnInfo(name = "st_latitude") var latitude: Double,
                  @ColumnInfo(name = "st_longitude") var  longitude: Double){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "st_id") var id: Int = 0
}