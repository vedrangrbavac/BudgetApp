package com.example.budgetapp.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {
    private val defaultErrorHandler = CoroutineExceptionHandler { _, throwable ->

    }

    fun <T> suspendCall(
        errorHandler: CoroutineExceptionHandler = defaultErrorHandler,
        block: suspend () -> T
    ) {
        viewModelScope.launch(errorHandler) {
            block()
        }
    }

}