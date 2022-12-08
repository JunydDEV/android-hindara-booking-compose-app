package com.android.hindara.booking.app.ui.authentication

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.utils.animatedComposable

const val authenticationRoute = "authentication_route"

fun NavGraphBuilder.authenticationGraph(navController: NavController) {
    animatedComposable(authenticationRoute) {
        AuthenticationScreen(navController = navController)
    }
}