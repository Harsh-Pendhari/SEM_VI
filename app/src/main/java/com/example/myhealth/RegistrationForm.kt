package com.example.myhealth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistrationForm : AppCompatActivity() {

    // User fields
    private lateinit var genderGroup: RadioGroup
    private lateinit var etAge: EditText
    private lateinit var etWeight: EditText
    private lateinit var etMedicalHistory: EditText

    // Buttons
    private lateinit var btnAddDoctor: MaterialButton
    private lateinit var btnSaveProfile: MaterialButton

    // Containers
    private lateinit var doctorsContainer: LinearLayout
    private lateinit var addedDoctorsContainer: LinearLayout

    // Firebase
    private lateinit var auth: FirebaseAuth
    private val database = FirebaseDatabase.getInstance()

    // Store doctors before saving
    private val doctorsList = mutableListOf<HashMap<String, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_form_activity)

        auth = FirebaseAuth.getInstance()

        genderGroup = findViewById(R.id.genderGroup)
        etAge = findViewById(R.id.etAge)
        etWeight = findViewById(R.id.etWeight)
        etMedicalHistory = findViewById(R.id.etMedicalHistory)

        btnAddDoctor = findViewById(R.id.btnAddDoctor)
        btnSaveProfile = findViewById(R.id.btnSaveProfile)

        doctorsContainer = findViewById(R.id.doctorsContainer)
        addedDoctorsContainer = findViewById(R.id.addedDoctorsContainer)

        btnAddDoctor.setOnClickListener {
            showDoctorInputCard()
        }

        btnSaveProfile.setOnClickListener {
            saveProfile()
        }
    }

    private fun showDoctorInputCard() {

        val doctorView = LayoutInflater.from(this)
            .inflate(R.layout.item_doctor_input, doctorsContainer, false)

        val etDoctorName = doctorView.findViewById<EditText>(R.id.etDoctorName)
        val etDoctorContact = doctorView.findViewById<EditText>(R.id.etDoctorContact)
        val etDoctorType = doctorView.findViewById<EditText>(R.id.etDoctorType)
        val etClinicName = doctorView.findViewById<EditText>(R.id.etClinicName)

        val btnAddDoctor = doctorView.findViewById<MaterialButton>(R.id.btnAddDoctor)

        btnAddDoctor.setOnClickListener {

            val name = etDoctorName.text.toString().trim()
            val contact = etDoctorContact.text.toString().trim()
            val type = etDoctorType.text.toString().trim()
            val clinic = etClinicName.text.toString().trim()

            if (name.isEmpty() || contact.isEmpty() || type.isEmpty() || clinic.isEmpty()) {
                Toast.makeText(this, "Fill doctor details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            addDoctorCard(name, type, clinic)

            val doctor = HashMap<String, String>()
            doctor["name"] = name
            doctor["contact"] = contact
            doctor["type"] = type
            doctor["clinic"] = clinic

            doctorsList.add(doctor)

            doctorsContainer.removeView(doctorView)
        }

        doctorsContainer.addView(doctorView)
    }

    private fun addDoctorCard(name: String, type: String, clinic: String) {

        val doctorView = LayoutInflater.from(this)
            .inflate(R.layout.item_added_doctor, addedDoctorsContainer, false)

        val tvDoctorName = doctorView.findViewById<TextView>(R.id.tvDoctorName)
        val tvDoctorType = doctorView.findViewById<TextView>(R.id.tvDoctorType)
        val tvClinic = doctorView.findViewById<TextView>(R.id.tvClinic)

        tvDoctorName.text = name
        tvDoctorType.text = type
        tvClinic.text = clinic

        addedDoctorsContainer.addView(doctorView)
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

        val uid = auth.currentUser!!.uid

        val userData = HashMap<String, Any>()

        userData["gender"] = selectedGender
        userData["age"] = age
        userData["weight"] = weight
        userData["medicalHistory"] = history
        userData["profileCompleted"] = true
        userData["doctors"] = doctorsList

        database.getReference("users")
            .child(uid)
            .updateChildren(userData)   // IMPORTANT FIX
            .addOnSuccessListener {

                Toast.makeText(this, "Profile Saved", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }
            .addOnFailureListener {

                Toast.makeText(this, "Failed to save profile", Toast.LENGTH_SHORT).show()
            }
    }
}