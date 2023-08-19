package com.weatherforecast.data.feature

import com.google.gson.Gson
import com.weatherforecast.domain.feature.Resource
import retrofit2.Response

data class RemoteOpenWeatherMapResult<T>(val list: T)
data class RemoteOpenWeatherMapError(val message: String)

suspend fun <T> callOpenWeatherMap(call: suspend () -> Response<RemoteOpenWeatherMapResult<T>>): Resource<T> {
    return try {
        val response = call()
        when (response.isSuccessful) {
            true -> {
                val responseBody = response.body()
                when (responseBody != null) {
                    true -> Resource.Success(responseBody.list)
                    false -> Resource.Error(RemoteException())
                }
            }

            false -> Resource.Error(RemoteException(Gson().fromJson(response.errorBody()?.string(), RemoteOpenWeatherMapError::class.java).message))
        }
    } catch (throwable: Throwable) {
        Resource.Error(throwable)
    }
}