package com.example.budgetapp.repositories

import com.example.budgetapp.data.database.storage.AuthStorage
import com.example.budgetapp.data.models.persistance.DBUser

class AuthRepository(private val authStorage: AuthStorage) {

    val user get() = authStorage.getUserLiveData()

    fun isUserLoggedIn(): Boolean{
        return authStorage.isLoggedIn() != null
    }

    suspend fun saveUser(user: DBUser){
        authStorage.saveUser(user)
    }

    fun logout() {
        authStorage.logout()
    }


}