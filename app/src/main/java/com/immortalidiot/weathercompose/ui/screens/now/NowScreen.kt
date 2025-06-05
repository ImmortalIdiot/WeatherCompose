package com.immortalidiot.weathercompose.ui.screens.now

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.immortalidiot.weathercompose.R
import com.immortalidiot.weathercompose.ui.components.PermissionHandler

object NowScreen : Screen {
    private fun readResolve(): Any = NowScreen

    @Composable
    override fun Content() {
        val viewModel: NowViewModel = hiltViewModel()
        NowWeatherScreenComposable(viewModel = viewModel)
    }
}

@Composable
fun NowWeatherScreenComposable(viewModel: NowViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        PermissionHandler(
            permission = Manifest.permission.ACCESS_FINE_LOCATION,
            rationaleText = stringResource(R.string.need_geo_position),
            deniedText = stringResource(R.string.geo_position_denied)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val weather = viewModel.weather.collectAsState().value

                weather?.let {
                    Text(text = "Широта: " + weather.latitude.toString())
                    Text(text = "Долгота: " + weather.longitude.toString())
                    Text(text = "Местоположение: " + weather.locationName)
                    Text(text = "Погода: " + weather.weatherMain)
                    Text(text = "Температура: " + weather.temperature)
                    Text(text = "Ощущается как: " + weather.feelsLike)
                    Text(text = "Влажность: " + weather.humidity)
                    Text(text = "Давление: " + weather.pressure)
                    Text(text = "Скорость ветра: " + weather.windSpeed.toString())
                }
            }
        }
    }
}
