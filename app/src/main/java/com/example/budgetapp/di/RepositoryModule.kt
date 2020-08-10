package com.example.budgetapp.di

import com.example.budgetapp.repositories.TransactionsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { TransactionsRepository(get()) }
}