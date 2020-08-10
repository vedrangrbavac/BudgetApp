package com.example.budgetapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity
data class DBTransaction(
    @PrimaryKey
    val id: Int,
    var title: String,
    var date: LocalDateTime,
    val totalPrice: Double
)