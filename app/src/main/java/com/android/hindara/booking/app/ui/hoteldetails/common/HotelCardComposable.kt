package com.android.hindara.booking.app.ui.hoteldetails.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.HindaraCard
import com.android.hindara.booking.app.ui.home.Hotel
import com.android.hindara.booking.app.ui.theme.YellowColor

@Composable
fun HotelCardComposable(hotel: Hotel) {
    HindaraCard(
        showBorders = false,
        cornersSize = dimensionResource(id = R.dimen.cardCornersSize)
    ) {
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