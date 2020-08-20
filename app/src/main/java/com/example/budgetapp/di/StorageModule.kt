package com.example.budgetapp.di

import com.example.budgetapp.data.database.storage.AuthStorage
import com.example.budgetapp.data.database.storage.TransactionsStorage
import org.koin.dsl.module

val storageModule = module {
    single { TransactionsStorage(get()) }
    single { AuthStorage(get()) }
}