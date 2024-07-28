package com.example.bankonline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bankonline.repositories.LoginRepo
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepo) : ViewModel() {
    private val _loginResults = MutableLiveData<Result<Boolean>>()
    val loginResults: LiveData<Result<Boolean>> = _loginResults

    fun login(userId: String, password: String) {
        viewModelScope.launch {
            val results = repository.login(userId, password)
            _loginResults.value = results
        }
    }
}

class LoginViewModelFactory(private val repository: LoginRepo) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
