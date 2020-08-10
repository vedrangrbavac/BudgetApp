package com.example.budgetapp.di

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.budgetapp.data.database.AppDb
import com.example.budgetapp.data.models.persistance.DBTransaction
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import java.text.DateFormat
import java.time.LocalDateTime
import java.util.*


val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDb::class.java, "transaction_database")
            .allowMainThreadQueries().addCallback(object : RoomDatabase.Callback() {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    GlobalScope.launch {
                        prepopulateDb(get())
                    }
                }
            }).fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDb>().transactionsDao }
}

@RequiresApi(Build.VERSION_CODES.O)
fun prepopulateDb(db: AppDb) {
    val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMAN)
    val data =
        listOf(
            DBTransaction( "Groceries from Lidl", LocalDateTime.now(), "Food", 157.31),
            DBTransaction( "Coffee with friends", LocalDateTime.now(), "Social lide", 11.51),
            DBTransaction( "Groceries from Konzum", LocalDateTime.now(), "Food", 67.11),
            DBTransaction( "New PC", LocalDateTime.now(), "Job", 2578.24)
        )

    GlobalScope.launch {
        db.transactionsDao.insertModels(data)
    }
}

