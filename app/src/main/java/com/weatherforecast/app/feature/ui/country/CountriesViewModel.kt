package com.weatherforecast.app.feature.ui.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherforecast.domain.feature.Resource
import com.weatherforecast.domain.feature.country.entity.Country
import com.weatherforecast.domain.feature.country.usecase.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(private val getCountriesUseCase: GetCountriesUseCase) : ViewModel() {

    private val _isProgressVisible = MutableStateFlow(true)
    val isProgressVisible: StateFlow<Boolean> = _isProgressVisible

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    private val _throwable = MutableSharedFlow<Throwable>()
    val throwable: SharedFlow<Throwable> = _throwable

    init {
        getCountries()
    }

    fun getCountries() = viewModelScope.launch {
        when (val result = getCountriesUseCase()) {
            is Resource.Success -> _countries.value = result.data
            is Resource.Error -> _throwable.emit(result.throwable)
        }
        _isProgressVisible.value = false
    }
}