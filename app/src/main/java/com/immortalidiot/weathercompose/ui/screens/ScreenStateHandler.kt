package com.immortalidiot.weathercompose.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.immortalidiot.weathercompose.R
import com.immortalidiot.weathercompose.ui.components.CircularIndicator

@Composable
fun ScreenStateHandler(
    state: ScreenUiState,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            is ScreenUiState.Init -> { /* do nothing */ }
            is ScreenUiState.Loading -> { CircularIndicator() }
            is ScreenUiState.Loaded -> { content() }
            is ScreenUiState.Error -> {
                Text(text = stringResource(R.string.error) + " " + state.errorMessage)
            }
        }
    }
}
