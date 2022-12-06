package com.android.hindara.booking.app.ui.reviews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.home.HomeViewModel
import com.android.hindara.booking.app.ui.hoteldetails.common.HotelCardComposable
import com.android.hindara.booking.app.ui.common.composables.AppTopBar
import com.android.hindara.booking.app.ui.hoteldetails.common.ReviewItemComposable
import com.android.hindara.booking.app.ui.theme.ScreenBackgroundColor

@Composable
fun ReviewsScreen(navController: NavController, homeViewModel: HomeViewModel) {
    val hotel = homeViewModel.getSelectedHotel()
    Scaffold(
        backgroundColor = ScreenBackgroundColor,
        topBar = {
            AppTopBar(navController, stringResource(id = R.string.label_reviews))
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(dimensionResource(id = R.dimen.default_spacing))
                .verticalScroll(rememberScrollState())
        ) {
            HotelCardComposable(hotel = hotel)
            Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.large_spacing)))
            repeat(hotel.reviewsList.size) { index ->
                ReviewItemComposable(review = hotel.reviewsList[index])
            }
            Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.default_spacing)))
        }
    }
}