package com.example.new_internet_banking_android.repositories

import com.example.bankonline.models.ResetPasswordModel
import com.example.bankonline.retrofit.ApiService
import com.example.bankonline.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse


interface ResetPasswordRepo {
    suspend fun resetPassword (userId: String, currentPassword: String, newPassword: String, confirmPassword: String):Result<Boolean>

}

class ResetPasswordRepoImp: ResetPasswordRepo {
    private val apiService: ApiService = RetrofitClient.apiService

    override suspend fun resetPassword(
        userId: String,
        currentPassword: String,
        newPassword: String,
        confirmPassword: String
    ): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.resetPassword(ResetPasswordModel(userId,currentPassword, newPassword, confirmPassword))
                    .awaitResponse()

                if (response.isSuccessful) {
                    Result.success(true)
                } else {
                    Result.failure(
                        Exception(
                            "Reset Password Failed: ${
                                response.errorBody()?.string()
                            }"
                        )
                    )
                }
            } catch (e: Exception) {
                Result.failure(Exception("Reset Password Failed: ${e.message}"))
            }

        }

    }
}