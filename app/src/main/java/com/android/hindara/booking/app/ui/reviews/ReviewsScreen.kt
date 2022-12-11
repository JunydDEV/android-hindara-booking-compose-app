package com.android.hindara.booking.app.ui.reviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.hoteldetails.common.HotelCardComposable
import com.android.hindara.booking.app.ui.common.composables.AppTopBar
import com.android.hindara.booking.app.ui.home.Hotel
import com.android.hindara.booking.app.ui.hoteldetails.common.ReviewItemComposable

@Composable
fun ReviewsScreen(navController: NavController, selectedHotel: Hotel) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            AppTopBar(navController, stringResource(id = R.string.label_reviews))
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(dimensionResource(id = R.dimen.default_spacing))
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_spacing))
        ) {
            HotelCardComposable(hotel = selectedHotel)
            Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.small_spacing)))
            repeat(selectedHotel.reviewsList.size) { index ->
                ReviewItemComposable(review = selectedHotel.reviewsList[index])
            }
        }
    }
}