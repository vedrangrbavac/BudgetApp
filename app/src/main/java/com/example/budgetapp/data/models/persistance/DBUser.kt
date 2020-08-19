package com.example.budgetapp.data.models.persistance

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBUser(
    @PrimaryKey
    val email: String
)