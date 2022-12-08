package com.android.hindara.booking.app.ui.reviews

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.android.hindara.booking.app.ui.home.HomeViewModel

const val reviewsRoute = "reviews_route"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.reviewsGraph(
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    composable(reviewsRoute) {
        ReviewsScreen(navController = navController, homeViewModel = homeViewModel)
    }
}