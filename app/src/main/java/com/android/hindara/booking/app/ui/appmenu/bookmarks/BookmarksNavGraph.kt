package com.android.hindara.booking.app.ui.appmenu.bookmarks

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.android.hindara.booking.app.ui.home.Hotel

const val bookmarksRoute = "bookmarks_route"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.bookmarksGraph(
    navController: NavController,
    onHotelSelect: (Hotel) -> Unit
){
    composable(bookmarksRoute){
        BookmarksScreen(navController = navController, onHotelSelect = onHotelSelect)
    }
}