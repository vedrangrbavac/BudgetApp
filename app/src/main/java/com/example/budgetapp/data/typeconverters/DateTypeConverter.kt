package com.example.budgetapp.data.typeconverters

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

class DateTypeConverter {

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun dateToTimestamp(value: LocalDate?): Long? {
        return value?.atStartOfDay(ZoneId.systemDefault())?.toEpochSecond()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {

        return value?.let {
            return Instant.ofEpochSecond(it).atZone(ZoneId.systemDefault()).toLocalDate()
        }

    }
}
