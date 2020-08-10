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
    fun getTransactionsAsync() : List<DBTransaction>
}