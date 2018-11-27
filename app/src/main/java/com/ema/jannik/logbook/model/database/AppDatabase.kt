package com.ema.jannik.logbook.model.database

import android.content.Context
import androidx.room.*

/**
 * This abstract class represent the Database which contains the tables drive, route and stage.
 */
@Database(entities = [Drive::class, Route::class], version = 1)
@TypeConverters(Converts::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun driveDao(): DriveDao
    abstract fun routeDao(): RouteDao

    companion object {
        private var instance: AppDatabase? = null

        /**
         * Singleton Pattern, because each RoomDatabase instance is fairly expensive.
         * Create an instance of the RoomDatabase if the instance is null
         */
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null){
                synchronized(AppDatabase::class){
                    instance = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "logbook")
                        .fallbackToDestructiveMigration()
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