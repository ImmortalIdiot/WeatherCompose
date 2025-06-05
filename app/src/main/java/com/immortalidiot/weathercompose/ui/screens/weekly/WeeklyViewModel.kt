package com.immortalidiot.weathercompose.ui.screens.weekly

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.immortalidiot.weathercompose.domain.model.DailyForecast
import com.immortalidiot.weathercompose.domain.usecase.GetForecastsUseCase
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
class WeeklyViewModel @Inject constructor(
    private val getForecastsUseCase: GetForecastsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<ScreenUiState>(ScreenUiState.Init)
    val uiState: StateFlow<ScreenUiState> = _uiState.asStateFlow()

    private val _forecasts = MutableStateFlow<List<DailyForecast>>(emptyList())
    val forecasts: StateFlow<List<DailyForecast>> = _forecasts.asStateFlow()

    init {
        viewModelScope.launch {
            while (isActive) {
                try {
                    Log.d("WeeklyRequest", System.currentTimeMillis().toString())
                    _uiState.value = ScreenUiState.Loading
                    val forecasts = getForecastsUseCase()
                    _forecasts.value = forecasts
                    _uiState.value = ScreenUiState.Loaded
                } catch (e: Exception) {
                    Log.d("WeeklyError", e.message.toString())
                    _uiState.value = ScreenUiState.Error(e.message ?: "Неизвестная ошибка")
                }
                delay(Constants.WEATHER_QUERY_DURATION)
            }
        }
    }
}
