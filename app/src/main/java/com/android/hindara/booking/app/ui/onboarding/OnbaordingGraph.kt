package com.android.hindara.booking.app.ui.onboarding

import androidx.compose.animation.ExperimentalAnimationApi
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

const val onboardingRoute = "onboarding_route"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.onboardingGraph(navHostController: NavHostController) {
    composable(onboardingRoute) {
        OnboardingScreen(navHostController)
    }
}