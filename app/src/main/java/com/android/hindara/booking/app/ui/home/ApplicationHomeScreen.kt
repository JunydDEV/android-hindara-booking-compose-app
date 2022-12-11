package com.android.hindara.booking.app.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.core.model.hotel_details.Hotel

@Composable
fun ApplicationHomeScreen(
    homeViewModel: HomeViewModel,
    navController: NavController,
    onHotelSelect: (Hotel) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        topBar = { HomeScreenTopBar(navController) }
    ) {
        val modifier = Modifier.padding(it)
        HomeScreenContent(
            viewModel = homeViewModel,
            modifier = modifier,
            navController = navController,
            onHotelSelect = onHotelSelect
        )
    }
}


