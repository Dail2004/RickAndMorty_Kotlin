package com.example.rickandmortykotlin.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.rickandmortykotlin.R
import com.example.rickandmortykotlin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigationInit()
    }

    private fun navigationInit() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.characterFragment, R.id.locationFragment, R.id.episodeFragment
        ).build()

        NavigationUI.setupWithNavController(binding.navView, navController)
        NavigationUI.setupWithNavController(binding.toolBar, navController, appBarConfiguration)
    }
}