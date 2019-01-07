package com.ema.jannik.logbook.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Created by Jannik on 11/24/2018.
 * This class represent the table route and contains geo locations.
 * These are used for Polylinies to show the route on maps.
 * This table has no entry when the journey is added manuel.
 * @constructor contains all properties except the id
 * @property driveId reference the column ro_drid which is a foreign key to drive.dr_id
 * @property counter order of routes referencong to drive.
 * @property latitude represent the column ro_latitude which contains a latitude to represent the location
 * @property longitude represent the column ro_latitude which contains a longitude to represent the location
 * @property id represent the column ro_id, primary key, auto generated
 */

@Entity(
    tableName = "route", foreignKeys = arrayOf(
        ForeignKey(
            entity = Drive::class,
            parentColumns = arrayOf("dr_id"),      //TODO must I use db or class name
            childColumns = arrayOf("ro_drId"),  //TODO must I use db or class name
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class Route(
    @ColumnInfo(name = "ro_drId") var driveId: Int,
    @ColumnInfo(name = "ro_latitude") var latitude: Double,
    @ColumnInfo(name = "ro_longitude") var longitude: Double
    //,@ColumnInfo(name = "ro_counter") var counter: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ro_id")
    var id: Int = 0
}