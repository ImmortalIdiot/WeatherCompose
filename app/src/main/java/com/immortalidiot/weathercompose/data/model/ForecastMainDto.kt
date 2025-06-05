package com.immortalidiot.weathercompose.data.model

import com.google.gson.annotations.SerializedName

data class ForecastMainDto(
    @SerializedName("temp_min") val minTemp: Float,
    @SerializedName("temp_max") val maxTemp: Float,
    val humidity: Int,
    val pressure: Int
)
