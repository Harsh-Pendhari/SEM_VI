package com.example.myhealth

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.LoginActivity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val LoginPageEmailField = findViewById<EditText>(R.id.userId)
        val LoginPagePasswordField = findViewById<EditText>(R.id.pwd)
        val LoginPageErrorMSG = findViewById<TextView>(R.id.error_msg)
        val LoginPageLoginBTN = findViewById<Button>(R.id.login_btn)
        val LoginPageRegisterBTN = findViewById<Button>(R.id.new_registration)
        val LoginPageForgotPasswordBTN = findViewById<Button>(R.id.forgotPassword)


    }
}