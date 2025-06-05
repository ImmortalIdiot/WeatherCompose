package com.immortalidiot.weathercompose.ui.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class ApplicationNavigation : ScreenProvider {
    data object Now : ApplicationNavigation()
    data object Weekly : ApplicationNavigation()
}