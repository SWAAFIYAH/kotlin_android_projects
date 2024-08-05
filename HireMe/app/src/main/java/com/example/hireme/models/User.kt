package com.example.hireme.models

data class User (
    val uid:String,
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
