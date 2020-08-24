package com.example.budgetapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.budgetapp.R
import com.example.budgetapp.common.extensions.snackbar
import com.example.budgetapp.data.models.persistance.DBUser
import com.example.budgetapp.viewmodels.AuthViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_registration.*
import org.koin.android.ext.android.inject

class RegistrationFragment : Fragment() {

    private val viewModel: AuthViewModel by inject()

    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRegister.setOnClickListener {
            createUser(it, etEmail.text.toString(), etPassword.text.toString())
        }
    }


    private fun createUser(view: View, email: String, password: String) {
        snackbar("Registering...")
        fbAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity(), OnCompleteListener { task ->
                if (task.isSuccessful) {
                    viewModel.saveUser(DBUser(email))
                    view.findNavController()
                        .navigate(R.id.action_registrationFragment_to_mainActivity)
                } else {
                    snackbar("Error: ${task.exception?.message}")
                }
            })
    }
}