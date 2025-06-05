package com.immortalidiot.weathercompose.domain.model

data class DailyForecast(
    val date: String,
    val averageTemp: Float,
    val weatherDescription: String,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Double,
    val icon: String,
)
