package com.example.budgetapp

import com.example.budgetapp.repositories.AuthRepository
import com.example.budgetapp.viewmodels.AuthViewModel
import io.mockk.mockk
import org.junit.Test
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get


class AuthViewModelTest : TestCase(), KoinTest {

    override val testCaseMocksModule: Module
        get() = module {
            single<AuthViewModel> { mockk(relaxed = true) }
            single<AuthRepository> { mockk(relaxed = true) }
        }
    private val authViewModel = AuthViewModel(get())

    @Test
    fun isValidEmailTest() {
        assert(!authViewModel.isValidEmail("wrongemail#f"))
        assert(authViewModel.isValidEmail("test2@email.com"))
        assert(!authViewModel.isValidEmail(""))

    }
}