package com.example.budgetapp.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.budgetapp.data.database.storage.TransactionsStorage
import com.example.budgetapp.data.models.persistance.DBTransaction

class TransactionsRepository(private val storage: TransactionsStorage){


    val transactions: MutableLiveData<List<DBTransaction>?> = MutableLiveData(storage.getTransactionsLiveData().value)

    suspend fun refreshTransactions(){
        this.transactions.postValue(storage.getTransactionsAsync())
    }

    suspend fun saveTransaction(model: DBTransaction){
        storage.save(model)
    }
}