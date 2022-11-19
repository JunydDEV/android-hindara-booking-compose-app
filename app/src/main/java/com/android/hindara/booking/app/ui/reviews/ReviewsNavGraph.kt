package com.android.hindara.booking.app.ui.reviews

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.android.hindara.booking.app.ui.home.HomeViewModel

const val reviewsRoute = "reviews_route"

fun NavGraphBuilder.reviewsGraph(navController: NavController, homeViewModel: HomeViewModel) {
    composable(reviewsRoute) {
        ReviewsScreen(navController = navController, homeViewModel = homeViewModel)
    }
}