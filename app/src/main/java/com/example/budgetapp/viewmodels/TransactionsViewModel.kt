package com.example.budgetapp.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.navigation.findNavController
import com.example.budgetapp.R
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

    fun onClick(view: View) {
        when (view.id) {
            R.id.fabAddTransaction -> {
                view.findNavController().navigate(R.id.action_transactionsFragment_to_addTransactionFragment)
            }

        }
    }
}


