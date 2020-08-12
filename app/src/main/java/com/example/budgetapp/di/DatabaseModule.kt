package com.example.budgetapp.di

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.budgetapp.data.database.AppDb
import com.example.budgetapp.data.models.persistance.DBTransaction
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import java.text.DateFormat
import java.time.LocalDate
import java.util.*


val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDb::class.java, "transaction_database")
            .allowMainThreadQueries().fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDb>().transactionsDao }
    /**
     * Adding data before on installation of app, can be useful when we want to test something, but
     * dont have any data to test.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun prepopulateDb(db: AppDb) {
        val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMAN)
        val data =
            listOf(
                DBTransaction("Groceries from Lidl", LocalDate.now(), "Food", 157.31),
                DBTransaction("Coffee with friends", LocalDate.now(), "Social lide", 11.51),
                DBTransaction("Groceries from Konzum", LocalDate.now(), "Food", 67.11),
                DBTransaction("New PC", LocalDate.now(), "Job", 2578.24)
            )

        GlobalScope.launch {
            db.transactionsDao.insertModels(data)

        }
    }
}


