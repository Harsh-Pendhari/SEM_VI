package com.example.myhealth

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class BloodPressureCardFragment : Fragment(R.layout.fragment_blood_pressure_card) {

    private lateinit var btnBack: ImageView
    private lateinit var etBloodPressure: EditText
    private lateinit var tvLastValue: TextView
    private lateinit var btnSave: MaterialButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnBack = view.findViewById(R.id.btnBack)
        etBloodPressure = view.findViewById(R.id.etBloodPressure)
        tvLastValue = view.findViewById(R.id.tvLastValue)
        btnSave = view.findViewById(R.id.btnSaveBP)

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnSave.setOnClickListener {

            val value = etBloodPressure.text.toString()

            if (value.isEmpty()) {
                Toast.makeText(requireContext(), "Enter blood pressure", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            tvLastValue.text = "Last recorded: $value mmHg"

            Toast.makeText(requireContext(), "Blood Pressure saved", Toast.LENGTH_SHORT).show()

            val bp = etBloodPressure.text.toString()

            if (!bp.contains("/")) {
                Toast.makeText(requireContext(), "Enter BP like 120/80", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }
}