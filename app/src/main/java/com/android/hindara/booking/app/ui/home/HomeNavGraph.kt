package com.android.hindara.booking.app.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val homeRoute = "home_route"

fun NavGraphBuilder.homeNavGraph(navHostController: NavHostController) {
    composable(homeRoute) {
        ApplicationHomeScreen(navHostController)
    }
}