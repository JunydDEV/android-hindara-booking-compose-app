package com.android.hindara.booking.app.ui.description

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.utils.animatedComposable
import com.core.model.hotel_details.Hotel

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