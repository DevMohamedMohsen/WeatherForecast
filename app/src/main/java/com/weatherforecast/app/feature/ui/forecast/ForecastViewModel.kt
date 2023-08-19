package com.weatherforecast.app.feature.ui.forecast

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherforecast.domain.feature.Resource
import com.weatherforecast.domain.feature.forecast.entity.Forecast
import com.weatherforecast.domain.feature.forecast.usecase.GetForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val getForecastUseCase: GetForecastUseCase, savedStateHandle: SavedStateHandle) : ViewModel() {

    private val city: String? = savedStateHandle["city"]

    private val _isProgressVisible = MutableStateFlow(true)
    val isProgressVisible: StateFlow<Boolean> = _isProgressVisible

    private val _forecast = MutableStateFlow<List<Forecast>>(emptyList())
    val forecast: StateFlow<List<Forecast>> = _forecast

    private val _throwable = MutableSharedFlow<Throwable>()
    val throwable: SharedFlow<Throwable> = _throwable

    init {
        city?.let { getForecast(it) }
    }

    private fun getForecast(city: String) = viewModelScope.launch {
        when (val result = getForecastUseCase(city)) {
            is Resource.Success -> _forecast.value = result.data
            is Resource.Error -> _throwable.emit(result.throwable)
        }
        _isProgressVisible.value = false
    }
}