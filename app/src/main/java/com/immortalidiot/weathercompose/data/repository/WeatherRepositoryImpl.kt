package com.immortalidiot.weathercompose.data.repository

import com.immortalidiot.weathercompose.BuildConfig
import com.immortalidiot.weathercompose.data.network.WeatherAPI
import com.immortalidiot.weathercompose.domain.model.DailyForecast
import com.immortalidiot.weathercompose.domain.model.Weather
import com.immortalidiot.weathercompose.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: WeatherAPI) : WeatherRepository {
    override suspend fun getWeatherByCoordinates(latitude: Double, longitude: Double): Weather {
        return api.getWeatherByCoordinates(
            latitude = latitude,
            longitude = longitude,
            apiKey = BuildConfig.API_KEY
        ).toModel()
    }

    override suspend fun getForecastByCoordinates(
        latitude: Double,
        longitude: Double
    ): List<DailyForecast> {
        return api.getForecastByCoordinates(
            latitude = latitude,
            longitude = longitude,
            apiKey = BuildConfig.API_KEY
        ).toDailyForecasts()
    }
}
