package com.immortalidiot.weathercompose.domain.model

data class Weather(
    val latitude: Double,
    val longitude: Double,
    val locationName: String,
    val weatherMain: String,
    val temperature: Float,
    val feelsLike: Float,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Double,
    val icon: String
)
