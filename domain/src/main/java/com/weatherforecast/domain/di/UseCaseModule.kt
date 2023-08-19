package com.weatherforecast.domain.di

import com.weatherforecast.domain.feature.country.CountryRepository
import com.weatherforecast.domain.feature.country.usecase.GetCountriesUseCase
import com.weatherforecast.domain.feature.forecast.ForecastRepository
import com.weatherforecast.domain.feature.forecast.usecase.GetForecastUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetCountries(coroutineDispatcher: CoroutineDispatcher, countryRepository: CountryRepository): GetCountriesUseCase =
        GetCountriesUseCase(coroutineDispatcher, countryRepository)

    @Singleton
    @Provides
    fun provideGetForecast(coroutineDispatcher: CoroutineDispatcher, forecastRepository: ForecastRepository): GetForecastUseCase =
        GetForecastUseCase(coroutineDispatcher, forecastRepository)
}