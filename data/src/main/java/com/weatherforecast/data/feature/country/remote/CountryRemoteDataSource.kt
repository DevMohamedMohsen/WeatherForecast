package com.weatherforecast.data.feature.country.remote

import com.weatherforecast.data.feature.callCountriesNow
import com.weatherforecast.data.feature.country.remote.entity.asDomainCountries
import com.weatherforecast.domain.feature.Resource
import com.weatherforecast.domain.feature.country.entity.Country

interface CountryRemoteDataSource {
    suspend fun getCountries(): Resource<List<Country>>
}

class CountryRemoteDataSourceImplementer(private val remoteAPI: CountryRemoteAPI) : CountryRemoteDataSource {

    override suspend fun getCountries(): Resource<List<Country>> {
        return when (val result = callCountriesNow { remoteAPI.getCountries() }) {
            is Resource.Success -> Resource.Success(result.data.asDomainCountries())
            is Resource.Error -> result
        }
    }
}