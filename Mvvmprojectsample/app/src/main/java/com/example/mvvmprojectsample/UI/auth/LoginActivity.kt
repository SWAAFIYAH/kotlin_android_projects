package com.example.mvvmprojectsample.UI.auth

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmprojectsample.R
import com.example.mvvmprojectsample.databinding.ActivityLoginBinding



class LoginActivity : AppCompatActivity(), AuthListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider.of(this).get(AuthenticationViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this


    }

    override fun onStarted() {
        Toast.makeText(this, "login started", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess() {
        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}