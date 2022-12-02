package com.android.hindara.booking.app.ui.appmenu.mybookmarks

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val bookmarksRoute = "bookmarks_route"

fun NavGraphBuilder.bookmarksGraph(navController: NavController){
    composable(bookmarksRoute){
        BookmarksScreen(navController = navController)
    }
}