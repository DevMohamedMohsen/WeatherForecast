package com.weatherforecast.data.feature.forecast

import com.weatherforecast.data.BuildConfig
import com.weatherforecast.data.feature.forecast.remote.ForecastRemoteDataSource
import com.weatherforecast.domain.feature.Resource
import com.weatherforecast.domain.feature.forecast.ForecastRepository
import com.weatherforecast.domain.feature.forecast.entity.Forecast

class ForecastRepositoryImplementer(private val remoteDataSource: ForecastRemoteDataSource) : ForecastRepository {

    override suspend fun getForecast(city: String): Resource<List<Forecast>> {
        return remoteDataSource.getForecast(city, BuildConfig.OPEN_WEATHER_MAP_ID)
    }
}