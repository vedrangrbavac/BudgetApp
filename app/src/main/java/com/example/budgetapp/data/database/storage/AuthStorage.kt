package com.example.budgetapp.data.database.storage

import androidx.lifecycle.LiveData
import com.example.budgetapp.data.database.dao.UserDao
import com.example.budgetapp.data.models.persistance.DBUser

class AuthStorage(private val userDao: UserDao) {

    fun isLoggedIn(): DBUser? {
        return when (userDao.isLoggedIn() != null) {
            true -> userDao.isLoggedIn()
            false -> null
        }
    }

    suspend fun saveUser(user: DBUser) {
        userDao.insertModel(user)
    }

    fun getUser(): DBUser {
        return userDao.getUser()
    }

    fun getUserLiveData(): LiveData<DBUser?> = userDao.getUserLiveData()

    fun logout() {
        userDao.deleteAllFromUser()
    }
}