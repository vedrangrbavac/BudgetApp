package com.example.budgetapp.repositories

import androidx.lifecycle.MutableLiveData
import com.example.budgetapp.data.database.storage.TransactionsStorage
import com.example.budgetapp.data.models.persistance.DBTransaction

class TransactionsRepository(private val storage: TransactionsStorage) {


    val transactions: MutableLiveData<List<DBTransaction>?> =
        MutableLiveData(storage.getTransactionsLiveData().value)

    suspend fun refreshTransactions() {
        this.transactions.postValue(storage.getTransactionsAsync())
    }

    suspend fun saveTransaction(model: DBTransaction) {
        storage.save(model)
    }

    suspend fun getTransactionsAsyncSortedByDate() {
        this.transactions.postValue(storage.getTransactionsAsyncSortedByDate())
    }

    suspend fun getTransactionsAsyncSortedByContents() {
        this.transactions.postValue(storage.getTransactionsAsyncSortedByContents())
    }

    suspend fun getTransactionsAsyncSortedByCategory() {
        this.transactions.postValue(storage.getTransactionsAsyncSortedByCategory())
    }

    suspend fun getTransactionsAsyncSortedBySpend() {
        this.transactions.postValue(storage.getTransactionsAsyncSortedBySpend())
    }
}