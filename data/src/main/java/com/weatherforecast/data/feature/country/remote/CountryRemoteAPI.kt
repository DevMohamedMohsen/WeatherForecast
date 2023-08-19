package com.weatherforecast.data.feature.country.remote

import com.weatherforecast.data.feature.RemoteCountriesNowResult
import com.weatherforecast.data.feature.country.remote.entity.RemoteCountry
import retrofit2.Response
import retrofit2.http.GET

interface CountryRemoteAPI {

    @GET("v0.1/countries")
    suspend fun getCountries(): Response<RemoteCountriesNowResult<List<RemoteCountry>>>
}