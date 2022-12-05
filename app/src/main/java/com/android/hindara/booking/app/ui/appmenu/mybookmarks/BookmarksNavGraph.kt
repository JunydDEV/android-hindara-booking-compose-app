package com.android.hindara.booking.app.ui.appmenu.mybookmarks

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.android.hindara.booking.app.ui.home.Hotel

const val bookmarksRoute = "bookmarks_route"

fun NavGraphBuilder.bookmarksGraph(navController: NavController, onHotelSelect: (Hotel) -> Unit
){
    composable(bookmarksRoute){
        BookmarksScreen(navController = navController, onHotelSelect = onHotelSelect)
    }
}