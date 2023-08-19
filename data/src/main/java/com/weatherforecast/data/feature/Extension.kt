package com.weatherforecast.data.feature

fun Int?.getOrDefault(): Int = this ?: 0
fun Long?.getOrDefault(): Long = this ?: 0
fun String?.getOrDefault(): String = this ?: ""