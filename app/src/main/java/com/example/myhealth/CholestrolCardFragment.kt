package com.example.myhealth

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class CholestrolCardFragment : Fragment(R.layout.fragment_cholestrol_card) {

    private lateinit var btnBack: ImageView
    private lateinit var etLDL: EditText
    private lateinit var etHDL: EditText
    private lateinit var btnSave: MaterialButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnBack = view.findViewById(R.id.btnBack)
        etLDL = view.findViewById(R.id.etLDL)
        etHDL = view.findViewById(R.id.etHDL)
        btnSave = view.findViewById(R.id.btnSaveCholesterol)

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnSave.setOnClickListener {

            val ldl = etLDL.text.toString()
            val hdl = etHDL.text.toString()

            if (ldl.isEmpty() || hdl.isEmpty()) {
                Toast.makeText(requireContext(), "Enter both values", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(requireContext(), "Cholesterol data saved", Toast.LENGTH_SHORT).show()
        }
    }
}