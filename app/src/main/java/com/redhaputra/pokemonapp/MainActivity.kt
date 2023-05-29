package com.redhaputra.pokemonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavGraph()
    }

    private fun setupNavGraph() {
        val navController = findNavController(this, R.id.navHostFragment)
        val bottomNavBar = findViewById<BottomNavigationView>(R.id.btmNavigation)
        setupWithNavController(bottomNavBar, navController)
    }
}