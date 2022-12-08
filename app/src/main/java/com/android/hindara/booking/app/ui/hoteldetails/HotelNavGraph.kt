package com.android.hindara.booking.app.ui.hoteldetails

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.android.hindara.booking.app.ui.home.HomeViewModel

const val hotelDetailsRoute = "hotel_details_route"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.hotelDetailsGraph(
    homeViewModel: HomeViewModel,
    navController: NavController
) {
    composable(hotelDetailsRoute) {
        HotelDetailsScreen(homeViewModel, navController)
    }
}
