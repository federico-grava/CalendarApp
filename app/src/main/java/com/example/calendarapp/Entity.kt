package com.example.calendarapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import java.util.*

/*@Entity
data class User(
        @PrimaryKey val uid: Int,
        @ColumnInfo(name = "first_name") val firstName: String?,
        @ColumnInfo(name = "last_name") val lastName: String?
)*/

@Entity
data class Event(
        @PrimaryKey val uid : Int,
        @ColumnInfo(name = "title") val title: String?,
        @ColumnInfo(name = "note") val note: String?,
        @ColumnInfo(name = "startDate") val startDate: String,
        @ColumnInfo(name = "endDate") val endDate: String,
        @ColumnInfo(name = "startTime") val startTime: String,
        @ColumnInfo(name = "endTime") val endTime: String
)