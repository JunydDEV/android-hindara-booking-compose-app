package com.android.hindara.booking.app

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.android.hindara.booking.app.ui.appmenu.appMenuGraph
import com.android.hindara.booking.app.ui.authentication.authenticationGraph
import com.android.hindara.booking.app.ui.home.HomeViewModel
import com.android.hindara.booking.app.ui.home.homeNavGraph
import com.android.hindara.booking.app.ui.home.homeRoute
import com.android.hindara.booking.app.ui.hoteldetails.hotelDetailsGraph
import com.android.hindara.booking.app.ui.onboarding.onboardingGraph
import com.android.hindara.booking.app.ui.onboarding.onboardingRoute

@Composable
fun HindaraAppNavHost(navController: NavHostController) {
    val homeViewModel: HomeViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = homeRoute) {
        onboardingGraph(navController)
        authenticationGraph(navController)
        homeNavGraph(homeViewModel,navController)
        appMenuGraph(navController)
        hotelDetailsGraph(homeViewModel,navController)
    }
}