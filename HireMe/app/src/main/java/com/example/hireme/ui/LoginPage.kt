package com.example.hireme.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hireme.databinding.ActivityLoginPageBinding
import com.google.firebase.auth.FirebaseAuth

class LoginPage : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPageBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.loginbtn.setOnClickListener {
            loginUser()
        }
        binding.signupText.setOnClickListener {
            val intent = Intent(this@LoginPage, SignUpPage::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginUser() {
        val email = binding.loginEmailEditText.text.toString().trim()
        val password = binding.loginPasswordEtext.text.toString().trim()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    val intent = Intent(this@LoginPage, SinglePage::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val error = it.exception?.message
                    Toast.makeText(this@LoginPage, "Login failed: $error", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this@LoginPage, "Please enter email and password", Toast.LENGTH_SHORT).show()
        }
    }
}
