package com.immortalidiot.weathercompose.ui.screens.weekly

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.immortalidiot.weathercompose.ui.components.ForecastCard

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

    val context = LocalContext.current
    val requestPermission = Manifest.permission.ACCESS_FINE_LOCATION

    var permissionGranted by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                requestPermission
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    var shouldShowRationale by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        permissionGranted = granted

        shouldShowRationale = !granted && ActivityCompat.shouldShowRequestPermissionRationale(
            context as Activity,
            requestPermission
        )
    }

    LaunchedEffect(Unit) {
        if (!permissionGranted) {
            launcher.launch(requestPermission)
        }
    }

    val forecasts = viewModel.forecasts.collectAsState().value

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        if (permissionGranted) {
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
        } else {
            if (shouldShowRationale) {
                Text("Для отображения погоды нужно разрешение на местоположение.")
            } else {
                Text("Разрешение не предоставлено.")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { launcher.launch(requestPermission) }) {
                Text("Запросить разрешение")
            }
        }
    }
}
