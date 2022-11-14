package com.android.hindara.booking.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.android.hindara.booking.app.ui.appmenu.appMenuGraph
import com.android.hindara.booking.app.ui.authentication.authenticationGraph
import com.android.hindara.booking.app.ui.home.homeNavGraph
import com.android.hindara.booking.app.ui.home.homeRoute
import com.android.hindara.booking.app.ui.onboarding.onboardingGraph
import com.android.hindara.booking.app.ui.onboarding.onboardingRoute

@Composable
fun HindaraAppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = onboardingRoute) {
        onboardingGraph(navController)
        authenticationGraph(navController)
        homeNavGraph(navController)
        appMenuGraph(navController)
    }
}