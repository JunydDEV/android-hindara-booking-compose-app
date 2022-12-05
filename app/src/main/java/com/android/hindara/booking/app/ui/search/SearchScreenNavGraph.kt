package com.android.hindara.booking.app.ui.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.android.hindara.booking.app.ui.home.Hotel

const val searchRoute = "searchRoute"

fun NavGraphBuilder.searchScreenNavGraph(navController: NavController, onHotelSelect: (Hotel) -> Unit
) {
    composable(searchRoute) {
        SearchScreen(navController = navController, onHotelSelect = onHotelSelect)
    }
}