package com.example.myhealth

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class HealthReportInsightsFragment : Fragment(R.layout.fragment_health_report_insights) {

    private lateinit var cardBloodPressure: FrameLayout
    private lateinit var cardCholestrol: FrameLayout
    private lateinit var cardSleep: FrameLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profileIcon = view.findViewById<ImageView?>(R.id.profileIcon)
        val profileName = view.findViewById<TextView?>(R.id.profileName)
        val profileAge = view.findViewById<TextView?>(R.id.profileAge)

        if (profileIcon != null && profileName != null && profileAge != null) {
            ProfileManager.loadProfile(profileName, profileAge, profileIcon)
        }
        cardBloodPressure = view.findViewById(R.id.cardBloodPressure)
        cardCholestrol = view.findViewById(R.id.cardCholestrol)
        cardSleep = view.findViewById(R.id.cardSleep)

        cardBloodPressure.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, BloodPressureCardFragment())
                .addToBackStack(null)
                .commit()
        }

        cardCholestrol.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, CholestrolCardFragment())
                .addToBackStack(null)
                .commit()
        }

        cardSleep.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, SleepFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}