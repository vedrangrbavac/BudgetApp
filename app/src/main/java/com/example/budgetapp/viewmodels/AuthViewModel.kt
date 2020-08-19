package com.example.budgetapp.viewmodels

import android.view.View
import androidx.navigation.findNavController
import com.example.budgetapp.R
import com.example.budgetapp.common.base.BaseViewModel
import com.example.budgetapp.data.models.persistance.DBUser
import com.example.budgetapp.repositories.AuthRepository
import kotlinx.coroutines.delay
import java.lang.Exception

class AuthViewModel(private val repository: AuthRepository) : BaseViewModel() {

    val isUserLoggedIn: Boolean get() = repository.isUserLoggedIn()

    fun saveUser(user : DBUser){
        suspendCall {
            repository.saveUser(user)
        }
    }


    fun decideStartingScreen(view: View){
        suspendCall {
            delay(1000)
            if(repository.isUserLoggedIn()){
                try {
                    view.findNavController().navigate(R.id.action_splashScreenFragment_to_mainActivity)
                }catch (e: Exception){
                    repository.logout()
                    view.findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
                }
            }else{
                view.findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
            }
        }
    }


    fun onClick(view: View) {
        when (view.id) {
            R.id.btnLogin -> {

            }

        }
    }

}