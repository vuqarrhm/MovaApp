package com.example.film

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.film.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomMenu()


        // Navigation Controller əldə etmək üçün supportFragmentManager istifadə edirik
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        // Fragment dəyişdikdə BottomNavigationView-i idarə et
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment,R.id.basketFragment,R.id.searchFragment-> binding.bottomMain.visibility = View.VISIBLE
                else -> binding.bottomMain.visibility = View.GONE
            }
        }

        // BottomNavigationView ilə NavigationController-i bağlayırıq
        binding.bottomMain.setupWithNavController(navController)

    }

    private fun setBottomMenu(){
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        NavigationUI.setupWithNavController(binding.bottomMain,navHostFragment.navController)

    }



}