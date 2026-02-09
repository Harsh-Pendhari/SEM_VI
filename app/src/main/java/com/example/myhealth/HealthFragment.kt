package com.example.myhealth

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment

class HealthFragment : Fragment(R.layout.fragment_health) {

    private lateinit var cardHealthInsights: FrameLayout
    private lateinit var cardHealthTips: FrameLayout
    private lateinit var cardHealthRecord: FrameLayout
    private lateinit var cardReportHistory: FrameLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dashboard = activity as? DashboardActivity ?: return

//        view.findViewById<View>(R.id.cardHealthInsights)
//            .findViewById<View>(R.id.cardRoot)
//            .setOnClickListener {
//                dashboard.openHealthReportInsightsPage()
//            }

        view.findViewById<View>(R.id.cardHealthTips)
            .findViewById<View>(R.id.cardRoot)
            .setOnClickListener {
                dashboard.openHealthTipsPage()
            }

        view.findViewById<View>(R.id.cardHealthRecord)
            .findViewById<View>(R.id.cardRoot)
            .setOnClickListener {
                dashboard.openHealthRecordTrackerPage()
            }

//        view.findViewById<View>(R.id.cardReportHistory)
//            .findViewById<View>(R.id.cardRoot)
//            .setOnClickListener {
//                dashboard.openReportHistoryPage()
//            }
    }

}
