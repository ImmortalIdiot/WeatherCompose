package com.immortalidiot.weathercompose

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.immortalidiot.weathercompose.ui.di.nowScreenModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        ScreenRegistry {
            nowScreenModule()
        }
    }
}
