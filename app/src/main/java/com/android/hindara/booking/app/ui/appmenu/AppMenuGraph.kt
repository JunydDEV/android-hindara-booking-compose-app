package com.android.hindara.booking.app.ui.appmenu

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.utils.animatedComposable

const val appMenuRoute = "app_menu_route"

fun NavGraphBuilder.appMenuGraph(navController: NavController) {
    animatedComposable(appMenuRoute) {
        AppMenuScreen(navController = navController)
    }
}