package com.ema.jannik.logbook.model.database

import androidx.room.TypeConverter
import java.sql.Date
import java.sql.Time
import java.util.*

/**
 * This class converts unstorable data types into data types that can be stored in the database
 */
class Converts {
    @TypeConverter
    fun fromTimestamp(value: Long?): Calendar? {    //TODO stimmt das
        val cal = Calendar.getInstance()
        cal.timeInMillis = value!!
        return cal
    }

    @TypeConverter
    fun CalendarToTimestamp(calendar: Calendar?): Long? {
        return calendar?.timeInMillis
    }

    /*
    companion object {
        /**
         * convert Timestamp into Date, to save it in the database
         */
        @JvmStatic
        @TypeConverter

        fun fromTimestamp(value: Long?): Date? {
            return if (value == null) null else Date(value)
        }

        /**
         * convert Date back into timestamp
         */
        @JvmStatic
        @TypeConverter
        fun dateToTimestamp(date: Date?): Long? {
            return date?.time
        }
    }
    */
}