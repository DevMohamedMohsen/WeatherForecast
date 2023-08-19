package com.weatherforecast.data.di

import com.weatherforecast.data.feature.country.CountryRepositoryImplementer
import com.weatherforecast.data.feature.country.remote.CountryRemoteDataSource
import com.weatherforecast.data.feature.forecast.ForecastRepositoryImplementer
import com.weatherforecast.data.feature.forecast.remote.ForecastRemoteDataSource
import com.weatherforecast.domain.feature.country.CountryRepository
import com.weatherforecast.domain.feature.forecast.ForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCountry(remoteDataSource: CountryRemoteDataSource): CountryRepository =
        CountryRepositoryImplementer(remoteDataSource)

    @Singleton
    @Provides
    fun provideForecast(remoteDataSource: ForecastRemoteDataSource): ForecastRepository =
        ForecastRepositoryImplementer(remoteDataSource)
}