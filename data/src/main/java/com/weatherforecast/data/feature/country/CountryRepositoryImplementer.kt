package com.weatherforecast.data.feature.country

import com.weatherforecast.data.feature.country.remote.CountryRemoteDataSource
import com.weatherforecast.domain.feature.Resource
import com.weatherforecast.domain.feature.country.CountryRepository
import com.weatherforecast.domain.feature.country.entity.Country

class CountryRepositoryImplementer(private val remoteDataSource: CountryRemoteDataSource) : CountryRepository {

    override suspend fun getCountries(): Resource<List<Country>> {
        return remoteDataSource.getCountries()
    }
}