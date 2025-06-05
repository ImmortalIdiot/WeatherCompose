package com.immortalidiot.weathercompose.ui.components.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.immortalidiot.weathercompose.R
import com.immortalidiot.weathercompose.ui.theme.PrimaryButtonColor
import com.immortalidiot.weathercompose.ui.theme.WeatherComposeTheme

@Composable
fun ToWeeklyWeatherButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    height: Dp = 48.dp
) {
    val primaryButtonColor = PrimaryButtonColor
    val toWeeklyWeatherButtonColors = ButtonDefaults.buttonColors(
        containerColor = primaryButtonColor,
        disabledContainerColor = primaryButtonColor,
        contentColor = MaterialTheme.colorScheme.surface,
        disabledContentColor = MaterialTheme.colorScheme.surface
    )

    Button(
        modifier = modifier.height(height),
        onClick = onClick,
        colors = toWeeklyWeatherButtonColors
    ) {
        Text(text = stringResource(R.string.forecast))
    }
}

@Preview
@Composable
private fun ButtonPreview() {
    WeatherComposeTheme {
        Scaffold { _ ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ToWeeklyWeatherButton(onClick = {})
            }
        }
    }
}
