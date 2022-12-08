package com.android.hindara.booking.app.ui.appmenu.settings

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

const val settingsRoute = "settings_route"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.settingsGraph(navController: NavController) {
    composable(settingsRoute) {
        SettingsScreen(navController = navController)
    }
}