package com.android.hindara.booking.app.ui.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.utils.animatedComposable
import com.core.model.hotel_details.Hotel

const val homeRoute = "home_route"

fun NavGraphBuilder.homeNavGraph(
    navController: NavController,
    homeViewModel: HomeViewModel,
    onHotelSelect: (Hotel) -> Unit
) {
    animatedComposable(homeRoute) {
        ApplicationHomeScreen(
            homeViewModel = homeViewModel,
            navController = navController,
            onHotelSelect = onHotelSelect
        )
    }
}