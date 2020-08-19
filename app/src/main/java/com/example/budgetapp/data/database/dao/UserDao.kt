package com.example.budgetapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.budgetapp.common.base.BaseDao
import com.example.budgetapp.data.models.persistance.DBUser

@Dao
interface UserDao: BaseDao<DBUser>{
    @Query("Select * from DBUser limit 1")
    fun isLoggedIn(): DBUser?

    @Query("Select * from DBUser limit 1")
    fun getUser(): DBUser

    @Query("Select * from DBUser limit 1")
    fun getUserLiveData(): LiveData<DBUser?>

    @Query("Select * from DBUser limit 1")
    suspend fun getUserAsync(): DBUser

}