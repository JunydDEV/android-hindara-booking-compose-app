package com.android.hindara.booking.app.ui.description

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.ui.home.Hotel
import com.android.hindara.booking.app.utils.animatedComposable

const val moreDescriptionRoute = "more_description_route"

fun NavGraphBuilder.moreDescriptionGraph(
    navController: NavController,
    selectedHotel: MutableState<Hotel?>
) {
    animatedComposable(moreDescriptionRoute) {
        MoreDescriptionComposable(
            navController = navController,
            selectedHotel = selectedHotel.value!!
        )
    }
}