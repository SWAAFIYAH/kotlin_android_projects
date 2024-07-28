package com.example.bankonline.retrofit

import com.example.bankonline.models.Login
import com.example.bankonline.models.LoginInstanceCreator
import com.example.bankonline.models.LoginResponse
import com.example.bankonline.models.LoginResponseInstanceCreator
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://102.210.244.222:6508/"

    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(Login::class.java, LoginInstanceCreator())
        .registerTypeAdapter(LoginResponse::class.java, LoginResponseInstanceCreator())
        .setLenient()
        .create()

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }
}
