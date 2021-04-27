package com.example.calendarapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Event::class], version = 1, exportSchema = false)
abstract class DatabaseAndroid : RoomDatabase() {
    //abstract fun userDao(): UserDao
    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseAndroid? = null

        fun getDatabase(context: Context): DatabaseAndroid {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseAndroid::class.java,
                        "database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}