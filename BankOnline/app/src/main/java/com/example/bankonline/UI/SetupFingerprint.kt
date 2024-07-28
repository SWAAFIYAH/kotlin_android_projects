package com.example.new_internet_banking_android

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.bankonline.LoginPage
import com.example.bankonline.databinding.ActivitySetupFingerprintBinding
import com.example.new_internet_banking_android.viewModels.SetupFingerprintViewModel

class SetupFingerprint : AppCompatActivity() {

    private lateinit var binding: ActivitySetupFingerprintBinding
    private val viewModel: SetupFingerprintViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetupFingerprintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSetupFingerprint.setOnClickListener {
            viewModel.checkBiometricSupportAndNavigate(this)
        }

        viewModel.fingerprintStatus.observe(this, Observer { status ->
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        })

        viewModel.navigateToSettings.observe(this, Observer { navigate ->
            if (navigate) {
                val enrollIntent = Intent(Settings.ACTION_SECURITY_SETTINGS)
                startActivity(enrollIntent)
                viewModel.onNavigatedToSettings()
            }
        })

        viewModel.navigateToLogin.observe(this, Observer { navigate ->
            if (navigate) {
                // Navigate to the login screen
                val loginIntent = Intent(this, LoginPage::class.java)
                startActivity(loginIntent)
                viewModel.onNavigatedToLogin()
            }
        })
    }
}