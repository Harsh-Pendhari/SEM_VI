package com.example.myhealth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val database = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser

        if (currentUser == null) {

            startActivity(Intent(this, LoginActivity::class.java))
            finish()

        } else {

            checkUserProfile(currentUser.uid)
        }
    }

    private fun checkUserProfile(uid: String) {

        val ref = database.getReference("users").child(uid)

        ref.get().addOnSuccessListener { snapshot ->

            if (snapshot.exists()) {

                val profileCompleted =
                    snapshot.child("profileCompleted").getValue(Boolean::class.java)

                if (profileCompleted == true) {

                    startActivity(Intent(this, DashboardActivity::class.java))
                    finish()

                } else {

                    startActivity(Intent(this, RegistrationForm::class.java))
                    finish()
                }

            } else {

                startActivity(Intent(this, RegistrationForm::class.java))
                finish()
            }
        }
    }
}