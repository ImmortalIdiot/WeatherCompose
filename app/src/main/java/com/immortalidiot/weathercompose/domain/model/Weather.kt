package com.immortalidiot.weathercompose.domain.model

data class Weather(
    val latitude: Double,
    val longitude: Double,
    val locationName: String,
    val weatherMain: String,
    val temperature: Int,
    val feelsLike: Int,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Double,
    val icon: String
)
