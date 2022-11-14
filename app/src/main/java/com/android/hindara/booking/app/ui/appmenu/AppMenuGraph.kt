package com.android.hindara.booking.app.ui.appmenu

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val appMenuRoute = "app_menu_route"

fun NavGraphBuilder.appMenuGraph(navController: NavController) {
    composable(appMenuRoute) {
        AppMenuScreen(navController = navController)
    }
}