package com.immortalidiot.weathercompose.ui.components.bars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    height: Dp = 64.dp,
    title: @Composable (() -> Unit) = {},
    navigationIcon: @Composable (() -> Unit)? = null,
) {
    val colorScheme = MaterialTheme.colorScheme
    val topPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = topPadding)
            .background(colorScheme.surface)
            .height(height)
            .then(modifier)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            title()
        }

        if (navigationIcon != null) {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterStart)
            ) {
                navigationIcon()
            }
        }
    }
}
