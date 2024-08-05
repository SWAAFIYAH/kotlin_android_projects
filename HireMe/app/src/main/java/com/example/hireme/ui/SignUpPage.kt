package com.example.hireme.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hireme.databinding.ActivitySignUpPageBinding
import com.example.hireme.models.User
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
        dataBase = FirebaseDatabase.getInstance().reference

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
                            val user = User(it.uid, name, email,password,confirmPassword)
                            Log.d("UserDetails", "UID: ${user.uid}, Name: ${user.name}, Email: ${user.email}, Password: ${user.password}, ConfirmPassword: ${user.confirmPassword}")
                            saveUserToDatabase(user)
                        }
                        Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SignUpPage, LoginPage::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        val error = task.exception?.message
                        Toast.makeText(this, "Registration failed: $error", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveUserToDatabase(user:User) {
        dataBase.child("users").child(user.uid).setValue(user).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Log.d("DatabaseSave", "User saved successfully with UID: ${user.uid}")
                val intent = Intent(this@SignUpPage, LoginPage::class.java)
                startActivity(intent)
                finish()
            }else{
                val error = task.exception?.message
                Toast.makeText(this, "Failed to save user: $error", Toast.LENGTH_SHORT).show()

            }
        }
    }
}
