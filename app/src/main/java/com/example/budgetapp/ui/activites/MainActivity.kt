package com.example.budgetapp.ui.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.budgetapp.R
import com.example.budgetapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding


    private val navHostFragment: NavHostFragment by lazy { getNavHost() as NavHostFragment }
    protected val navController: NavController by lazy { navHostFragment.navController }
    
    fun getNavHost(): Fragment = drawerNavHost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(toolbar)
        setupBottomNavigation()
        setupActionBar()
        setupDestinationListener()
    }

    private fun setupBottomNavigation() {
        bottomNav?.let {
            NavigationUI.setupWithNavController(bottomNav, navController)
        }
    }

    private fun setupActionBar() {
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
    }

    private fun setupDestinationListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.profileFragment ||
                destination.id == R.id.graphFragment ||
                destination.id == R.id.transactionsFragment
            ) {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.drawerNavHost),
            drawer_layout
        )
    }
}
