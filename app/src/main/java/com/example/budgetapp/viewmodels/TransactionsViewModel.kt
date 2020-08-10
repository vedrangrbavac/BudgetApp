package com.example.budgetapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.budgetapp.common.base.BaseViewModel
import com.example.budgetapp.data.models.persistance.DBTransaction
import com.example.budgetapp.repositories.TransactionsRepository

class TransactionsViewModel(private val repository: TransactionsRepository) : BaseViewModel() {

    val transactionsLiveData: LiveData<List<DBTransaction>?> get() = repository.transactions


    fun refreshTransactions() {
        suspendCall {
            repository.refreshTransactions()
        }
    }

    val filteredData = MediatorLiveData<List<DBTransaction>>().apply {
        listOf(
            transactionsLiveData
        ).forEach { it ->
            addSource(it) {
                val filteredValue = transactionsLiveData.value
                Log.d("FilteredValue", transactionsLiveData.value.toString())
                value = filteredValue
            }
        }
    }

    fun saveTransaction(dbTransaction: DBTransaction) {
        suspendCall {
            repository.saveTransaction(dbTransaction)
        }
    }
}


