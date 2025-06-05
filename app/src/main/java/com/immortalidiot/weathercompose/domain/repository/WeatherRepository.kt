package com.immortalidiot.weathercompose.domain.repository

import com.immortalidiot.weathercompose.domain.model.DailyForecast
import com.immortalidiot.weathercompose.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeatherByCoordinates(latitude: Double, longitude: Double): Weather
    suspend fun getForecastByCoordinates(latitude: Double, longitude: Double): List<DailyForecast>
}
