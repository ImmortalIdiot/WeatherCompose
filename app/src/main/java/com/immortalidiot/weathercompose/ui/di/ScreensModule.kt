package com.immortalidiot.weathercompose.ui.di

import cafe.adriel.voyager.core.registry.screenModule
import com.immortalidiot.weathercompose.ui.navigation.ApplicationNavigation
import com.immortalidiot.weathercompose.ui.screens.now.NowScreen
import com.immortalidiot.weathercompose.ui.screens.weekly.WeeklyScreen

val screensModule = screenModule {
    register<ApplicationNavigation.Now> { NowScreen }
    register<ApplicationNavigation.Weekly> { WeeklyScreen }
}
