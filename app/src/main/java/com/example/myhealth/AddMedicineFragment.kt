package com.example.myhealth

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.button.MaterialButton
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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
        val calendar = java.util.Calendar.getInstance()

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(calendar.get(java.util.Calendar.HOUR_OF_DAY))
            .setMinute(calendar.get(java.util.Calendar.MINUTE))
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

        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val db = FirebaseDatabase.getInstance().reference

        val medicineId = db.child("users").child(uid).child("medicines").push().key!!

        val medicine = HashMap<String, Any>()
        medicine["name"] = name
        medicine["dosage"] = dosage
        medicine["frequency"] = frequency
        medicine["time"] = time

        db.child("users")
            .child(uid)
            .child("medicines")
            .child(medicineId)
            .setValue(medicine)

        scheduleMedicineReminder(name, time)

        toast("Medicine Saved")

        parentFragmentManager.popBackStack()
    }

    private fun scheduleMedicineReminder(medicineName: String, time: String) {

        val context = requireContext()

        val intent = Intent(context, MedicineReminderReceiver::class.java)
        intent.putExtra("medicineName", medicineName)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            medicineName.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val date = formatter.parse(time)

        val reminderCalendar = Calendar.getInstance()
        reminderCalendar.time = date!!

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, reminderCalendar.get(Calendar.HOUR_OF_DAY))
        calendar.set(Calendar.MINUTE, reminderCalendar.get(Calendar.MINUTE))
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }

    private fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}