package com.android.hindara.booking.app.ui.hoteldetails.common

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.composables.ApplicationCard
import com.android.hindara.booking.app.ui.home.Hotel

@Composable
fun HotelInfoComposable(hotel: Hotel) {
    ApplicationCard {
        Row(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.default_spacing))
        ) {
            HotelImageComposable(hotel)
            Column(
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.default_spacing))
                    .height(80.dp), verticalArrangement = Arrangement.SpaceBetween
            ) {
                HotelNameComposable(hotel)
                HotelAddressComposable(hotel)
            }
        }
    }
}
