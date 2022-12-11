package com.android.hindara.booking.app.ui.hoteldetails.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.android.hindara.booking.app.R
import com.core.model.hotel_details.Hotel

@Composable
fun HotelImageComposable(hotel: Hotel) {
    val size = dimensionResource(id = R.dimen.hotel_small_image_size)
    val modifier = Modifier
        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.hotel_image_corners_size)))
        .width(size)
        .height(size)
    Image(
        modifier = modifier,
        contentScale = ContentScale.Crop,
        painter = painterResource(id = hotel.image), contentDescription = null
    )
}