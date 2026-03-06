package com.example.myhealth

import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object ProfileManager {

    fun loadProfile(nameView: TextView?, ageView: TextView?, iconView: ImageView?) {

        val user = FirebaseAuth.getInstance().currentUser ?: return
        val uid = user.uid

        val db = FirebaseDatabase.getInstance().reference
        db.child("users").child(uid).get().addOnSuccessListener { snapshot ->

            if (!snapshot.exists()) return@addOnSuccessListener

            val name = snapshot.child("name").value?.toString() ?: ""
            val age = snapshot.child("age").value?.toString() ?: ""
            val gender = snapshot.child("gender").value?.toString() ?: ""

            nameView?.text = "Hello, $name!"
            ageView?.text = "$age yrs"

            if (iconView != null) {
                if (gender == "Male") {
                    iconView.setImageResource(R.drawable.ic_male)
                } else {
                    iconView.setImageResource(R.drawable.ic_female)
                }
            }
        }
    }
}