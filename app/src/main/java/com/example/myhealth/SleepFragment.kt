package com.example.myhealth

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class SleepFragment : Fragment(R.layout.fragment_sleep) {

    private lateinit var btnBack: ImageView
    private lateinit var etSleep: EditText
    private lateinit var btnSave: MaterialButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnBack = view.findViewById(R.id.btnBack)
        etSleep = view.findViewById(R.id.etSleep)
        btnSave = view.findViewById(R.id.btnSaveSleep)

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnSave.setOnClickListener {

            val sleep = etSleep.text.toString()

            if (sleep.isEmpty()) {
                Toast.makeText(requireContext(), "Enter sleep duration", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(requireContext(), "Sleep data saved", Toast.LENGTH_SHORT).show()
        }
    }
}