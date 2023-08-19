package com.weatherforecast.domain.feature.forecast.entity

data class Forecast(
    val timestamp: Long = 0,
    val datetime: String = "",
    val temperature: Int = 0,
    val icon: String = "",
    val description: String = ""
)