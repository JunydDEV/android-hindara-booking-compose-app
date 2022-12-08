package com.android.hindara.booking.app.ui.onboarding

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.android.hindara.booking.app.utils.animatedComposable

const val onboardingRoute = "onboarding_route"

fun NavGraphBuilder.onboardingGraph(navHostController: NavHostController) {
    animatedComposable(route = onboardingRoute) {
        OnboardingScreen(navHostController)
    }
}