package com.android.hindara.booking.app.ui.description

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.home.HomeViewModel
import com.android.hindara.booking.app.ui.hoteldetails.common.HotelShortHeader
import com.android.hindara.booking.app.ui.hoteldetails.common.MoreDetailsTopBar
import com.android.hindara.booking.app.ui.theme.DarkTextColor
import com.android.hindara.booking.app.ui.theme.ScreenBackgroundColor

@Composable
fun MoreDescriptionComposable(navController: NavController, homeViewModel: HomeViewModel) {
    val hotel = homeViewModel.getChosenHotel()
    Scaffold(
        backgroundColor = ScreenBackgroundColor,
        topBar = {
            MoreDetailsTopBar(navController, stringResource(id = R.string.description_title))
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(dimensionResource(id = R.dimen.defaultSpacing))
        ) {
            HotelShortHeader(hotel = hotel)
            Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.defaultSpacing)))
            Text(
                modifier = Modifier.wrapContentWidth(),
                text = hotel.description,
                style = MaterialTheme.typography.body1,
                color = DarkTextColor
            )
        }
    }
}
