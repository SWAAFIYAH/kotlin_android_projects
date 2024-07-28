package com.example.new_internet_banking_android.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.new_internet_banking_android.repositories.ResetPasswordRepo
import kotlinx.coroutines.launch

class ResetPasswordViewModel (private val repository: ResetPasswordRepo): ViewModel() {
    private val _resetPasswordResult = MutableLiveData<Result<Boolean>>()
    val resetPasswordResult : LiveData<Result<Boolean>> = _resetPasswordResult

    fun resetPassword(userId: String, currentPassword: String, newPassword: String, confirmPassword: String) {

        viewModelScope.launch {
            val result = repository.resetPassword(userId, currentPassword, newPassword, confirmPassword)
            _resetPasswordResult.value = result

        }
    }
}
class ResetPasswordViewModelFactory(private val repository: ResetPasswordRepo) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResetPasswordViewModel::class.java)) {
            return ResetPasswordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}