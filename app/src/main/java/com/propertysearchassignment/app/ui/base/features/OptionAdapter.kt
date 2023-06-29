package com.propertysearchassignment.app.ui.base.features

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.propertysearchassignment.app.R
import com.propertysearchassignment.app.model.data.Option
import kotlinx.android.synthetic.main.item_option.view.optionIconImageView
import kotlinx.android.synthetic.main.item_option.view.optionNameTextView

class OptionAdapter(private val options: List<Option>,
                    private val optionClickListener: (Option) -> Unit) :
    RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_option, parent, false)
        return OptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        val option = options[position]
        holder.bind(option)
    }

    override fun getItemCount(): Int {
        return options.size
    }

    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(option: Option) {
            // Bind option data to the views in the item layout
            itemView.optionNameTextView.text = option.name
            itemView.setOnClickListener {
                // Invoke the click listener with the selected option
                optionClickListener(option)
            }
            // Load the option icon using Glide or any other image loading library
            when(option.icon)
            {
                "apartment" -> itemView.optionIconImageView.setImageResource(R.drawable.apartment)
                "land" -> itemView.optionIconImageView.setImageResource(R.drawable.land)
                "condo" -> itemView.optionIconImageView.setImageResource(R.drawable.condo)
                "boat" -> itemView.optionIconImageView.setImageResource(R.drawable.boat)
                "rooms" -> itemView.optionIconImageView.setImageResource(R.drawable.rooms)
                "no-rooms" -> itemView.optionIconImageView.setImageResource(R.drawable.noroom)
                "swimming" -> itemView.optionIconImageView.setImageResource(R.drawable.swimming)
                "garden" -> itemView.optionIconImageView.setImageResource(R.drawable.garden)
                "garage" -> itemView.optionIconImageView.setImageResource(R.drawable.garage)
            }
//            Glide.with(this).load(image_url).apply(options).into(itemView.optionIconImageView);
//            Glide.with(itemView)
//                .load(option.icon)
//                .into(itemView.optionIconImageView)
        }
    }
}
