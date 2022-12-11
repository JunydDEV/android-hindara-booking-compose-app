package com.android.hindara.booking.app.ui.hoteldetails

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.ui.home.Hotel
import com.android.hindara.booking.app.utils.animatedComposable

const val hotelDetailsRoute = "hotel_details_route"

fun NavGraphBuilder.hotelDetailsGraph(
    navController: NavController,
    selectedHotel: MutableState<Hotel?>
) {
    animatedComposable(hotelDetailsRoute) {
        HotelDetailsScreen(selectedHotel.value!!, navController)
    }
}
