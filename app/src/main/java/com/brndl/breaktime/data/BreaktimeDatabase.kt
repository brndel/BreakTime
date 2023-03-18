package com.brndl.breaktime.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.brndl.breaktime.data.exercise.Exercise
import com.brndl.breaktime.data.exercise.ExerciseDao

@Database(entities = [Exercise::class], version = 1, exportSchema = false)
abstract class BreaktimeDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao

    companion object {

        private var instance: BreaktimeDatabase? = null

        fun getDatabase(context: Context): BreaktimeDatabase {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BreaktimeDatabase::class.java,
                    "breaktime_db"
                ).build()

                BreaktimeDatabase.instance = instance

                instance
            }
        }
    }
}