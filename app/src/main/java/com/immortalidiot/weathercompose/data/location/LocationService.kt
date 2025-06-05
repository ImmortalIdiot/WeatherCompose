package com.immortalidiot.weathercompose.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.immortalidiot.weathercompose.domain.model.UserLocation
import com.immortalidiot.weathercompose.domain.repository.LocationProvider
import kotlinx.coroutines.tasks.await

class LocationService(context: Context) : LocationProvider {
    private val client: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): UserLocation? {
        val location: Location? = client.lastLocation.await()
        return location?.let { UserLocation(latitude = it.latitude, longitude = it.longitude) }
    }
}
