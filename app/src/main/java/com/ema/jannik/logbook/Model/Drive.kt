package com.ema.jannik.logbook.Model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Embedded
import java.util.*

/**
 * Created by Jannik on 11/24/2018.
 * represent the table "drive" in the database.
 * This contains the purpose for the drive and the mileage at start and arrival.
 */
@Entity(tableName = "drive")
data class Drive (
        @ColumnInfo(name = "dr_purpose") var purpose: String,
        @ColumnInfo(name = "dr_mileage_start") var mileageStart: Double,
        @ColumnInfo(name = "dr_mileage_destination") var mileageDestination: Double,
        @ColumnInfo(name = "dr_start") @Embedded var start: Stage,
        @ColumnInfo(name = "dr_destination") @Embedded var destination: Stage
) {
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "dr_id") var id: Int = 0
}