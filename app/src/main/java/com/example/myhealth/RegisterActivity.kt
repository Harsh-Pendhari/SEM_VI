package com.example.myhealth

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.RegisterActivity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val RegisterPageNameField = findViewById<EditText>(R.id.userName)
        val RegisterPagePhonenumField = findViewById<EditText>(R.id.phoneNo)
        val RegisterPageEmailField = findViewById<EditText>(R.id.userId)
        val RegisterPagePasswordField = findViewById<EditText>(R.id.password)
        val RegisterPageConfirmPasswordField = findViewById<EditText>(R.id.confirmPassword)
        val RegisterPageErrorMSG = findViewById<TextView>(R.id.error_msg)
        val RegisterPageRegisterBTN = findViewById<Button>(R.id.register_btn)
        val RegisterPageLoginBTN = findViewById<Button>(R.id.already_account)


    }
}