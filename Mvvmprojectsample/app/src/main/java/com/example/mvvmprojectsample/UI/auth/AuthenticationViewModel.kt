package com.example.mvvmprojectsample.UI.auth

import android.view.View
import androidx.lifecycle.ViewModel

class AuthenticationViewModel: ViewModel() {
    var email: String? = null
     var password: String? = null
    var authListener:AuthListener? = null

    fun onLoginButtonClicked(view: View) {
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("invalid email or password")
        }
        authListener?.onSuccess()
    }

}