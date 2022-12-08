package com.android.hindara.booking.app.ui.appmenu.mybookings

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

const val myBookingsRoute = "my_bookings_route"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.myBookingsGraph(
    navController: NavController,
    myBookingsViewModel: MyBookingsViewModel
) {
    composable(myBookingsRoute) {
        MyBookingsScreen(navController = navController, viewModel = myBookingsViewModel)
    }
}