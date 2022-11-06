package com.android.hindara.booking.app.ui.authentication

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val authenticationRoute = "authentication_route"

fun NavGraphBuilder.authenticationGraph(navController: NavController) {
    composable(authenticationRoute) {
        AuthenticationScreen(navController = navController)
    }
}