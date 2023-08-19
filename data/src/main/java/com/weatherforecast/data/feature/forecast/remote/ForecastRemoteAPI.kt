package com.weatherforecast.data.feature.forecast.remote

import com.weatherforecast.data.feature.RemoteOpenWeatherMapResult
import com.weatherforecast.data.feature.forecast.remote.entity.RemoteForecast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastRemoteAPI {

    @GET("2.5/forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") appid: String,
        @Query("units") units: String = "metric"
    ): Response<RemoteOpenWeatherMapResult<List<RemoteForecast>>>
}