package com.android.hindara.booking.app.ui.appmenu.mybookings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val myBookingsRoute = "my_bookings_route"

fun NavGraphBuilder.myBookingsGraph(navController: NavController) {
    composable(myBookingsRoute) {
        MyBookingsScreen(navController = navController)
    }
}