package com.immortalidiot.weathercompose.ui.di

import cafe.adriel.voyager.core.registry.screenModule
import com.immortalidiot.weathercompose.ui.navigation.ApplicationNavigation
import com.immortalidiot.weathercompose.ui.screens.now.NowScreen

val nowScreenModule = screenModule {
    register<ApplicationNavigation.Now> { NowScreen }
}
