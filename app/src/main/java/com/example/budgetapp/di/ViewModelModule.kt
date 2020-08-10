package com.example.budgetapp.di

import com.example.budgetapp.viewmodels.TransactionsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { TransactionsViewModel(get()) }
}