package com.immortalidiot.weathercompose.domain.usecase

import com.immortalidiot.weathercompose.domain.model.Weather
import com.immortalidiot.weathercompose.domain.repository.LocationProvider
import com.immortalidiot.weathercompose.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository,
    private val locationProvider: LocationProvider
) {
    suspend operator fun invoke(): Weather {
        val location = locationProvider.getCurrentLocation()
            ?: throw IllegalStateException("Location unavailable")

        return repository.getWeatherByCoordinates(
            latitude = location.latitude,
            longitude = location.longitude
        )
    }
}
