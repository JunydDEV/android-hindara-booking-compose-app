package com.android.hindara.booking.app.ui.reviews

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.ui.home.Hotel
import com.android.hindara.booking.app.utils.animatedComposable

const val reviewsRoute = "reviews_route"

fun NavGraphBuilder.reviewsGraph(
    navController: NavController,
    selectedHotel: MutableState<Hotel?>
) {
    animatedComposable(reviewsRoute) {
        ReviewsScreen(
            navController = navController,
            selectedHotel = selectedHotel.value!!
        )
    }
}