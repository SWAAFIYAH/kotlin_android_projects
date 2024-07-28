package com.example.bankonline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.bankonline.UI.Dashbord
import com.example.bankonline.databinding.ActivityLoginPageBinding
import com.example.bankonline.repositories.LoginRepoImp

import com.example.new_internet_banking_android.ResetPassword

import java.util.concurrent.Executor

class LoginPage : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPageBinding
    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(LoginRepoImp())
    }

    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var executor: Executor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(applicationContext, "Authentication error: $errString", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(applicationContext, "Authentication succeeded", Toast.LENGTH_SHORT).show()
                navigateToDashboard()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(applicationContext, "Authentication failed", Toast.LENGTH_SHORT).show()
            }
        })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Fingerprint Authentication")
            .setSubtitle("Authenticate using your fingerprint")
            .setNegativeButtonText("Cancel")
            .build()

        if (isFingerprintEnrolled()) {
            biometricPrompt.authenticate(promptInfo)
        }

        binding.btnSubmitLogin.setOnClickListener {
            val userId = binding.userID.text.toString()
            val passWord = binding.password.text.toString()
            loginViewModel.login(userId, passWord)
        }

        loginViewModel.loginResults.observe(this, Observer { result ->
            result.fold(
                onSuccess = {
                    if (firstLogin()){
                        val userId = binding.userID.text.toString()
                        val intent = Intent(this, ResetPassword::class.java)
                            .apply { putExtra("USER_ID", userId)  }
                        startActivity(intent)
                        finish()
                    } else {
                        val intent = Intent(this, Dashbord::class.java)
                        startActivity(intent)
                        finish()
                    }
                },
                onFailure = { exception ->
                    Toast.makeText(this, "Login failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
            )
        })
    }
    private fun firstLogin(): Boolean{
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("first_login", true)
    }


    private fun isFingerprintEnrolled(): Boolean {
        val biometricManager = androidx.biometric.BiometricManager.from(this)
        return biometricManager.canAuthenticate(androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG) ==
                androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS
    }

    private fun navigateToDashboard() {

        val intent = Intent(this, Dashbord::class.java)
        startActivity(intent)
        finish()
    }
}
