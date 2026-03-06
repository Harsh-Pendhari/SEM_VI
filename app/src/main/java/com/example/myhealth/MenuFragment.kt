package com.example.myhealth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class MenuFragment : Fragment(R.layout.fragment_menu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profileIcon = view.findViewById<ImageView?>(R.id.profileIcon)
        val profileName = view.findViewById<TextView?>(R.id.profileName)
        val profileAge = view.findViewById<TextView?>(R.id.profileAge)

        val logoutBtn = view.findViewById<View>(R.id.Logout)

        if (profileIcon != null && profileName != null && profileAge != null) {
            ProfileManager.loadProfile(profileName, profileAge, profileIcon)
        }

        logoutBtn.setOnClickListener {
            logoutUser()
        }
    }

    private fun logoutUser() {

        // Firebase logout
        FirebaseAuth.getInstance().signOut()

        // Go to login screen
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}