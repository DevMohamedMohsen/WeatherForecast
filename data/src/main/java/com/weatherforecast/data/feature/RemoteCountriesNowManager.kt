package com.weatherforecast.data.feature

import com.google.gson.Gson
import com.weatherforecast.domain.feature.Resource
import retrofit2.Response

data class RemoteCountriesNowResult<T>(val data: T)
data class RemoteCountriesNowError(val msg: String)

suspend fun <T> callCountriesNow(call: suspend () -> Response<RemoteCountriesNowResult<T>>): Resource<T> {
    return try {
        val response = call()
        when (response.isSuccessful) {
            true -> {
                val responseBody = response.body()
                when (responseBody != null) {
                    true -> Resource.Success(responseBody.data)
                    false -> Resource.Error(RemoteException())
                }
            }

            false -> Resource.Error(RemoteException(Gson().fromJson(response.errorBody()?.string(), RemoteCountriesNowError::class.java).msg))
        }
    } catch (throwable: Throwable) {
        Resource.Error(throwable)
    }
}