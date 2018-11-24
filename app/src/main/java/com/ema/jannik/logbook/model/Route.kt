package com.ema.jannik.logbook.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Created by Jannik on 11/24/2018.
 */

@Entity(tableName = "route", foreignKeys = arrayOf(
    ForeignKey(entity = Drive::class,
        parentColumns = arrayOf("id"),      //TODO must I use db or class name
        childColumns = arrayOf("driveId"),  //TODO must I use db or class name
        onDelete = ForeignKey.CASCADE)
))
data class Route(
    @ColumnInfo(name = "ro_drId") var driveId: Int,
    @ColumnInfo(name = "ro_latitude") var latitude: Double,
    @ColumnInfo(name = "ro_longitude") var  longitude: Double
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ro_id") var id: Int = 0
}