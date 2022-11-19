package com.android.hindara.booking.app.ui.description

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.android.hindara.booking.app.ui.home.HomeViewModel

const val moreDescriptionRoute = "more_description_route"

fun NavGraphBuilder.moreDescriptionGraph(navController: NavController, homeViewModel: HomeViewModel) {
    composable(moreDescriptionRoute) {
        MoreDescriptionComposable(navController = navController, homeViewModel = homeViewModel)
    }
}