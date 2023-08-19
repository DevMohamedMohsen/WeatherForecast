package com.weatherforecast.app.feature.ui.country

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.weatherforecast.app.R
import com.weatherforecast.app.databinding.CountryItemBinding
import com.weatherforecast.domain.feature.country.entity.Country

class CountriesAdapter(
    var onCityItemClick: (name: String) -> Unit = {}
) : ListAdapter<Country, CountriesAdapter.CountryViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(getItem(position), onCityItemClick)
    }

    class DiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean = oldItem == newItem
    }

    class CountryViewHolder(private val binding: CountryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): CountryViewHolder {
                return CountryViewHolder(CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }

        fun bind(country: Country, onCityItemClick: (name: String) -> Unit) {
            binding.apply {
                tvCountryName.text = country.name
                toggleCitiesVisibility(binding, country, onCityItemClick)
                itemView.setOnClickListener {
                    country.isExpanded = !country.isExpanded
                    toggleCitiesVisibility(binding, country, onCityItemClick)
                }
            }
        }

        private fun toggleCitiesVisibility(binding: CountryItemBinding, country: Country, onCityItemClick: (name: String) -> Unit) {
            binding.apply {
                when (country.isExpanded) {
                    true -> {
                        tvCountryName.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_up, 0)
                        rvCities.visibility = View.VISIBLE
                        initCitiesAdapter(binding.rvCities, country.cities, onCityItemClick)
                    }

                    false -> {
                        tvCountryName.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0)
                        rvCities.visibility = View.GONE
                    }
                }
            }
        }

        private fun initCitiesAdapter(rvCities: RecyclerView, cities: List<String>, onCityItemClick: (name: String) -> Unit) {
            val citiesAdapter = CitiesAdapter(onCityItemClick)
            citiesAdapter.submitList(cities)
            rvCities.adapter = citiesAdapter
        }
    }
}