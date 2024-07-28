package com.example.new_internet_banking_android.viewModels

import android.app.Application
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.biometric.BiometricManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.new_internet_banking_android.BiometricHelper

class SetupFingerprintViewModel(application: Application) : AndroidViewModel(application) {

    private val _navigateToSettings = MutableLiveData<Boolean>()
    val navigateToSettings: LiveData<Boolean> get() = _navigateToSettings

    private val _fingerprintStatus = MutableLiveData<String>()
    val fingerprintStatus: LiveData<String> get() = _fingerprintStatus

    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean> get() = _navigateToLogin

    fun checkBiometricSupportAndNavigate(context: Context) {
        val biometricManager = BiometricManager.from(context)
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                _fingerprintStatus.value = "SUPPORTED"
                _navigateToSettings.value = false
                val biometricHelper = BiometricHelper(
                    context)
                biometricHelper.initBiometricPrompt( onSuccess = {
                    _fingerprintStatus.postValue("Authentication succeeded")
                    _navigateToLogin.postValue(true)
                },
                    onFailure = { error ->
                        _fingerprintStatus.postValue("Authentication failed: $error")
                    })
                biometricHelper.authenticateUser()
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                _fingerprintStatus.value = "No biometric hardware available"
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                _fingerprintStatus.value = "Biometric hardware unavailable"
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                _fingerprintStatus.value = "No biometric credentials enrolled"
                _navigateToSettings.value = true
            }
            else -> {
                _fingerprintStatus.value = "Biometric authentication not supported"
            }
        }
    }

    fun onNavigatedToSettings() {
        _navigateToSettings.value = false
    }

    fun onNavigatedToLogin() {
        _navigateToLogin.value=false
        }
}