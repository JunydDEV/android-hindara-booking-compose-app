package com.android.hindara.booking.app.ui.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val searchRoute = "searchRoute"

fun NavGraphBuilder.searchScreenNavGraph(navController: NavController) {
    composable(searchRoute) {
        SearchScreen(navController = navController)
    }
}