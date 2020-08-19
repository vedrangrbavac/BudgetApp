package com.example.budgetapp.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetapp.repositories.ResourceRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseViewModel : ViewModel(), KoinComponent {
    private val defaultErrorHandler = CoroutineExceptionHandler { _, throwable ->

    }

    val res: ResourceRepository by inject()

    fun <T> suspendCall(
        errorHandler: CoroutineExceptionHandler = defaultErrorHandler,
        block: suspend () -> T
    ) {
        viewModelScope.launch(errorHandler) {
            block()
        }
    }

}