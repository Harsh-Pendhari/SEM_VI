package com.example.myhealth

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class MedicinesFragment : Fragment(R.layout.fragment_medicines) {

    // Header
    private lateinit var ivMedicineIcon: ImageView
    private lateinit var ivMyMedicinesText: ImageView
    private lateinit var ivProfile: ImageView

    // Medicine cards (static for now)
    private lateinit var medicineCard1: FrameLayout
    private lateinit var medicineCard2: FrameLayout
    private lateinit var medicineCard3: FrameLayout

    // Buttons
    private lateinit var btnAddMedicine: MaterialButton
    private lateinit var btnMedicineReminder: MaterialButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Header
        ivProfile = view.findViewById(R.id.ivProfile)

        // Medicine cards
        medicineCard1 = view.findViewById(R.id.medicineCard1)
        medicineCard2 = view.findViewById(R.id.medicineCard2)
        medicineCard3 = view.findViewById(R.id.medicineCard3)

        // Buttons
        btnAddMedicine = view.findViewById(R.id.btnAddMedicine)
        btnMedicineReminder = view.findViewById(R.id.btnMedicineReminder)
    }
}
