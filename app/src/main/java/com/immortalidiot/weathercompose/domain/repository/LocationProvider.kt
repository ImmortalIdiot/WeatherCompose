package com.immortalidiot.weathercompose.domain.repository

import com.immortalidiot.weathercompose.domain.model.UserLocation

interface LocationProvider {
    suspend fun getCurrentLocation(): UserLocation?
}
