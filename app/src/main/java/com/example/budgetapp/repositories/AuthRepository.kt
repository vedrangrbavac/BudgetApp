package com.example.budgetapp.repositories

import com.example.budgetapp.data.database.storage.AuthStorage
import com.example.budgetapp.data.models.persistance.DBUser
import com.google.firebase.auth.FirebaseAuth

class AuthRepository(private val authStorage: AuthStorage) {

    var fbAuth = FirebaseAuth.getInstance()

    val user get() = authStorage.getUserLiveData()

    fun isUserLoggedIn(): Boolean{
        return authStorage.isLoggedIn() != null
    }

    suspend fun saveUser(user: DBUser){
        authStorage.saveUser(user)
    }

    fun logout() {
        fbAuth.signOut()
        authStorage.logout()
    }


}