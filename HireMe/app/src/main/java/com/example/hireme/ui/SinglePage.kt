package com.example.hireme.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.hireme.R
import com.example.hireme.databinding.ActivitySinglePageBinding

class SinglePage : AppCompatActivity() {
    private lateinit var binding: ActivitySinglePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySinglePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.containerView)as NavHostFragment
val navController =  navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)


    }
}
