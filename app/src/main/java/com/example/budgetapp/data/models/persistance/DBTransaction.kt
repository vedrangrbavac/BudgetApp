package com.example.budgetapp.data.models.persistance

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