package com.weatherforecast.data.feature.forecast.remote.entity

import com.weatherforecast.data.feature.getOrDefault
import com.weatherforecast.domain.feature.forecast.entity.Forecast

data class RemoteForecast(
    val dt: Long? = null,
    val dt_txt: String? = null,
    val main: Main? = null,
    val weather: List<Weather?>? = null
) {
    data class Main(
        val temp: Double? = null
    )

    data class Weather(
        val icon: String? = null,
        val description: String? = null
    )
}

fun RemoteForecast?.asDomainForecast(): Forecast =
    this?.let { remoteForecast ->
        Forecast(
            timestamp = remoteForecast.dt.getOrDefault(),
            datetime = remoteForecast.dt_txt.getOrDefault(),
            temperature = remoteForecast.main?.temp?.toInt().getOrDefault(),
            icon = getIconURL(remoteForecast.weather?.first()?.icon),
            description = remoteForecast.weather?.first()?.description.getOrDefault()
        )
    } ?: Forecast()

fun getIconURL(icon: String?): String =
    if (!icon.isNullOrBlank()) "https://openweathermap.org/img/wn/$icon@4x.png" else ""

fun List<RemoteForecast?>?.asDomainForecast(): List<Forecast> =
    this?.map { it.asDomainForecast() } ?: emptyList()