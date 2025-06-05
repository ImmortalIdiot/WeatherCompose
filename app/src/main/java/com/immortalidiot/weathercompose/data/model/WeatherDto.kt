package com.immortalidiot.weathercompose.data.model

import com.google.gson.annotations.SerializedName
import com.immortalidiot.weathercompose.data.utils.FormatManager
import com.immortalidiot.weathercompose.domain.model.Weather
import kotlin.math.roundToInt

data class WeatherDto(
    @SerializedName("coord") val coord: CoordinatesDto,
    @SerializedName("weather") val weather: List<WeatherDescriptionDto>,
    @SerializedName("main") val main: MainDto,
    @SerializedName("wind") val wind: WindDto,
    @SerializedName("name") val name: String
) {
    fun toModel(): Weather {
        val w = weather.firstOrNull() ?: error("Empty weather description from server")
        return Weather(
            latitude = coord.lat,
            longitude = coord.lon,
            locationName = name,
            weatherMain = FormatManager.uppercaseFirst(w.description),
            temperature = main.temp.roundToInt(),
            feelsLike = main.feelsLike.roundToInt(),
            humidity = main.humidity,
            pressure = main.pressure,
            windSpeed = wind.speed,
            icon = w.icon
        )
    }
}
