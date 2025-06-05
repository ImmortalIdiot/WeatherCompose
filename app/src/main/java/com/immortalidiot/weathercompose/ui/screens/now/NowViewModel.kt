package com.immortalidiot.weathercompose.ui.screens.now

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.immortalidiot.weathercompose.domain.model.Weather
import com.immortalidiot.weathercompose.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val WEATHER_QUERY_DURATION = 60_000L

@HiltViewModel
class NowViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    private val _weather = MutableStateFlow<Weather?>(null)
    val weather: StateFlow<Weather?> = _weather.asStateFlow()

    init {
        viewModelScope.launch {
            while (isActive) {
                try {
                    Log.d("NowViewModel", "Request weather time millis: ${System.currentTimeMillis()}")
                    val weather = getWeatherUseCase()
                    _weather.value = weather
                } catch (e: Exception) {
                    Log.d("Error", e.message.toString())
                }
                delay(WEATHER_QUERY_DURATION)
            }
        }
    }
}
