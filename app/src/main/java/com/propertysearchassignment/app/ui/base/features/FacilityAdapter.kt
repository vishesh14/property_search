package com.propertysearchassignment.app.ui.base.features

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.propertysearchassignment.app.R
import com.propertysearchassignment.app.model.data.Facility
import com.propertysearchassignment.app.model.data.Option
import kotlinx.android.synthetic.main.item_facility.view.facilityNameTextView
import kotlinx.android.synthetic.main.item_facility.view.optionsRecyclerView

class FacilityAdapter(private val optionSelectionListener: OptionSelectionListener)  : RecyclerView.Adapter<FacilityAdapter.FacilityViewHolder>() {

    private val facilities: MutableList<Facility> = mutableListOf()

    fun setFacilities(facilities: List<Facility>) {
        this.facilities.clear()
        this.facilities.addAll(facilities)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_facility, parent, false)
        return FacilityViewHolder(view)
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        val facility = facilities[position]
        holder.bind(facility)
    }

    override fun getItemCount(): Int {
        return facilities.size
    }

    inner class FacilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(facility: Facility) {
            // ...
            itemView.facilityNameTextView.text = facility.name
            val optionsAdapter = OptionAdapter(facility.options) { option ->
                optionSelectionListener.onOptionSelected(facility, option)
            }
            itemView.optionsRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            itemView.optionsRecyclerView.adapter = optionsAdapter
        }
    }
    interface OptionSelectionListener {
        fun onOptionSelected(facility: Facility, option: Option)
    }

}
