package com.redhaputra.pokemonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.redhaputra.core.shared.SharedMainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: SharedMainViewModel by viewModels()
    private lateinit var bottomNavBar: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavGraph()
        observeViewModel()
    }

    private fun setupNavGraph() {
        val navController = findNavController(this, R.id.navHostFragment)
        bottomNavBar = findViewById(R.id.btmNavigation)
        setupWithNavController(bottomNavBar, navController)
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.isShowBottomBar.collectLatest {
                if (::bottomNavBar.isInitialized) {
                    bottomNavBar.visibility = if (it) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                }
            }
        }
    }
}