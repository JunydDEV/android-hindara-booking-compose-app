package com.android.hindara.booking.app.ui.description

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.android.hindara.booking.app.ui.home.HomeViewModel

const val moreDescriptionRoute = "more_description_route"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.moreDescriptionGraph(
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    composable(moreDescriptionRoute) {
        MoreDescriptionComposable(navController = navController, homeViewModel = homeViewModel)
    }
}