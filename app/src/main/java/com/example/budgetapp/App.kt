package com.example.budgetapp

import android.app.Application
import com.example.budgetapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@App)
            loadKoinModules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    databaseModule,
                    storageModule
                )
            )
        }
    }
}