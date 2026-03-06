package com.example.myhealth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var registerBtn: Button
    private lateinit var loginBtn: Button
    private lateinit var errorMsg: TextView

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        name = findViewById(R.id.userName)
        phone = findViewById(R.id.phoneNo)
        email = findViewById(R.id.userId)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirmPassword)
        registerBtn = findViewById(R.id.register_btn)
        loginBtn = findViewById(R.id.already_account)
        errorMsg = findViewById(R.id.error_msg)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        errorMsg.visibility = View.GONE

        registerBtn.setOnClickListener {

            val nameText = name.text.toString().trim()
            val phoneText = phone.text.toString().trim()
            val emailText = email.text.toString().trim()
            val passText = password.text.toString().trim()
            val confirmPassText = confirmPassword.text.toString().trim()

            if (nameText.isEmpty() ||
                phoneText.isEmpty() ||
                emailText.isEmpty() ||
                passText.isEmpty() ||
                confirmPassText.isEmpty()
            ) {
                showError("Please fill all fields")
                return@setOnClickListener
            }

            if (passText != confirmPassText) {
                showError("Passwords do not match")
                return@setOnClickListener
            }

            createAccount(nameText, phoneText, emailText, passText)
        }

        loginBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun createAccount(name: String, phone: String, email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val uid = auth.currentUser!!.uid

                    val userData = HashMap<String, Any>()
                    userData["name"] = name
                    userData["phone"] = phone
                    userData["email"] = email
                    userData["profileCompleted"] = false

                    database.reference
                        .child("users")
                        .child(uid)
                        .setValue(userData)

                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()

                } else {

                    showError("User already has an account")
                }
            }
    }

    private fun showError(message: String) {

        errorMsg.visibility = View.VISIBLE
        errorMsg.text = message
    }
}