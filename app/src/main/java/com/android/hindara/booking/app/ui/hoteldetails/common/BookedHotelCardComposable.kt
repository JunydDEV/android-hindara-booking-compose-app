package com.android.hindara.booking.app.ui.hoteldetails.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.composables.ApplicationCard
import com.android.hindara.booking.app.ui.theme.primary_color
import com.android.hindara.booking.app.utils.getFormattedDate
import com.core.model.hotel_details.Hotel
import java.time.LocalDate

@Composable
fun BookedHotelCardComposable(
    checkInDate: LocalDate,
    hotel: Hotel,
    onClick: () -> Unit
) {
    ApplicationCard(
        showBorders = false,
        cornersSize = dimensionResource(id = R.dimen.app_card_corners_size),
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .padding(dimensionResource(id = R.dimen.default_spacing))
        ) {
            HotelImageComposable(hotel)
            Column(
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.default_spacing)),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                HotelNameComposable(hotel)
                HotelAddressComposable(hotel)
                BookingDatesComposable(checkInDate)
            }
        }
    }
}

@Composable
fun BookingDatesComposable(checkInDate: LocalDate) {
    Text(
        modifier = Modifier.wrapContentWidth(),
        text = checkInDate.getFormattedDate(),
        style = MaterialTheme.typography.h2,
        color = primary_color
    )
}
