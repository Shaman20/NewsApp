package com.example.devnews

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.devnews.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        validate()
    }

    private fun validate() {

        binding.btnLogin.setOnClickListener {

            val username = intent.getStringExtra("username")
            val password = intent.getStringExtra("password")
            val name = binding.username.text.toString()
            val pass = binding.password.text.toString()

            if (username == name && password == pass) {

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {

                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_LONG).show()
            }
        }
    }
}