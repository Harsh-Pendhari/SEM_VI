package com.example.myhealth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class EditMedicineFragment : Fragment() {
    private lateinit var etMedicineName: EditText
    private lateinit var etDosage: EditText
    private lateinit var etTime: EditText
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button

    private var medicineId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_edit_medicine, container, false)

        etMedicineName = view.findViewById(R.id.etMedicineName)
        etDosage = view.findViewById(R.id.etDosage)
        etTime = view.findViewById(R.id.etTime)
        btnSave = view.findViewById(R.id.btnSaveChanges)
        btnDelete = view.findViewById(R.id.btnDeleteMedicine)

        loadMedicineData()
        setupButtons()

        etTime.setOnClickListener {
            openTimePicker()
        }

        return view
    }

    private fun openTimePicker() {

        val calendar = java.util.Calendar.getInstance()

        val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)
        val minute = calendar.get(java.util.Calendar.MINUTE)

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(hour)
            .setMinute(minute)
            .setTitleText("Select Reminder Time")
            .build()

        picker.show(parentFragmentManager, "TIME_PICKER")

        picker.addOnPositiveButtonClickListener {

            val selectedCalendar = java.util.Calendar.getInstance()
            selectedCalendar.set(java.util.Calendar.HOUR_OF_DAY, picker.hour)
            selectedCalendar.set(java.util.Calendar.MINUTE, picker.minute)

            val formatter = java.text.SimpleDateFormat("hh:mm a", java.util.Locale.getDefault())
            val formattedTime = formatter.format(selectedCalendar.time)

            etTime.setText(formattedTime)
        }

    }

    private fun loadMedicineData() {

        arguments?.let {

            medicineId = it.getInt("id", -1)

            val name = it.getString("name") ?: ""
            val dosage = it.getString("dosage") ?: ""
            val timing = it.getString("timing") ?: ""

            etMedicineName.setText(name)
            etDosage.setText(dosage)
            etTime.setText(timing)
        }
    }

    private fun setupButtons() {

        btnSave.setOnClickListener {

            val name = etMedicineName.text.toString().trim()
            val dosage = etDosage.text.toString().trim()
            val timing = etTime.text.toString().trim()

            if (name.isEmpty() || dosage.isEmpty() || timing.isEmpty()) {

                Toast.makeText(
                    context,
                    "Please fill all fields",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val result = Bundle()
            result.putInt("id", medicineId)
            result.putString("name", name)
            result.putString("dosage", dosage)
            result.putString("timing", timing)

            parentFragmentManager.setFragmentResult("editMedicine", result)
            parentFragmentManager.popBackStack()
        }

        btnDelete.setOnClickListener {

            val result = Bundle()
            result.putInt("deleteId", medicineId)

            parentFragmentManager.setFragmentResult("deleteMedicine", result)
            parentFragmentManager.popBackStack()
        }
    }
}
