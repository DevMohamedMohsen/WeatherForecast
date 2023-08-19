package com.weatherforecast.data.di

import com.weatherforecast.data.feature.country.remote.CountryRemoteAPI
import com.weatherforecast.data.feature.forecast.remote.ForecastRemoteAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteAPIModule {

    @Singleton
    @Provides
    fun provideCountry(@Named(RemoteRetrofitModule.HILT_NAMED_COUNTRIES_NOW) retrofit: Retrofit): CountryRemoteAPI =
        retrofit.create(CountryRemoteAPI::class.java)

    @Singleton
    @Provides
    fun provideForecast(@Named(RemoteRetrofitModule.HILT_NAMED_OPEN_WEATHER_MAP) retrofit: Retrofit): ForecastRemoteAPI =
        retrofit.create(ForecastRemoteAPI::class.java)
}