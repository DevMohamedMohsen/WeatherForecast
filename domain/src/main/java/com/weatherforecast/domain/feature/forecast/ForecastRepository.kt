package com.weatherforecast.domain.feature.forecast

import com.weatherforecast.domain.feature.Resource
import com.weatherforecast.domain.feature.forecast.entity.Forecast

interface ForecastRepository {
    suspend fun getForecast(city: String): Resource<List<Forecast>>
}