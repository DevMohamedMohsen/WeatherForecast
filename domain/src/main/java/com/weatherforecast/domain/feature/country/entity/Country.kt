package com.weatherforecast.domain.feature.country.entity

data class Country(
    val name: String = "",
    val cities: List<String> = emptyList(),
    var isExpanded: Boolean = false
)