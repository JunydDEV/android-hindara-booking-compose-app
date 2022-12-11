package com.android.hindara.booking.app.ui.hoteldetails.common

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.core.model.hotel_details.Hotel

@Composable
fun HotelAddressComposable(hotel: Hotel) {
    Text(
        modifier = Modifier.wrapContentWidth(),
        text = hotel.address.locationTitle,
        style = MaterialTheme.typography.body1
    )
}