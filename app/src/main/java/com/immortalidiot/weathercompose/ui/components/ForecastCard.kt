package com.immortalidiot.weathercompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.immortalidiot.weathercompose.R
import com.immortalidiot.weathercompose.domain.model.DailyForecast

@Composable
fun ForecastCard(
    forecast: DailyForecast,
    modifier: Modifier = Modifier
) {
    val headerTextStyle = MaterialTheme.typography.titleMedium
    val contentTextStyle = MaterialTheme.typography.bodyMedium

    Column(
        modifier = modifier
            .background(
                color = CardDefaults.cardColors().containerColor,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = forecast.date,
            style = headerTextStyle
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = stringResource(R.string.avg_temp) +
                            " ${forecast.averageTemp}" +
                            stringResource(R.string.celsius_temp_scale),
                    style = contentTextStyle
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.weather) + " ${forecast.weatherDescription}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    AsyncImage(
                        model = "https://openweathermap.org/img/wn/${forecast.icon}@2x.png",
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )
                }
                Text(
                    text = stringResource(R.string.humidity) + " ${forecast.humidity}%",
                    style = contentTextStyle
                )
                Text(
                    text = stringResource(R.string.pressure) +
                            " ${forecast.pressure} " +
                            stringResource(R.string.pressure_scale),
                    style = contentTextStyle
                )
                Text(
                    text = stringResource(R.string.wind_speed) +
                            " ${forecast.windSpeed} " +
                            stringResource(R.string.speed_scale),
                    style = contentTextStyle
                )
            }
        }
    }
}
