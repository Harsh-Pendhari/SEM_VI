package com.example.myhealth

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment

class HealthRecordTrackerFragment : Fragment(R.layout.fragment_health_record_tracker) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardSteps = view.findViewById<FrameLayout>(R.id.cardSteps)
        val cardSleep = view.findViewById<FrameLayout>(R.id.cardSleep)
        val cardBP = view.findViewById<FrameLayout>(R.id.cardBP)
        val cardHeartRate = view.findViewById<FrameLayout>(R.id.cardHeartRate)

        cardSteps.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, StepsFragment())
                .addToBackStack(null)
                .commit()
        }

        cardSleep.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, SleepFragment())
                .addToBackStack(null)
                .commit()
        }

        cardBP.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, BloodPressureCardFragment())
                .addToBackStack(null)
                .commit()
        }

        cardHeartRate.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HeartRateFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}