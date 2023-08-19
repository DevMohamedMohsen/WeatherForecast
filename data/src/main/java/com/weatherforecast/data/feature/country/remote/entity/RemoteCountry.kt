package com.weatherforecast.data.feature.country.remote.entity

import com.weatherforecast.data.feature.getOrDefault
import com.weatherforecast.domain.feature.country.entity.Country

data class RemoteCountry(
    val country: String? = null,
    val cities: List<String?>? = null
)

fun RemoteCountry?.asDomainCountry(): Country =
    this?.let { remoteCountry ->
        Country(
            name = remoteCountry.country.getOrDefault(),
            cities = remoteCountry.cities?.take(20)?.map { it.getOrDefault() } ?: emptyList()
        )
    } ?: Country()

fun List<RemoteCountry?>?.asDomainCountries(): List<Country> =
    this?.map { it.asDomainCountry() } ?: emptyList()