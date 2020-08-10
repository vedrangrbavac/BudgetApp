package com.example.budgetapp.data.models.persistance

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity
data class DBTransaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val date: LocalDateTime,
    val category: String,
    val totalPrice: Double
) {
    constructor(
        title: String,
        date: LocalDateTime,
        category: String,
        totalPrice: Double
    ) : this(0, title, date, category, totalPrice)
}