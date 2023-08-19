package com.weatherforecast.domain.feature.country.usecase

import com.weatherforecast.domain.feature.Resource
import com.weatherforecast.domain.feature.country.CountryRepository
import com.weatherforecast.domain.feature.country.entity.Country
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetCountriesUseCase(private val coroutineDispatcher: CoroutineDispatcher, private val countryRepository: CountryRepository) {
    suspend operator fun invoke(): Resource<List<Country>> {
        return withContext(coroutineDispatcher) {
            countryRepository.getCountries()
        }
    }
}