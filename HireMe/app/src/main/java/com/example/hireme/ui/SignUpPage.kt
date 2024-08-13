package com.example.hireme.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hireme.databinding.ActivitySignUpPageBinding
import com.example.hireme.models.User
import com.example.hireme.models.User2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpPage : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var dataBase: DatabaseReference

    private lateinit var binding: ActivitySignUpPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.signupBtn.setOnClickListener {
            signUpUser()
        }
    }

    private fun signUpUser() {
        val name = binding.nameEditText.text.toString().trim()
        val email = binding.emailEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString().trim()
        val confirmPassword = binding.confirmPasswordEditText.text.toString().trim()

        if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
            if (password == confirmPassword) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser = auth.currentUser
                        firebaseUser?.let {
                            val user = User(
                                uid = it.uid,
                                name = name,
                                email = email,
                                password = password,
                                confirmPassword = confirmPassword
                            )
                            Log.d(
                                "UserDetails",
                                "UID: ${user.uid}, Name: ${user.name}, Email: ${user.email}, Password: ${user.password}, ConfirmPassword: ${user.confirmPassword}"
                            )
                            saveUserToDatabase(user)
                        }
                        Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(this@SignUpPage, LoginPage::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        val error = task.exception?.message
                        Toast.makeText(this, "Registration failed: $error", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveUserToDatabase(user: User) {
        dataBase = FirebaseDatabase.getInstance().reference.child("User").child(user.uid)

        dataBase.push().setValue(user).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("DatabaseSave", "User saved successfully with UID: ${user.uid}")
                val intent = Intent(this@SignUpPage, LoginPage::class.java)
                startActivity(intent)
                finish()
            } else {
                val error = it.exception?.message
                Log.d("DatabaseSave", "User Not saved successfully with UID: ${user.uid}")
                Toast.makeText(this, "Failed to save user: $error", Toast.LENGTH_SHORT).show()

            }
        }.addOnFailureListener { e ->
            Log.e("DatabaseSave", "Error saving user", e)
        }
    }
}
