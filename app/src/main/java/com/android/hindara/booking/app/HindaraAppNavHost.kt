package com.android.hindara.booking.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.android.hindara.booking.app.ui.onboarding.onboardingGraph
import com.android.hindara.booking.app.ui.onboarding.onboardingRoute

@Composable
fun HindaraAppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = onboardingRoute) {
        this.onboardingGraph(navController)
    }
}