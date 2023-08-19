package com.weatherforecast.data.feature.forecast.remote

import com.weatherforecast.data.feature.callOpenWeatherMap
import com.weatherforecast.data.feature.forecast.remote.entity.asDomainForecast
import com.weatherforecast.domain.feature.Resource
import com.weatherforecast.domain.feature.forecast.entity.Forecast

interface ForecastRemoteDataSource {
    suspend fun getForecast(city: String, appID: String): Resource<List<Forecast>>
}

class ForecastRemoteDataSourceImplementer(private val remoteAPI: ForecastRemoteAPI) : ForecastRemoteDataSource {

    override suspend fun getForecast(city: String, appID: String): Resource<List<Forecast>> {
        return when (val result = callOpenWeatherMap { remoteAPI.getForecast(city, appID) }) {
            is Resource.Success -> Resource.Success(result.data.asDomainForecast())
            is Resource.Error -> result
        }
    }
}