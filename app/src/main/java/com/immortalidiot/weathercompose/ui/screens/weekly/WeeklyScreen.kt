package com.immortalidiot.weathercompose.ui.screens.weekly

import android.Manifest
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.immortalidiot.weathercompose.R
import com.immortalidiot.weathercompose.ui.components.ForecastCard
import com.immortalidiot.weathercompose.ui.components.PermissionHandler
import com.immortalidiot.weathercompose.ui.components.bars.ApplicationAppBar

object WeeklyScreen : Screen {
    private fun readResolve(): Any = WeeklyScreen

    @Composable
    override fun Content() {
        val viewModel: WeeklyViewModel = hiltViewModel()
        WeeklyScreenComposable(viewModel = viewModel)
    }
}

@Composable
private fun WeeklyScreenComposable(viewModel: WeeklyViewModel) {
    val forecasts = viewModel.forecasts.collectAsState().value
    val navigator = LocalNavigator.currentOrThrow

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ApplicationAppBar(
                isAction = true,
                navigator = navigator,
                textRes = R.string.forecast
            )
        }
    ) { innerPadding ->
        PermissionHandler(
            permission = Manifest.permission.ACCESS_FINE_LOCATION,
            rationaleText = stringResource(R.string.need_geo_position),
            deniedText = stringResource(R.string.geo_position_denied)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(forecasts) { forecast ->
                    ForecastCard(
                        forecast = forecast,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}
