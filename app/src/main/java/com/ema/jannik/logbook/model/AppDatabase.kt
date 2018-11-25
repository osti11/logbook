package com.ema.jannik.logbook.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ema.jannik.logbook.model.RouteDao
import com.ema.jannik.logbook.model.StageDao

/**
 * Created by Jannik on 11/24/2018.
 */

@Database(entities = [Drive::class, Route::class, Stage::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun driveDao(): DriveDao
    abstract fun routeDao(): RouteDao
    abstract fun  stageDao(): StageDao

    companion object {
        private var instance: AppDatabase? = null

        /**
         * Singleton Pattern, because each RoomDatabase instance is fairly expensive.
         * create an instance of the RoomDatabase if the instance is null
         */
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null){
                synchronized(AppDatabase::class){
                    instance = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "weather")
                        .build()
                }
            }
            return instance
        }

        /**
         * set the RoomDatabase instance to null
         */
        fun destroyInstance(){
            instance = null
        }
    }
}