package com.example.myhealth

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class StepsFragment : Fragment(R.layout.fragment_steps) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        val etSteps = view.findViewById<EditText>(R.id.etSteps)
        val btnSave = view.findViewById<MaterialButton>(R.id.btnSaveSteps)

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnSave.setOnClickListener {

            val steps = etSteps.text.toString()

            if (steps.isEmpty()) {
                Toast.makeText(requireContext(),"Enter steps",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(requireContext(),"Steps saved",Toast.LENGTH_SHORT).show()
        }
    }
}