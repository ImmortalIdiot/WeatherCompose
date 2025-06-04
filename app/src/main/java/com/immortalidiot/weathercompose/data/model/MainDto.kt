package com.immortalidiot.weathercompose.data.model

import com.google.gson.annotations.SerializedName

data class MainDto(
    val temp: Float,
    @SerializedName("feels_like") val feelsLike: Float,
    val humidity: Int,
    val pressure: Int
)
