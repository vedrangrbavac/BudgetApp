package com.example.budgetapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.budgetapp.data.database.dao.TransactionsDao
import com.example.budgetapp.data.models.persistance.DBTransaction
import com.example.budgetapp.data.typeconverters.DateTypeConverter

@Database(
    entities = [DBTransaction::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    DateTypeConverter::class
)
abstract class AppDb : RoomDatabase() {
    abstract val transactionsDao: TransactionsDao
}