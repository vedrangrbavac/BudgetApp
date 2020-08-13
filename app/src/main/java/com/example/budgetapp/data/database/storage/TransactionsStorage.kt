package com.example.budgetapp.data.database.storage

import androidx.lifecycle.LiveData
import com.example.budgetapp.data.database.dao.TransactionsDao
import com.example.budgetapp.data.models.persistance.DBTransaction

class TransactionsStorage(private val dao: TransactionsDao) {

    fun getTransactionsLiveData() : LiveData<List<DBTransaction>>{
        return dao.getTransactionsLiveData()
    }

    suspend fun getTransactionsAsync(): List<DBTransaction>{
        return dao.getTransactionsAsync()
    }

    suspend fun getTransactionsAsyncSortedByDate(): List<DBTransaction>{
        return dao.getTransactionsAsyncSortedByDate()
    }
    suspend fun getTransactionsAsyncSortedByContents(): List<DBTransaction>{
        return dao.getTransactionsAsyncSortedByContents()
    }

    suspend fun getTransactionsAsyncSortedByCategory(): List<DBTransaction>{
        return dao.getTransactionsAsyncSortedByCategory()
    }

    suspend fun getTransactionsAsyncSortedBySpend(): List<DBTransaction>{
        return dao.getTransactionsAsyncSortedBySpend()
    }


    suspend fun save(model: DBTransaction) {
        dao.insertModel(model)
    }
}