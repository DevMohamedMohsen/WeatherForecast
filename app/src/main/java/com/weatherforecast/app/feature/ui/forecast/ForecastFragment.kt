package com.weatherforecast.app.feature.ui.forecast

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.weatherforecast.app.R
import com.weatherforecast.app.databinding.ForecastFragmentBinding
import com.weatherforecast.app.feature.util.ThrowableManager
import com.weatherforecast.app.feature.util.formatTimeStampToDateTime
import com.weatherforecast.domain.feature.forecast.entity.Forecast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForecastFragment : Fragment(R.layout.forecast_fragment) {

    private var _binding: ForecastFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: ForecastFragmentArgs by navArgs()
    private val viewModel: ForecastViewModel by viewModels()
    private val forecastAdapter: ForecastAdapter = ForecastAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ForecastFragmentBinding.bind(view)
        init()
        collectStates()
    }

    private fun init() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun collectStates() {
        viewModel.apply {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    isProgressVisible.collect {
                        binding.progressbar.isVisible = it
                    }
                }
            }
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    forecast.collect {
                        onForecastCollected(it)
                    }
                }
            }
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    throwable.collect {
                        onThrowableCollected(it)
                    }
                }
            }
        }
    }

    private fun onForecastCollected(forecastList: List<Forecast>) {
        if (forecastList.isNotEmpty()) {
            binding.apply {
                forecastList.first().also { forecast: Forecast ->
                    gContainer.isVisible = true
                    tvTemperature.text = getString(R.string.concatenate_temperature, forecast.temperature)
                    ivIcon.load(forecast.icon)
                    tvDescription.text = forecast.description
                    tvCity.text = args.city
                    tvDateTime.text = forecast.timestamp.formatTimeStampToDateTime()
                }
                rvForecast.adapter = forecastAdapter
                forecastAdapter.submitList(forecastList)
            }
        }
    }

    private fun onThrowableCollected(throwable: Throwable) {
        binding.apply {
            gContainer.isVisible = false
            tvErrorMessage.isVisible = true
            tvErrorMessage.text = ThrowableManager.getErrorMessage(requireContext(), throwable)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}