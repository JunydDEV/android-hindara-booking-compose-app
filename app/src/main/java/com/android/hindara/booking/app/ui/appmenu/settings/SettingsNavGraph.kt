package com.android.hindara.booking.app.ui.appmenu.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val settingsRoute = "settings_route"

fun NavGraphBuilder.settingsGraph(navController: NavController) {
    composable(settingsRoute) {
        SettingsScreen(navController = navController)
    }
}