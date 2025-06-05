package com.immortalidiot.weathercompose.data.model

import com.google.gson.annotations.SerializedName
import com.immortalidiot.weathercompose.data.utils.DateManager
import com.immortalidiot.weathercompose.domain.model.DailyForecast
import kotlin.math.roundToInt

data class ForecastDto(@SerializedName("list") val forecasts: List<ForecastItemDto>) {
    fun toDailyForecasts(): List<DailyForecast> {
        return forecasts
            .groupBy { forecast ->
                DateManager.toDateString(forecast.timestamp)
            }
            .map { (date, items) ->
                val minTemp = items.minOf { it.forecastMain.minTemp }
                val maxTemp = items.maxOf { it.forecastMain.maxTemp }
                val avgTemp = (maxTemp + minTemp) / 2
                val mostFrequencyWeather = items
                    .flatMap { it.weather }
                    .groupingBy { it.main }
                    .eachCount()
                    .maxByOrNull { it.value }?.key ?: "Unknown"

                val mostFrequencyIcon = items
                    .flatMap { it.weather }
                    .groupingBy { it.icon }
                    .eachCount()
                    .maxByOrNull { it.value }?.key ?: ""

                DailyForecast(
                    date = date,
                    averageTemp = avgTemp.roundToInt(),
                    weatherDescription = mostFrequencyWeather,
                    humidity = items.map { it.forecastMain.humidity }.average().roundToInt(),
                    pressure = items.map { it.forecastMain.pressure }.average().roundToInt(),
                    windSpeed = (items.map { it.wind.speed }.average() * 3600 / 1000).roundToInt(),
                    icon = mostFrequencyIcon
                )
            }
            .take(5)
    }
}
