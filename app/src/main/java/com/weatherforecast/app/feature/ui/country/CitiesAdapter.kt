package com.weatherforecast.app.feature.ui.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.weatherforecast.app.databinding.CityItemBinding

class CitiesAdapter(
    private var onCityItemClick: (name: String) -> Unit = {}
) : ListAdapter<String, CitiesAdapter.CityViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position), onCityItemClick)
    }

    class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
    }

    class CityViewHolder(private val binding: CityItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): CityViewHolder {
                return CityViewHolder(CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }

        fun bind(name: String, onCityItemClick: (name: String) -> Unit) {
            binding.apply {
                tvCityName.text = name
                itemView.setOnClickListener {
                    onCityItemClick(name)
                }
            }
        }
    }
}