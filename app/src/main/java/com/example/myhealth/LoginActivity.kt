package com.example.myhealth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginBtn: Button
    private lateinit var registerBtn: Button
    private lateinit var errorMsg: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.userId)
        password = findViewById(R.id.pwd)
        loginBtn = findViewById(R.id.login_btn)
        registerBtn = findViewById(R.id.new_registration)
        errorMsg = findViewById(R.id.error_msg)

        auth = FirebaseAuth.getInstance()

        errorMsg.visibility = View.GONE

        loginBtn.setOnClickListener {

            val emailText = email.text.toString().trim()
            val passText = password.text.toString().trim()

            if (emailText.isEmpty() || passText.isEmpty()) {

                showError("Please fill all fields")
                return@setOnClickListener
            }

            loginUser(emailText, passText)
        }

        registerBtn.setOnClickListener {

            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun loginUser(email: String, password: String) {

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {

                if (it.isSuccessful) {

                    startActivity(Intent(this, MainActivity::class.java))
                    finish()

                } else {

                    showError("Invalid email or password")
                }
            }
    }

    private fun showError(message: String) {

        errorMsg.visibility = View.VISIBLE
        errorMsg.text = message
    }
}