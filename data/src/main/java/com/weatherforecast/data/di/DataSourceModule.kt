package com.weatherforecast.data.di

import com.weatherforecast.data.feature.country.remote.CountryRemoteAPI
import com.weatherforecast.data.feature.country.remote.CountryRemoteDataSource
import com.weatherforecast.data.feature.country.remote.CountryRemoteDataSourceImplementer
import com.weatherforecast.data.feature.forecast.remote.ForecastRemoteAPI
import com.weatherforecast.data.feature.forecast.remote.ForecastRemoteDataSource
import com.weatherforecast.data.feature.forecast.remote.ForecastRemoteDataSourceImplementer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideCountry(remoteAPI: CountryRemoteAPI): CountryRemoteDataSource =
        CountryRemoteDataSourceImplementer(remoteAPI)

    @Singleton
    @Provides
    fun provideForecast(remoteAPI: ForecastRemoteAPI): ForecastRemoteDataSource =
        ForecastRemoteDataSourceImplementer(remoteAPI)
}