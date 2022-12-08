package com.android.hindara.booking.app.ui.authentication

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

const val authenticationRoute = "authentication_route"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authenticationGraph(navController: NavController) {
    composable(authenticationRoute) {
        AuthenticationScreen(navController = navController)
    }
}