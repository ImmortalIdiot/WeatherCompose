package com.immortalidiot.weathercompose.domain.repository

import com.immortalidiot.weathercompose.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeatherByCoordinates(latitude: Double, longitude: Double): Weather
}
