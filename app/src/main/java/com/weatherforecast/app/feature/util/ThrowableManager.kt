package com.weatherforecast.app.feature.util

import android.content.Context
import com.weatherforecast.app.R
import com.weatherforecast.data.feature.RemoteException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ThrowableManager {

    fun getErrorMessage(context: Context, throwable: Throwable): String {
        return when (throwable) {
            is ConnectException, is UnknownHostException, is SocketTimeoutException -> context.getString(R.string.error_connection)
            is RemoteException -> throwable.message ?: ""
            else -> context.getString(R.string.error_exception)
        }
    }
}