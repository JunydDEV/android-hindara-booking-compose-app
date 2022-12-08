package com.android.hindara.booking.app.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.android.hindara.booking.app.utils.animatedComposable

const val homeRoute = "home_route"

fun NavGraphBuilder.homeNavGraph(homeViewModel: HomeViewModel, navHostController: NavHostController) {
    animatedComposable(homeRoute) {
        ApplicationHomeScreen(homeViewModel,navHostController)
    }
}