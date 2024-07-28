package com.example.bankonline.models

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("userId") val userId: String = "",
    @SerializedName("password") val password: String = ""
)

data class LoginResponse(
    @SerializedName("message") val message: String = ""
)
