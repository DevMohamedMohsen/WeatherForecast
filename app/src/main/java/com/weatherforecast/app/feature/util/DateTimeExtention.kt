package com.weatherforecast.app.feature.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Long.formatTimeStampToDateTime(): String {
    val date = SimpleDateFormat("EEE, h:mm a", Locale.getDefault())
    date.timeZone = TimeZone.getTimeZone("GMT")
    return date.format(Date(this * 1000L))
}