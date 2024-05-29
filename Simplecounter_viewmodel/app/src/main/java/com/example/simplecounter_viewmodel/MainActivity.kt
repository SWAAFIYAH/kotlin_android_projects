package com.example.simplecounter_viewmodel

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.simplecounter_viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myViewModel: AppViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        myViewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        binding.countertext.text = myViewModel.getCurrentCounter().toString()
        binding.counterbutton.setOnClickListener {
            binding.countertext.text = myViewModel.updateCounter().toString()
        }

    }
}