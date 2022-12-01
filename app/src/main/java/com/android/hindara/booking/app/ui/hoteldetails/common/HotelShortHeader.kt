package com.android.hindara.booking.app.ui.hoteldetails.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.HindaraCard
import com.android.hindara.booking.app.ui.home.Hotel
import com.android.hindara.booking.app.ui.theme.DarkTextColor
import com.android.hindara.booking.app.ui.theme.YellowColor

@Composable
fun HotelShortHeader(hotel: Hotel) {
    HindaraCard {
        Row(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.defaultSpacing))
        ) {
            HotelImageComposable(hotel)
            Column(
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.defaultSpacing)),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                HotelNameComposable(hotel)
                HotelAddressComposable(hotel)
                HotelRatingAndReviewComposable(hotel)
            }
        }
    }
}

@Composable
fun HotelImageComposable(hotel: Hotel) {
    val modifier = Modifier
        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.detailsSheetCornersSize)))
        .width(100.dp)
        .height(100.dp)
    Image(
        modifier = modifier,
        contentScale = ContentScale.Crop,
        painter = painterResource(id = hotel.image), contentDescription = null
    )
}

@Composable
private fun HotelNameComposable(hotel: Hotel) {
    Text(
        modifier = Modifier.wrapContentWidth(),
        text = hotel.name,
        style = MaterialTheme.typography.h1,
        color = DarkTextColor
    )
}

@Composable
fun HotelAddressComposable(hotel: Hotel) {
    Text(
        modifier = Modifier.wrapContentWidth(),
        text = hotel.address.locationTitle,
        style = MaterialTheme.typography.body1,
        color = DarkTextColor
    )
}

@Composable
fun HotelRatingAndReviewComposable(hotel: Hotel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RatingImageComposable()
        RatingTextComposable(hotel)
        ReviewsCount(hotel)
    }
}

@Composable
private fun RatingImageComposable() {
    Image(
        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.verySmallSpacing)),
        painter = painterResource(id = R.drawable.ic_star), contentDescription = null
    )
}


@Composable
fun RatingTextComposable(hotel: Hotel) {
    val modifier = Modifier
        .wrapContentWidth()
        .padding(start = dimensionResource(id = R.dimen.smallSpacing))

    Text(
        modifier = modifier,
        text = hotel.rating.toString(),
        style = MaterialTheme.typography.body1,
        color = YellowColor
    )
}

@Composable
fun ReviewsCount(hotel: Hotel) {
    val modifier = Modifier
        .wrapContentWidth()
        .padding(start = dimensionResource(id = R.dimen.smallSpacing))

    Text(
        modifier = modifier,
        text = "(${hotel.reviewsList.size} ${stringResource(id = R.string.reviews_title_text)})",
        style = MaterialTheme.typography.body1,
        color = Color.LightGray
    )
}
