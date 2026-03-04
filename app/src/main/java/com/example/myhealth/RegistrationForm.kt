package com.example.myhealth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class RegistrationForm : AppCompatActivity() {

    // User details
    private lateinit var genderGroup: RadioGroup
    private lateinit var etAge: EditText
    private lateinit var etWeight: EditText
    private lateinit var etMedicalHistory: EditText

    // Doctor inputs
    private lateinit var etDoctorName: EditText
    private lateinit var etDoctorType: EditText
    private lateinit var etClinicName: EditText

    // Buttons
    private lateinit var btnAddDoctor: MaterialButton
    private lateinit var btnSaveProfile: MaterialButton

    // Container where added doctors appear
    private lateinit var addedDoctorsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_form_activity)

        // Bind user fields
        genderGroup = findViewById(R.id.genderGroup)
        etAge = findViewById(R.id.etAge)
        etWeight = findViewById(R.id.etWeight)
        etMedicalHistory = findViewById(R.id.etMedicalHistory)

        // Bind doctor input fields
        etDoctorName = findViewById(R.id.etDoctorName)
        etDoctorType = findViewById(R.id.etDoctorType)
        etClinicName = findViewById(R.id.etClinicName)

        // Buttons
        btnAddDoctor = findViewById(R.id.btnAddDoctor)
        btnSaveProfile = findViewById(R.id.btnSaveProfile)

        // Container
        addedDoctorsContainer = findViewById(R.id.addedDoctorsContainer)

        // Add Doctor logic
        btnAddDoctor.setOnClickListener {
            addDoctor()
        }

        // Save profile logic
        btnSaveProfile.setOnClickListener {
            saveProfile()
        }
    }

    private fun addDoctor() {

        val name = etDoctorName.text.toString().trim()
        val type = etDoctorType.text.toString().trim()
        val clinic = etClinicName.text.toString().trim()

        if (name.isEmpty() || type.isEmpty() || clinic.isEmpty()) {
            Toast.makeText(this, "Fill doctor details", Toast.LENGTH_SHORT).show()
            return
        }

        val doctorView = LayoutInflater.from(this)
            .inflate(R.layout.item_added_doctor, addedDoctorsContainer, false)

        val tvDoctorName = doctorView.findViewById<TextView>(R.id.tvDoctorName)
        val tvDoctorType = doctorView.findViewById<TextView>(R.id.tvDoctorType)
        val tvClinic = doctorView.findViewById<TextView>(R.id.tvClinic)

        tvDoctorName.text = name
        tvDoctorType.text = type
        tvClinic.text = clinic

        addedDoctorsContainer.addView(doctorView)

        // Clear input fields after adding
        etDoctorName.text.clear()
        etDoctorType.text.clear()
        etClinicName.text.clear()
    }

    private fun saveProfile() {

        val age = etAge.text.toString().trim()
        val weight = etWeight.text.toString().trim()
        val history = etMedicalHistory.text.toString().trim()

        val selectedGenderId = genderGroup.checkedRadioButtonId

        if (selectedGenderId == -1 || age.isEmpty() || weight.isEmpty()) {
            Toast.makeText(this, "Please fill all required details", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedGender = findViewById<RadioButton>(selectedGenderId).text.toString()

        // Later you can store this in database or shared preferences

        Toast.makeText(this, "Profile Saved", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }
}