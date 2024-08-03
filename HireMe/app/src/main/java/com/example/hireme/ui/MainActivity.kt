package com.example.hireme.ui

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import com.example.hireme.R
import com.example.hireme.databinding.ActivityMainBinding
import com.google.android.material.animation.AnimationUtils

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val rotationAnimation = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.rotation_file)
        binding.logoImage.startAnimation(rotationAnimation)


        rotationAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                // No-op
            }

            override fun onAnimationEnd(animation: Animation?) {
                startActivity(Intent(this@MainActivity, LoginPage::class.java))
                finish()  // Close splash activity
            }

            override fun onAnimationRepeat(animation: Animation?) {
                // No-op
            }
        })
    }
}



