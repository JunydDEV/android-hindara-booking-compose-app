package com.android.hindara.booking.app.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import com.core.model.hotel_details.Hotel
import com.android.hindara.booking.app.R

@Composable
fun ApplicationHomeScreen(
    homeViewModel: HomeViewModel,
    navController: NavController,
    onHotelSelect: (Hotel) -> Unit
) {

    val categories = homeViewModel.getFeaturedCategories(LocalContext.current).collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        topBar = { HomeScreenTopBar(navController) }
    ) {
        val modifier = Modifier
            .padding(it)
            .padding(bottom = dimensionResource(id = R.dimen.navigation_bar_height))
        HomeScreenContent(
            modifier = modifier,
            navController = navController,
            onHotelSelect = onHotelSelect,
            categories = categories.value
        )
    }
}


