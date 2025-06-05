package com.immortalidiot.weathercompose.data.model

import com.google.gson.annotations.SerializedName

data class ForecastItemDto(
    @SerializedName("dt") val timestamp: Long,
    @SerializedName("main") val forecastMain: ForecastMainDto,
    @SerializedName("weather") val weather: List<WeatherDescriptionDto>,
    @SerializedName("wind") val wind: WindDto,
    @SerializedName("dt_txt") val textDateTime: String
)
