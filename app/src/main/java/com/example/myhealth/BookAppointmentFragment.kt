package com.example.myhealth

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import java.util.*

class BookAppointmentFragment : Fragment(R.layout.fragment_book_appointment) {

    private lateinit var spinnerDoctors: Spinner
    private lateinit var etDate: EditText
    private lateinit var etTime: EditText
    private lateinit var btnBook: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinnerDoctors = view.findViewById(R.id.spinnerDoctors)
        etDate = view.findViewById(R.id.etAppointmentDate)
        etTime = view.findViewById(R.id.etAppointmentTime)
        btnBook = view.findViewById(R.id.btnBookAppointment)

        setupDoctorDropdown()
        setupDatePicker()
        setupTimePicker()

        btnBook.setOnClickListener {
            bookAppointment()
        }

        val profileIcon = view.findViewById<ImageView?>(R.id.profileIcon)
        val profileName = view.findViewById<TextView?>(R.id.profileName)
        val profileAge = view.findViewById<TextView?>(R.id.profileAge)

        if (profileIcon != null && profileName != null && profileAge != null) {
            ProfileManager.loadProfile(profileName, profileAge, profileIcon)
        }
    }

    private fun setupDoctorDropdown() {

        val doctors = listOf(
            "Select Doctor",
            "Dr. Sharma (Cardiologist)",
            "Dr. Mehta (Family Doctor)",
            "Dr. Patel (Neurologist)"
        )

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item_selected,
            doctors
        )

        adapter.setDropDownViewResource(R.layout.spinner_item_dropdown)

        spinnerDoctors.adapter = adapter
    }

    private fun setupDatePicker() {

        etDate.setOnClickListener {

            val calendar = Calendar.getInstance()

            val dialog = DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    etDate.setText("$day/${month+1}/$year")
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            dialog.show()
        }
    }

    private fun setupTimePicker() {

        etTime.setOnClickListener {

            val calendar = Calendar.getInstance()

            val dialog = TimePickerDialog(
                requireContext(),
                { _, hour, minute ->
                    etTime.setText(String.format("%02d:%02d", hour, minute))
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            )

            dialog.show()
        }
    }

    private fun bookAppointment() {

        val doctor = spinnerDoctors.selectedItem.toString()
        val date = etDate.text.toString()
        val time = etTime.text.toString()

        if (doctor == "Select Doctor" || date.isEmpty() || time.isEmpty()) {

            Toast.makeText(
                requireContext(),
                "Please fill all details",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        Toast.makeText(
            requireContext(),
            "Appointment booked successfully",
            Toast.LENGTH_SHORT
        ).show()

        // Later save to DB
    }
}