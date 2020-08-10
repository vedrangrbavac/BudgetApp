package com.example.budgetapp.repositories

import androidx.lifecycle.MutableLiveData
import com.example.budgetapp.data.database.storage.TransactionsStorage
import com.example.budgetapp.data.models.persistance.DBTransaction

class TransactionsRepository(private val storage: TransactionsStorage){


    val transactions: MutableLiveData<List<DBTransaction>?> = MutableLiveData(storage.getTransactionsLiveData().value)
}