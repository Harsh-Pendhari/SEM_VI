package com.example.myhealth

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MedicinesFragment : Fragment(R.layout.fragment_medicines) {

    private lateinit var ivProfile: ImageView
    private lateinit var btnAddMedicine: MaterialButton
    private lateinit var btnMedicineReminder: MaterialButton

    private lateinit var recyclerView: RecyclerView

    private val medicines = mutableListOf<Medicine>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profileIcon = view.findViewById<ImageView?>(R.id.profileIcon)
        val profileName = view.findViewById<TextView?>(R.id.profileName)
        val profileAge = view.findViewById<TextView?>(R.id.profileAge)

        if (profileIcon != null && profileName != null && profileAge != null) {
            ProfileManager.loadProfile(profileName, profileAge, profileIcon)
        }

        btnAddMedicine = view.findViewById(R.id.btnAddMedicine)
        btnMedicineReminder = view.findViewById(R.id.btnMedicineReminder)

        recyclerView = view.findViewById(R.id.medicinesRecycler)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadMedicines()

        btnAddMedicine.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddMedicineFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun loadMedicines() {

        val uid = FirebaseAuth.getInstance().currentUser!!.uid

        val ref = FirebaseDatabase.getInstance()
            .getReference("users")
            .child(uid)
            .child("medicines")

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                medicines.clear()

                for (medicineSnap in snapshot.children) {

                    val medicine =
                        medicineSnap.getValue(Medicine::class.java)

                    medicine?.id = medicineSnap.key!!

                    if (medicine != null) {
                        medicines.add(medicine)
                    }
                }

                recyclerView.adapter =
                    MedicineAdapter(medicines) { medicine ->
                        openEditPage(medicine)
                    }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun openEditPage(medicine: Medicine) {

        val fragment = EditMedicineFragment()

        val bundle = Bundle()
        bundle.putString("id", medicine.id)
        bundle.putString("name", medicine.name)
        bundle.putString("dosage", medicine.dosage)
        bundle.putString("timing", medicine.time)

        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}