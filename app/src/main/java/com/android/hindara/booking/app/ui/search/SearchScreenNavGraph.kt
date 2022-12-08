package com.android.hindara.booking.app.ui.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.android.hindara.booking.app.ui.home.Hotel

const val searchRoute = "searchRoute"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.searchScreenNavGraph(
    navController: NavController,
    onHotelSelect: (Hotel) -> Unit
) {
    composable(searchRoute) {
        SearchScreen(navController = navController, onHotelSelect = onHotelSelect)
    }
}