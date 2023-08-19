package com.weatherforecast.app.feature.ui.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.weatherforecast.app.R
import com.weatherforecast.app.databinding.ForecastItemBinding
import com.weatherforecast.app.feature.util.formatTimeStampToDateTime
import com.weatherforecast.domain.feature.forecast.entity.Forecast

class ForecastAdapter : ListAdapter<Forecast, ForecastAdapter.ForecastViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Forecast>() {
        override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean = oldItem.timestamp == newItem.timestamp
        override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast): Boolean = oldItem == newItem
    }

    class ForecastViewHolder(private val binding: ForecastItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ForecastViewHolder {
                return ForecastViewHolder(ForecastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }

        fun bind(forecast: Forecast) {
            binding.apply {
                tvDateTime.text = forecast.timestamp.formatTimeStampToDateTime()
                ivIcon.load(forecast.icon)
                tvTemperature.text = itemView.context.getString(R.string.concatenate_temperature, forecast.temperature)
            }
        }
    }
}