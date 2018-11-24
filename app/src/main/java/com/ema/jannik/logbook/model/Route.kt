package com.ema.jannik.logbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Created by Jannik on 11/24/2018.
 * This class represent the table route and contains geo locations.
 * These are used for Polylinies to show the route on maps
 * @constructor contains all properties except the id
 */

@Entity(tableName = "route", foreignKeys = arrayOf(
    ForeignKey(entity = Drive::class,
        parentColumns = arrayOf("id"),      //TODO must I use db or class name
        childColumns = arrayOf("driveId"),  //TODO must I use db or class name
        onDelete = ForeignKey.CASCADE)
))
data class Route(
    /**
     * reference the column ro_drid which is a foreign key to drive.dr_id
     */
    @ColumnInfo(name = "ro_drId") var driveId: Int,
    /**
     * represent the column ro_latitude which contains a latitude to represent the location
     */
    @ColumnInfo(name = "ro_latitude") var latitude: Double,
    /**
     * represent the column ro_latitude which contains a longitude to represent the location
     */
    @ColumnInfo(name = "ro_longitude") var  longitude: Double
) {
    /**
     * represent the column ro_id, primary key, auto generated
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ro_id") var id: Int = 0
}