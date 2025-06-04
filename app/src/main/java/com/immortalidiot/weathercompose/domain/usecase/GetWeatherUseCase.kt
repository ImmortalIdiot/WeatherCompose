package com.immortalidiot.weathercompose.domain.usecase

import com.immortalidiot.weathercompose.domain.model.Weather
import com.immortalidiot.weathercompose.domain.repository.WeatherRepository

class GetWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(latitude: Double?, longitude: Double?): Weather {
        return if (latitude != null && longitude != null) {
            repository.getWeatherByCoordinates(latitude, longitude)
        } else {
            throw IllegalArgumentException("Coordinates or city must be provided")
        }
    }
}
