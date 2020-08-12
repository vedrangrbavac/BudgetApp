package com.example.budgetapp.data.models.persistance

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime


@Entity
data class DBTransaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val contents: String,
    val date: LocalDate,
    val category: String,
    val totalPrice: Double
) {
    constructor(
        contents: String,
        date: LocalDate,
        category: String,
        totalPrice: Double
    ) : this(0, contents, date, category, totalPrice)
}