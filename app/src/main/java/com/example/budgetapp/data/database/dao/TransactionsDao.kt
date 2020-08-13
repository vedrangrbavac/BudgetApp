package com.example.budgetapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.budgetapp.common.base.BaseDao
import com.example.budgetapp.data.models.persistance.DBTransaction

@Dao
interface TransactionsDao: BaseDao<DBTransaction> {

    @Query("Select * from DBTransaction")
    fun getTransactionsLiveData() : LiveData<List<DBTransaction>>

    @Query("Select * from DBTransaction")
    suspend fun getTransactionsAsync() : List<DBTransaction>

    @Query("Select * from DBTransaction ORDER BY date")
    suspend fun getTransactionsAsyncSortedByDate() : List<DBTransaction>

    @Query("Select * from DBTransaction ORDER BY contents")
    suspend fun getTransactionsAsyncSortedByContents() : List<DBTransaction>

    @Query("Select * from DBTransaction ORDER BY category")
    suspend fun getTransactionsAsyncSortedByCategory() : List<DBTransaction>

    @Query("Select * from DBTransaction ORDER BY totalPrice")
    suspend fun getTransactionsAsyncSortedBySpend() : List<DBTransaction>

}