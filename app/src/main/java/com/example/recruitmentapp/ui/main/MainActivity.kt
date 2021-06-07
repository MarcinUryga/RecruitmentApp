package com.example.recruitmentapp.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.recruitmentapp.R
import com.example.recruitmentapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var viewDataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = ActivityMainBinding.inflate(layoutInflater)
        viewDataBinding.apply {
            viewmodel = viewModel
            lifecycleOwner = this@MainActivity
        }
        setContentView(viewDataBinding.root)
        setupNavigationToolbar()
    }

    private fun setupNavigationToolbar() {
        setSupportActionBar(viewDataBinding.toolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        viewDataBinding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}