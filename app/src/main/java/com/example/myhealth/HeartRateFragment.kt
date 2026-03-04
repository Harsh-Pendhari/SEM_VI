package com.example.myhealth

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class HeartRateFragment : Fragment(R.layout.fragment_heart_rate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        val etHeartRate = view.findViewById<EditText>(R.id.etHeartRate)
        val btnSave = view.findViewById<MaterialButton>(R.id.btnSaveHeartRate)

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnSave.setOnClickListener {

            val rate = etHeartRate.text.toString()

            if (rate.isEmpty()) {
                Toast.makeText(requireContext(),"Enter heart rate",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(requireContext(),"Heart rate saved",Toast.LENGTH_SHORT).show()
        }
    }
}