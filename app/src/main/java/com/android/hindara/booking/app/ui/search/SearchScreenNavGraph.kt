package com.android.hindara.booking.app.ui.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.ui.home.Hotel
import com.android.hindara.booking.app.utils.animatedComposable

const val searchRoute = "searchRoute"

fun NavGraphBuilder.searchScreenNavGraph(
    navController: NavController,
    onHotelSelect: (Hotel) -> Unit
) {
    animatedComposable(searchRoute) {
        SearchScreen(navController = navController, onHotelSelect = onHotelSelect)
    }
}