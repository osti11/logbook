package com.ema.jannik.logbook.model.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.util.*

/**
 * Created by Jannik on 11/24/2018.
 * represent the table "drive" in the database.
 * This table contains the purpose for the drive, start, destination and the mileage at start and arrival.
 * @constructor contains all properties except the id
 * @property purpose represent the column dr_purpose, purpose of the drive
 * @property duration represent the column dr_duration ehich contains the duration of the journey
 * @property mileageStart represent the column dr_mileage_start, mileage of the car at the start of the journey
 * @property mileageDestination represent the column dr_mileage_destination, mileage of the car at the end of the journey
 * @property category represent the category. Values: 0 = uncategorized, 1 = private, 2 = work, 3 = way to work/home
 * @property id represent the column dr_id, Primary Key, auto increment
 * @property start represent the column dr_start, contains the Location at the start of the journey
 * @property destination represent the column dr_destination, contains the Location at the end of the journey
 * @property distance Entfernung in Kilometer zwischen Start- und Zielpunkt.
 */
@Entity(tableName = "drive")
data class Drive(
    @ColumnInfo(name = "dr_purpose") var purpose: String,   //TODO wenn leer
    @ColumnInfo(name = "dr_duration") var duration: Calendar,       //TODO richtig ge castet?
    @ColumnInfo(name= "dr_start_timestamp") var start_timestamp: Calendar,
    @ColumnInfo(name= "dr_destination_timestamp") var destination_timestamp: Calendar,
    @ColumnInfo(name = "dr_mileage_start") var mileageStart: Double,
    @ColumnInfo(name = "dr_mileage_destination") var mileageDestination: Double,
    @ColumnInfo(name = "dr_distance") var distance: Double,
    @ColumnInfo(name = "dr_category") var category: Int,
    @Embedded(prefix = "dr_start_") var start: Stage,
    @Embedded(prefix = "dr_destination_") var destination: Stage
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "dr_id")
    var id: Int = 0
}