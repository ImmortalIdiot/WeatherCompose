package com.immortalidiot.weathercompose.domain.usecase

import com.immortalidiot.weathercompose.domain.model.DailyForecast
import com.immortalidiot.weathercompose.domain.repository.LocationProvider
import com.immortalidiot.weathercompose.domain.repository.WeatherRepository
import javax.inject.Inject

class   GetForecastsUseCase @Inject constructor(
    private val repository: WeatherRepository,
    private val locationProvider: LocationProvider
) {
    suspend operator fun invoke(): List<DailyForecast> {
        val location = locationProvider.getCurrentLocation()
            ?: throw IllegalStateException("Location unavailable")

        return repository.getForecastByCoordinates(
            latitude = location.latitude,
            longitude = location.longitude
        )
    }
}
