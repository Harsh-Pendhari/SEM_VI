package com.example.myhealth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.fragment_home) {

    // Header
    private lateinit var ivHeartIcon: ImageView
    private lateinit var ivMyHealthText: ImageView
    private lateinit var ivNotification: ImageView
    private lateinit var ivSOS: ImageView
    private lateinit var ivProfile: ImageView

    // Cards
    private lateinit var cardMyMedicines: View
    private lateinit var cardHealthReportInsights: View
    private lateinit var cardHealthTips: View
    private lateinit var cardHealthRecord: View
    private lateinit var cardDoctorsAppointments: View
    private lateinit var cardReportHistory: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews(view)
        setClickListeners()
    }

    private fun bindViews(view: View) {
        ivHeartIcon = view.findViewById(R.id.ivHeartIcon)
        ivMyHealthText = view.findViewById(R.id.ivMyHealthText)
        ivNotification = view.findViewById(R.id.ivNotification)
        ivSOS = view.findViewById(R.id.ivSOS)
        ivProfile = view.findViewById(R.id.ivProfile)

        cardMyMedicines = view.findViewById(R.id.cardMyMedicines)
        cardHealthReportInsights = view.findViewById(R.id.cardHealthReportInsights)
        cardHealthTips = view.findViewById(R.id.cardHealthTips)
        cardHealthRecord = view.findViewById(R.id.cardHealthRecord)
        cardDoctorsAppointments = view.findViewById(R.id.cardDoctorsAppointments)
        cardReportHistory = view.findViewById(R.id.cardReportHistory)
    }

    private fun setClickListeners() {

        val dashboard = activity as? DashboardActivity ?: return

        // Cards → Bottom Navigation destinations

        cardMyMedicines.setOnClickListener {
            dashboard.selectBottomNavItem(R.id.nav_medicine)
        }

        cardHealthReportInsights.setOnClickListener {
            dashboard.openHealthReportInsightsPage()
        }

        cardHealthTips.setOnClickListener {
            dashboard.openHealthTipsPage()
        }

        cardHealthRecord.setOnClickListener {
            dashboard.openHealthRecordTrackerPage()
        }

        cardDoctorsAppointments.setOnClickListener {
            dashboard.selectBottomNavItem(R.id.nav_appointment)
        }

        // Report history → leave for later (as requested)
        cardReportHistory.setOnClickListener {
            dashboard.openReportHistoryPage()
        }

        // Header icons (still independent)
        ivNotification.setOnClickListener {
            // TODO: notifications page later
        }

        ivSOS.setOnClickListener {
            startActivity(Intent(requireContext(), SOSActivity::class.java))

        }

        ivProfile.setOnClickListener {
            // TODO: profile page later
        }
    }


//    private fun toast(message: String) {
//        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
//    }
}
