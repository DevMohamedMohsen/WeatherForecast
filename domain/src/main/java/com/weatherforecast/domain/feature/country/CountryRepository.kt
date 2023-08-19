package com.weatherforecast.domain.feature.country

import com.weatherforecast.domain.feature.Resource
import com.weatherforecast.domain.feature.country.entity.Country

interface CountryRepository {
    suspend fun getCountries(): Resource<List<Country>>
}