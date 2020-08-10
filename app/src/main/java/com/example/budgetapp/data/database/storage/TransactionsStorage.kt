package com.example.budgetapp.data.database.storage

import androidx.lifecycle.LiveData
import com.example.budgetapp.data.database.dao.TransactionsDao
import com.example.budgetapp.data.models.persistance.DBTransaction

class TransactionsStorage(private val dao: TransactionsDao) {

    fun getTransactionsLiveData() : LiveData<List<DBTransaction>>{
        return dao.getTransactionsLiveData()
    }
}