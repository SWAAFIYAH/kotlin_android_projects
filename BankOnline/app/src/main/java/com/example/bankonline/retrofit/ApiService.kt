package com.example.bankonline.retrofit

import com.example.bankonline.models.Login
import com.example.bankonline.models.LoginResponse
import com.example.bankonline.models.ResetPasswordModel
import com.example.bankonline.models.ResetPasswordResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("authentication/login")
    fun login(@Body login: Login): Call<LoginResponse>

    @POST("authentication/setPassword")
    fun resetPassword(@Body resetPassword: ResetPasswordModel): Call<ResetPasswordResponse>
}
