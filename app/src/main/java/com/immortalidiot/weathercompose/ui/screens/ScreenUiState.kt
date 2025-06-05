package com.immortalidiot.weathercompose.ui.screens

import androidx.compose.runtime.Immutable

@Immutable
sealed interface ScreenUiState {
    data object Init : ScreenUiState
    data object Loading : ScreenUiState
    data object Loaded : ScreenUiState
    data class Error(val errorMessage: String) : ScreenUiState
}
