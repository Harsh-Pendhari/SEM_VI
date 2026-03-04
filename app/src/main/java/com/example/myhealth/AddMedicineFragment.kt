package com.example.myhealth

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.button.MaterialButton
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class AddMedicineFragment : Fragment(R.layout.fragment_add_medicine) {

    private lateinit var ivBack: ImageView

    private lateinit var etMedicineName: TextInputEditText
    private lateinit var etDosage: TextInputEditText
    private lateinit var etFrequency: TextInputEditText
    private lateinit var etTime: TextInputEditText

    private lateinit var btnSaveMedicine: MaterialButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews(view)
        setupClickListeners()
    }

    private fun bindViews(view: View) {

        ivBack = view.findViewById(R.id.ivBack)

        etMedicineName = view.findViewById(R.id.etMedicineName)
        etDosage = view.findViewById(R.id.etDosage)
        etFrequency = view.findViewById(R.id.etFrequency)
        etTime = view.findViewById(R.id.etTime)

        btnSaveMedicine = view.findViewById(R.id.btnSaveMedicine)
    }

    private fun setupClickListeners() {

        ivBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        etTime.setOnClickListener {
            openTimePicker()
        }

        btnSaveMedicine.setOnClickListener {
            saveMedicine()
        }
    }

    private fun openTimePicker() {

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(8)
            .setMinute(0)
            .setTitleText("Select Reminder Time")
            .build()

        picker.show(parentFragmentManager, "TIME_PICKER")

        picker.addOnPositiveButtonClickListener {

            val hour = picker.hour
            val minute = picker.minute

            val time = LocalTime.of(hour, minute)

            val formattedTime =
                time.format(DateTimeFormatter.ofPattern("hh:mm a"))

            etTime.setText(formattedTime)
        }
    }

    private fun saveMedicine() {

        val name = etMedicineName.text.toString().trim()
        val dosage = etDosage.text.toString().trim()
        val frequency = etFrequency.text.toString().trim()
        val time = etTime.text.toString().trim()

        if (name.isEmpty()) {
            toast("Enter medicine name")
            return
        }

        if (dosage.isEmpty()) {
            toast("Enter dosage")
            return
        }

        if (time.isEmpty()) {
            toast("Select reminder time")
            return
        }

        // Later this will go to database
        toast("Medicine Saved")

        requireActivity().supportFragmentManager.popBackStack()
    }

    private fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}