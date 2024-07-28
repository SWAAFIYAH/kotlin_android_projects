package com.example.bankonline.repositories

import com.example.bankonline.retrofit.ApiService
import com.example.bankonline.models.Login
import com.example.bankonline.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

interface LoginRepo {
    suspend fun login(userId: String, password: String): Result<Boolean>
}


class LoginRepoImp : LoginRepo {
    private val apiService: ApiService = RetrofitClient.apiService

    override suspend fun login(userId: String, password: String): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.login(Login(userId, password)).awaitResponse()
                if (response.isSuccessful) {
                    Result.success(true)
                } else {
                    Result.failure(Exception("Login failed: ${response.errorBody()?.string()}"))
                }
            } catch (e: Exception) {
                Result.failure(Exception("Login failed: ${e.message}"))
            }
        }
    }
}
