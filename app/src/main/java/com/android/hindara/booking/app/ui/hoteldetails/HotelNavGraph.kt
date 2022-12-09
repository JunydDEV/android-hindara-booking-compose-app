package com.android.hindara.booking.app.ui.hoteldetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.ui.home.HomeViewModel
import com.android.hindara.booking.app.utils.animatedComposable

const val hotelDetailsRoute = "hotel_details_route"

fun NavGraphBuilder.hotelDetailsGraph(
    homeViewModel: HomeViewModel,
    navController: NavController
) {
    animatedComposable(hotelDetailsRoute) {
        HotelDetailsScreen(homeViewModel, navController)
    }
}
