package com.example.budgetapp.di

import com.example.budgetapp.repositories.ResourceRepository
import com.example.budgetapp.repositories.TransactionsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { TransactionsRepository(get()) }
    single { ResourceRepository(androidContext()) }
}