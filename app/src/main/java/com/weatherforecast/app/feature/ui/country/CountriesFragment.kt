package com.weatherforecast.app.feature.ui.country

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.weatherforecast.app.R
import com.weatherforecast.app.databinding.CountriesFragmentBinding
import com.weatherforecast.app.feature.util.ThrowableManager
import com.weatherforecast.domain.feature.country.entity.Country
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CountriesFragment : Fragment(R.layout.countries_fragment) {

    private var _binding: CountriesFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CountriesViewModel by viewModels()
    private val countriesAdapter: CountriesAdapter = CountriesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = CountriesFragmentBinding.bind(view)
        init()
        collectStates()
    }

    private fun init() {
        binding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.getCountries()
                binding.swipeRefreshLayout.isRefreshing = false
            }
            rvCountries.adapter = countriesAdapter
            countriesAdapter.onCityItemClick = { city: String ->
                navigateToForecast(city)
            }
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
                    countries.collect {
                        onCountriesCollected(it)
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

    private fun onCountriesCollected(list: List<Country>) {
        binding.apply {
            rvCountries.isVisible = true
            countriesAdapter.submitList(list)
            tvErrorMessage.isVisible = false
        }
    }

    private fun onThrowableCollected(throwable: Throwable) {
        binding.apply {
            rvCountries.isVisible = false
            tvErrorMessage.isVisible = true
            tvErrorMessage.text = ThrowableManager.getErrorMessage(requireContext(), throwable)
        }
    }

    private fun navigateToForecast(city: String) {
        findNavController().navigate(CountriesFragmentDirections.actionCountriesFragmentToForecastFragment(city))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}