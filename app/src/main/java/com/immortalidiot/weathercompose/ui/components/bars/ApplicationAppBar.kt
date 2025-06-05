package com.immortalidiot.weathercompose.ui.components.bars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun ApplicationAppBar(
    isAction: Boolean,
    navigator: Navigator,
    textRes: Int,
) {
    CustomTopAppBar(
        title = {
            Text(
                text = stringResource(textRes),
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            if (isAction) {
                IconButton (onClick = { navigator.pop() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
        }
    )
}
