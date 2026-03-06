package com.example.myhealth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class HealthTipsFragment : Fragment(R.layout.fragment_health_tips) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profileIcon = view.findViewById<ImageView?>(R.id.profileIcon)
        val profileName = view.findViewById<TextView?>(R.id.profileName)
        val profileAge = view.findViewById<TextView?>(R.id.profileAge)

        if (profileIcon != null && profileName != null && profileAge != null) {
            ProfileManager.loadProfile(profileName, profileAge, profileIcon)
        }

        val lowerBP = view.findViewById<View>(R.id.lowerBP)
        val dietTips = view.findViewById<View>(R.id.dietTips)
        val exercise = view.findViewById<View>(R.id.regularExercise)

        lowerBP.setOnClickListener {
            openWeb("https://namhyafoods.com/blogs/news/natural-ways-to-manage-blood-pressure")
        }

        dietTips.setOnClickListener {
            openWeb("https://weight-loss.formula.care/")
        }

        exercise.setOnClickListener {
            openWeb("https://medlineplus.gov/benefitsofexercise.html")
        }
    }

    private fun openWeb(url: String) {
        val intent = Intent(requireContext(), WebViewActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }
}