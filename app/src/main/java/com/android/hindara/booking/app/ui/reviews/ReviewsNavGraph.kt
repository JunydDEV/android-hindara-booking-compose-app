package com.android.hindara.booking.app.ui.reviews

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.ui.home.HomeViewModel
import com.android.hindara.booking.app.utils.animatedComposable

const val reviewsRoute = "reviews_route"

fun NavGraphBuilder.reviewsGraph(
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    animatedComposable(reviewsRoute) {
        ReviewsScreen(navController = navController, homeViewModel = homeViewModel)
    }
}