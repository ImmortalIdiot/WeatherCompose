package com.immortalidiot.weathercompose.ui.screens.now

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import com.immortalidiot.weathercompose.R
import com.immortalidiot.weathercompose.ui.components.PermissionHandler
import com.immortalidiot.weathercompose.ui.components.bars.ApplicationAppBar
import com.immortalidiot.weathercompose.ui.components.buttons.ToWeeklyWeatherButton
import com.immortalidiot.weathercompose.ui.screens.weekly.WeeklyScreen

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
    val navigator = LocalNavigator.currentOrThrow

    val mainContentStyle = MaterialTheme.typography.titleLarge.copy(fontSize = 30.sp)
    val contentStyle = MaterialTheme.typography.titleLarge.copy(fontSize = 16.sp)
    val temperatureStyle = MaterialTheme.typography.titleLarge.copy(fontSize = 60.sp)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ApplicationAppBar(
                isAction = false,
                navigator = navigator,
                textRes = R.string.weather_screen
            )
        }
    ) { innerPadding ->
        PermissionHandler(
            permission = Manifest.permission.ACCESS_FINE_LOCATION,
            rationaleText = stringResource(R.string.need_geo_position),
            deniedText = stringResource(R.string.geo_position_denied)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                val weather = viewModel.weather.collectAsState().value

                weather?.let {
                    Column(
                        modifier = Modifier.align(Alignment.TopCenter),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = weather.locationName,
                            style = mainContentStyle
                        )

                        VerticalScreenSpacer(height = 8.dp)

                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            Text(
                                text = stringResource(R.string.latitude) + " " +
                                        weather.latitude.toString(),
                                style = contentStyle
                            )
                            Text(
                                text = stringResource(R.string.longitude) + " " +
                                        weather.longitude.toString(),
                                style = contentStyle
                            )
                        }
                    }

                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = weather.temperature.toString() +
                                    stringResource(R.string.celsius_temp_scale),
                            style = temperatureStyle
                        )
                        Text(
                            text = stringResource(R.string.feels_like) + " " + weather.feelsLike,
                            style = contentStyle
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = weather.weatherMain,
                                style = contentStyle
                            )
                            AsyncImage(
                                model = "https://openweathermap.org/img/wn/${weather.icon}@2x.png",
                                contentDescription = "",
                                modifier = Modifier.size(32.dp)
                            )
                        }

                        VerticalScreenSpacer(height = 48.dp)

                        Column {
                            Text(text = stringResource(R.string.humidity) + " " + weather.humidity)
                            Text(text = stringResource(R.string.pressure) + " " + weather.pressure)
                            Text(text = stringResource(R.string.wind_speed) + " " + weather.windSpeed.toString())
                        }
                    }

                }

                ToWeeklyWeatherButton(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    onClick = { navigator push WeeklyScreen })
            }
        }
    }
}

@Composable
private fun VerticalScreenSpacer(
    height: Dp = 16.dp
) {
    Spacer(modifier = Modifier.height(height))
}
