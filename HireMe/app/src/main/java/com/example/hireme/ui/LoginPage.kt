package com.example.hireme.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hireme.R
import com.example.hireme.databinding.ActivityLoginPageBinding

class LoginPage : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginbtn.setOnClickListener{
            val intent = Intent(this@LoginPage, SinglePage::class.java)
            startActivity(intent)
            finish()
        }
        binding.signupText.setOnClickListener {
            val intent = Intent(this@LoginPage,SignUpPage::class.java)
            startActivity(intent)
            finish()
        }

    }
}