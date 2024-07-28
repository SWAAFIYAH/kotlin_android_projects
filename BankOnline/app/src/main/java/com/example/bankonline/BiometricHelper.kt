package com.example.bankonline

import android.content.Context
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat

class BiometricHelper(private val context: Context) {

    private var biometricPrompt: BiometricPrompt? = null
    private var promptInfo: BiometricPrompt.PromptInfo? = null

    fun initBiometricPrompt(
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val executor = ContextCompat.getMainExecutor(context)

        biometricPrompt = BiometricPrompt(
            context as androidx.fragment.app.FragmentActivity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    onFailure(errString.toString())
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    onSuccess()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    onFailure("Authentication failed")
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Authenticate using your fingerprint")
            .setNegativeButtonText("Cancel")
            .build()
    }

    fun authenticateUser() {
        biometricPrompt?.authenticate(promptInfo!!)
    }
}