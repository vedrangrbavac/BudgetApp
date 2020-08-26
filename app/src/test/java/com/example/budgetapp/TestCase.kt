package com.example.budgetapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.test.KoinTest

abstract class TestCase: KoinTest {

    @JvmField @Rule
    val instantTaskExRule = InstantTaskExecutorRule()

    abstract val testCaseMocksModule: Module

    open fun before() {}
    open fun after() {}

    init {
        startKoin {
            androidContext(mockk())
            loadKoinModules(testCaseMocksModule)
        }
    }

    @Before
    fun setUp() {
        before()
    }

    @After
    fun tearDown() {
        stopKoin()
        after()
    }

}