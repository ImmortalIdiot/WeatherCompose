package com.immortalidiot.weathercompose.ui.screens.now

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.immortalidiot.weathercompose.domain.model.Weather
import com.immortalidiot.weathercompose.domain.usecase.GetWeatherUseCase
import com.immortalidiot.weathercompose.ui.screens.ScreenUiState
import com.immortalidiot.weathercompose.ui.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<ScreenUiState>(ScreenUiState.Init)
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _weather = MutableStateFlow<Weather?>(null)
    val weather: StateFlow<Weather?> = _weather.asStateFlow()

    init {
        viewModelScope.launch {
            while (isActive) {
                try {
                    _uiState.value = ScreenUiState.Loading
                    Log.d("NowViewModel", "Request weather time millis: ${System.currentTimeMillis()}")
                    val weather = getWeatherUseCase()
                    _weather.value = weather
                    _uiState.value = ScreenUiState.Loaded
                    delay(Constants.WEATHER_QUERY_DURATION)
                } catch (e: Exception) {
                    _uiState.value = ScreenUiState.Error(e.message ?: "Неизвестная ошибка")
                    delay(Constants.UI_STATE_DELAY)
                }
            }
        }
    }
}
