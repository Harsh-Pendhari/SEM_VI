package com.example.myhealth

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MedicineAdapter(
    private val medicines: List<Medicine>,
    private val onEditClick: (Medicine) -> Unit
) : RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder>() {

    class MedicineViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.tvMedicineName)
        val details: TextView = view.findViewById(R.id.tvMedicineDetails)
        val edit: TextView = view.findViewById(R.id.tvEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medicine_card, parent, false)

        return MedicineViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {

        val medicine = medicines[position]

        holder.name.text = medicine.name
        val doseText =
            if (medicine.dosage == "1") "1 tablet"
            else "${medicine.dosage} tablets"

        val freqText =
            if (medicine.frequency == "1") "Once a day"
            else "${medicine.frequency} times a day"

        holder.details.text =
            "$doseText | $freqText | ${medicine.time}"

        holder.edit.setOnClickListener {
            onEditClick(medicine)
        }
    }

    override fun getItemCount(): Int = medicines.size
}