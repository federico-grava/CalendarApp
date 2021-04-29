package com.example.calendarapp

import androidx.room.*

/*@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg user: User)

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    suspend fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}*/

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg event: Event)

    @Query("SELECT * FROM event ORDER BY event_id")
    suspend fun getAll(): MutableList<Event>

    @Query("SELECT MAX(event_id) FROM event")
    suspend fun getMaxId(): Int

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //suspend fun insertAll(vararg event: Event)

    @Delete
    suspend fun delete(event: Event)
}