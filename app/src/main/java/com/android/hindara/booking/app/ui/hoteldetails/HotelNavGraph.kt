package com.android.hindara.booking.app.ui.hoteldetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.android.hindara.booking.app.ui.home.HomeViewModel

const val hotelDetailsRoute = "hotel_details_route"

fun NavGraphBuilder.hotelDetailsGraph(homeViewModel: HomeViewModel, navController: NavController) {
    composable(hotelDetailsRoute) {
        HotelDetailsScreen(homeViewModel, navController)
    }
}
