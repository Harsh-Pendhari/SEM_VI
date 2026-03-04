package com.example.myhealth

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class AppointmentsFragment : Fragment(R.layout.fragment_appointments) {

    private lateinit var cardBookAppointment: MaterialButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardBookAppointment = view.findViewById(R.id.btnBookAppointment)

        cardBookAppointment.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, BookAppointmentFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}