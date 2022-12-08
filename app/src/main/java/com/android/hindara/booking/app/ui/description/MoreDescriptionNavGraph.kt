package com.android.hindara.booking.app.ui.description

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.ui.home.HomeViewModel
import com.android.hindara.booking.app.utils.animatedComposable

const val moreDescriptionRoute = "more_description_route"

fun NavGraphBuilder.moreDescriptionGraph(
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    animatedComposable(moreDescriptionRoute) {
        MoreDescriptionComposable(navController = navController, homeViewModel = homeViewModel)
    }
}