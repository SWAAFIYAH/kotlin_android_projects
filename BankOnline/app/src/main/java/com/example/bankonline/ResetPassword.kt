package com.example.new_internet_banking_android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.bankonline.LoginPage
import com.example.bankonline.databinding.ActivityResetPasswordBinding
import com.example.new_internet_banking_android.repositories.ResetPasswordRepoImp
import com.example.new_internet_banking_android.viewModels.ResetPasswordViewModel
import com.example.new_internet_banking_android.viewModels.ResetPasswordViewModelFactory

class ResetPassword : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private val resetPasswordViewModel: ResetPasswordViewModel by viewModels {
        ResetPasswordViewModelFactory(ResetPasswordRepoImp())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {
            val currentPassword = binding.password.text.toString()
            val newPassword = binding.newPassword.text.toString()
            val confirmPassword = binding.confirmPassword.text.toString()
            validateInput(currentPassword, newPassword, confirmPassword)
        }
    }

    private fun validateInput(
        currentPassword: String,
        newPassword: String,
        confirmPassword: String
    ) {
        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields required. Please enter", Toast.LENGTH_SHORT).show()
        } else {
            if (newPassword != confirmPassword) {
                Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show()
            } else {
                val userId = intent.getStringExtra("USER_ID") ?: ""
                resetPasswordViewModel.resetPassword(userId, currentPassword, newPassword, confirmPassword)

                resetPasswordViewModel.resetPasswordResult.observe(this, Observer { result ->
                    result.fold(
                        onSuccess = {
                            navigateToLogin()
                            setFirstLoginCompleted()
                        },
                        onFailure = { exception ->
                            Toast.makeText(
                                this,
                                "Reset Password failed: ${exception.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                })
            }
        }
    }

    private fun setFirstLoginCompleted() {
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putBoolean("first_login", false)
            apply()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginPage::class.java)
        startActivity(intent)
        finish()
    }
}