package com.android.hindara.booking.app.ui.onboarding

import androidx.navigation.compose.composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

const val onboardingRoute = "onboarding_route"

fun NavGraphBuilder.onboardingGraph(navHostController: NavHostController) {
    composable(onboardingRoute) {
        OnboardingScreen(navHostController)
    }
}