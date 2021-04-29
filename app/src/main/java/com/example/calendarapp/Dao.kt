package com.example.calendarapp

import androidx.room.*

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg event: Event)

    @Query("SELECT * FROM event ORDER BY event_id")
    suspend fun getAll(): MutableList<Event>

    @Query("SELECT MAX(event_id) FROM event")
    suspend fun getMaxId(): Int

    @Delete
    suspend fun delete(event: Event)
}