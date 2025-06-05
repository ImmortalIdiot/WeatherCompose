package com.immortalidiot.weathercompose.domain.model

data class DailyForecast(
    val date: String,
    val averageTemp: Int,
    val weatherDescription: String,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Int,
    val icon: String,
)
