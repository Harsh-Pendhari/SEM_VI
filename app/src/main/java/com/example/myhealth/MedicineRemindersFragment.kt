package com.example.myhealth

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class MedicineRemindersFragment : Fragment(R.layout.fragment_medicine_reminders) {

    private lateinit var ivBack: ImageView
    private lateinit var btnAddReminder: MaterialButton

    private lateinit var reminderSwitch1: Switch
    private lateinit var reminderSwitch2: Switch
    private lateinit var reminderSwitch3: Switch

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews(view)
        setupClickListeners()
    }

    private fun bindViews(view: View) {

        ivBack = view.findViewById(R.id.ivBack)
//        btnAddReminder = view.findViewById(R.id.btnAddReminder)

        reminderSwitch1 = view.findViewById(R.id.switchReminder)
        reminderSwitch2 = view.findViewById(R.id.switchReminder)
        reminderSwitch3 = view.findViewById(R.id.switchReminder)
    }

    private fun setupClickListeners() {

        // Back button
        ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Add reminder
//        btnAddReminder.setOnClickListener {
//            openTimePicker()
//        }

        // Reminder switches
        reminderSwitch1.setOnCheckedChangeListener { _, isChecked ->
            handleReminderToggle(isChecked)
        }

        reminderSwitch2.setOnCheckedChangeListener { _, isChecked ->
            handleReminderToggle(isChecked)
        }

        reminderSwitch3.setOnCheckedChangeListener { _, isChecked ->
            handleReminderToggle(isChecked)
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

            val time = String.format("%02d:%02d", hour, minute)

            Toast.makeText(requireContext(), "Reminder set at $time", Toast.LENGTH_SHORT).show()

            // Later: schedule alarm here
        }
    }

    private fun handleReminderToggle(isEnabled: Boolean) {

        if (isEnabled) {
            Toast.makeText(requireContext(), "Reminder Enabled", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Reminder Disabled", Toast.LENGTH_SHORT).show()
        }
    }
}