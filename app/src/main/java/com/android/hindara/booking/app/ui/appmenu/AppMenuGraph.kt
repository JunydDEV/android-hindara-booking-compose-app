package com.android.hindara.booking.app.ui.appmenu

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

const val appMenuRoute = "app_menu_route"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.appMenuGraph(navController: NavController) {
    composable(appMenuRoute) {
        AppMenuScreen(navController = navController)
    }
}