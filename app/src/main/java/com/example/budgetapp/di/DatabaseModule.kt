package com.example.budgetapp.di

import androidx.room.Room
import com.example.budgetapp.data.database.AppDb
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDb::class.java, "news_database")
            .allowMainThreadQueries().fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDb>().transactionsDao }
}

