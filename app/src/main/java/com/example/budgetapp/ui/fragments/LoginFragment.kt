package com.example.budgetapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.budgetapp.R
import com.example.budgetapp.common.extensions.snackbar
import com.example.budgetapp.data.models.persistance.DBUser
import com.example.budgetapp.databinding.FragmentLoginBinding
import com.example.budgetapp.ui.activites.MainActivity
import com.example.budgetapp.viewmodels.AuthViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject

class LoginFragment : Fragment() {


    private lateinit var binding: FragmentLoginBinding
    private val viewModel: AuthViewModel by inject()

    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        btnLogin.setOnClickListener { view ->
            signIn(view, etEmail.text.toString(), etPassword.text.toString())
        }
    }

    private fun signIn(view: View, email: String, password: String) {
        snackbar("Authenticating...")
        fbAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity(), OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    viewModel.saveUser(DBUser(email))
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.putExtra("id", fbAuth.currentUser?.email)
                    startActivity(intent)
                } else {
                    snackbar("Error: ${task.exception?.message}")
                }
            })

    }

}