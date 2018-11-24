package com.ema.jannik.logbook.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by Jannik on 11/24/2018.
 * represent the table "drive" in the database.
 * This table contains the purpose for the drive, start, destination and the mileage at start and arrival.
 * @constructor contains all properties except the id
 */
@Entity(tableName = "drive")
data class Drive (
        /**
         * represent the column dr_purpose, purpose of the drive
         */
        @ColumnInfo(name = "dr_purpose") var purpose: String,
        /**
         * represent the column dr_mileage_start, mileage of the car at the start of the journey
         */
        @ColumnInfo(name = "dr_mileage_start") var mileageStart: Double,
        /**
         * represent the column dr_mileage_destination, mileage of the car at the end of the journey
         */
        @ColumnInfo(name = "dr_mileage_destination") var mileageDestination: Double,
        /**
         * represent the column dr_start, contains the Location at the start of the journey
         */
        @ColumnInfo(name = "dr_start") @Embedded var start: Stage,
        /**
         * represent the column dr_destination, contains the Location at the end of the journey
         */
        @ColumnInfo(name = "dr_destination") @Embedded var destination: Stage
        //TODO duration of the journey
        ) {
        /**
         * represent the column dr_id, Primary Key, auto increment
         */
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "dr_id") var id: Int = 0
}