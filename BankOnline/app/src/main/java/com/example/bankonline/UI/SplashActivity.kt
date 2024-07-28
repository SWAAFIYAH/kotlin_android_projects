package com.example.bankonline.UI

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.bankonline.LoginPage
import com.example.bankonline.databinding.ActivitySplashBinding

const val SPLASH_DELAY: Long = 2000

class MainActivity : AppCompatActivity() {
private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DELAY)
    }
}
