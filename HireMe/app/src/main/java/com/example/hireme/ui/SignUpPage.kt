package com.example.hireme.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hireme.R
import com.example.hireme.databinding.ActivitySignUpPageBinding
import com.example.hireme.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpPage : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var dataBase :DatabaseReference
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
        val passWord = binding.passwordEditText.text.toString().trim()
        val confirmPassword = binding.confirmPasswordEditText.text.toString().trim()

        if(name.isNotEmpty() && email.isNotEmpty() && passWord.isNotEmpty() && confirmPassword.isNotEmpty()){
            if(passWord == confirmPassword){

            }else{
                Toast.makeText(this@SignUpPage, "unmatched passwords",Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this@SignUpPage, "No field should be empty, please fill all required fields",Toast.LENGTH_SHORT).show()
        }
        auth.createUserWithEmailAndPassword(email, passWord).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val userId = auth.currentUser?.uid
                userId?.let {
                    val user = User(name, email, passWord, confirmPassword)
                    dataBase.child("users").child(it).setValue(user)
                        .addOnCompleteListener { dbTask ->
                          if(dbTask.isSuccessful){
                              Toast.makeText(this@SignUpPage, "Registration successful", Toast.LENGTH_SHORT).show()

                          } else{
                              Toast.makeText(this, "Failed to save user info", Toast.LENGTH_SHORT).show()

                          }
                        }
                }
            }else{
                Toast.makeText(this@SignUpPage, "Registration failed", Toast.LENGTH_SHORT).show()

            }
        }
    }
}