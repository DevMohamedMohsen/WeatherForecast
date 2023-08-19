package com.weatherforecast.domain.feature.forecast.usecase

import com.weatherforecast.domain.feature.Resource
import com.weatherforecast.domain.feature.forecast.ForecastRepository
import com.weatherforecast.domain.feature.forecast.entity.Forecast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetForecastUseCase(private val coroutineDispatcher: CoroutineDispatcher, private val forecastRepository: ForecastRepository) {
    suspend operator fun invoke(city: String): Resource<List<Forecast>> {
        return withContext(coroutineDispatcher) {
            forecastRepository.getForecast(city)
        }
    }
}