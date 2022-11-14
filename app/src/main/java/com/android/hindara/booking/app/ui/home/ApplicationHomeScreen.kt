package com.android.hindara.booking.app.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.android.hindara.booking.app.ui.theme.ScreenBackgroundColor

@Composable
fun ApplicationHomeScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = ScreenBackgroundColor,
        topBar = { HomeScreenTopBar(navController) }
    ) {
        val modifier = Modifier.padding(it)
        HomeScreenContent(modifier = modifier, navController = navController)
    }
}


