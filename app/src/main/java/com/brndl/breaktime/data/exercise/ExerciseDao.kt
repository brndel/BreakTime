package com.brndl.breaktime.data.exercise

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Query("Select * FROM exercise")
    fun getAll(): Flow<List<Exercise>>

    @Query("Select * FROM exercise WHERE id = :id")
    suspend fun getById(id: Int): Exercise?

    @Upsert
    suspend fun upsert(exercise: Exercise)

    @Delete
    suspend fun delete(exercise: Exercise)
}