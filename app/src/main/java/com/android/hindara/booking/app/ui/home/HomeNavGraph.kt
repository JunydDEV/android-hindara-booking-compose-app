package com.android.hindara.booking.app.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable

const val homeRoute = "home_route"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeNavGraph(homeViewModel: HomeViewModel, navHostController: NavHostController) {
    composable(homeRoute) {
        ApplicationHomeScreen(homeViewModel,navHostController)
    }
}