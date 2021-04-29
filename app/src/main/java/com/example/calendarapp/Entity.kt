package com.example.calendarapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat

@Entity
data class Event(
        @PrimaryKey val event_id : Int,
        @ColumnInfo(name = "title") val title: String?,
        @ColumnInfo(name = "note") val note: String?,
        @ColumnInfo(name = "startDate") val startDate: String,
        @ColumnInfo(name = "endDate") val endDate: String,
        @ColumnInfo(name = "startTime") val startTime: String,
        @ColumnInfo(name = "endTime") val endTime: String
) : Comparable<Event>{
    override fun compareTo(other: Event) : Int{
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val timeFormat = SimpleDateFormat("kk:mm")

        if(dateFormat.parse(this.startDate) < dateFormat.parse(other.startDate))
            return -1
        if(dateFormat.parse(this.startDate) > dateFormat.parse(other.startDate))
            return 1
        if(timeFormat.parse(this.startTime) < timeFormat.parse(other.startTime))
            return -1
        if(timeFormat.parse(this.startTime) > timeFormat.parse(other.startTime))
            return 1

        return 0
    }
}