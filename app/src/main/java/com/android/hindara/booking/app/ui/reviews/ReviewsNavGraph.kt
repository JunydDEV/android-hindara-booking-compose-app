package com.android.hindara.booking.app.ui.reviews

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.utils.animatedComposable
import com.core.model.hotel_details.Hotel

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