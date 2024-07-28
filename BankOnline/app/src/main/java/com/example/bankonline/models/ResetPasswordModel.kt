package com.example.bankonline.models

data class ResetPasswordModel (

    val userId: String,
    val currentPassword: String,
    val newPassword: String,
    val confirmPassword: String


    )
 data class ResetPasswordResponse(
     val success: Boolean,
     val message: String
 )