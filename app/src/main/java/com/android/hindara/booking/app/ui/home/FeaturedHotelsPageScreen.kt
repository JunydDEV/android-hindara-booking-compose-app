package com.android.hindara.booking.app.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.BlackGradientColor
import com.android.hindara.booking.app.ui.theme.WhiteColor

@Composable
fun FeaturedHotelsPageScreen(category: FeaturedCategory) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyRow {
            items(category.hotelsList.size) {
                HotelItemComposable(category.hotelsList[it])
            }
        }
    }
}

@Composable
fun HotelItemComposable(hotel: Hotel) {
    val defaultSpacing = dimensionResource(id = R.dimen.defaultSpacing)
    val largeSpacing = dimensionResource(id = R.dimen.largeSpacing)

    val constrainLayoutModifier = Modifier
        .wrapContentSize()
        .padding(top = defaultSpacing, bottom = defaultSpacing)

    ConstraintLayout(modifier = constrainLayoutModifier) {

        val (image, gradientLayer, title, description, rating) = createRefs()

        val featuredImageModifier = Modifier
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.featuredImageCornerSize)))
            .width(dimensionResource(id = R.dimen.featuredHotelImageWith))
            .height(dimensionResource(id = R.dimen.featuredHotelImageHeight))
            .constrainAs(image) {
                top.linkTo(parent.top, margin = defaultSpacing)
                start.linkTo(parent.start, margin = defaultSpacing)
                end.linkTo(parent.end, margin = defaultSpacing)
                bottom.linkTo(parent.bottom, margin = defaultSpacing)
            }
        Image(
            modifier = featuredImageModifier,
            contentScale = ContentScale.Crop,
            painter = painterResource(id = hotel.image),
            contentDescription = stringResource(R.string.hotel_image_discription)
        )

        Spacer(
            modifier = Modifier
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.featuredImageCornerSize)))
                .width(dimensionResource(id = R.dimen.featuredHotelImageWith))
                .height(dimensionResource(id = R.dimen.featuredHotelImageHeight))
                .background(BlackGradientColor)
                .constrainAs(gradientLayer) {
                    top.linkTo(parent.top, margin = defaultSpacing)
                    start.linkTo(parent.start, margin = defaultSpacing)
                    end.linkTo(parent.end, margin = defaultSpacing)
                    bottom.linkTo(parent.bottom, margin = defaultSpacing)
                }
        )

        val hotelNameTextModifier = Modifier
            .fillMaxWidth()
            .constrainAs(title) {
                start.linkTo(image.start, margin = defaultSpacing)
                bottom.linkTo(description.top)
            }
        Text(
            modifier = hotelNameTextModifier,
            text = hotel.name,
            style = MaterialTheme.typography.h2,
            color = WhiteColor
        )

        val hotelAddressTextModifier = Modifier
            .fillMaxWidth()
            .constrainAs(description) {
                start.linkTo(image.start, margin = defaultSpacing)
                bottom.linkTo(parent.bottom, margin = largeSpacing)
            }
        Text(
            modifier = hotelAddressTextModifier,
            text = hotel.address,
            style = MaterialTheme.typography.body2,
            color = WhiteColor
        )

        val ratingViewModifier = Modifier
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.ratingViewCornerSize)))
            .background(BlackGradientColor)
            .padding(
                start = dimensionResource(id = R.dimen.smallSpacing),
                end = dimensionResource(id = R.dimen.smallSpacing),
                top = dimensionResource(id = R.dimen.verySmallSpacing),
                bottom = dimensionResource(id = R.dimen.verySmallSpacing)
            )
            .constrainAs(rating) {
                end.linkTo(image.end, margin = defaultSpacing)
                top.linkTo(image.top, margin = defaultSpacing)
            }
        Row(
            modifier = ratingViewModifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingContentComposable(hotel)
        }
    }
}

@Composable
private fun RatingContentComposable(hotel: Hotel) {
    Image(
        painter = painterResource(id = R.drawable.ic_star),
        contentDescription = stringResource(R.string.start_icon_image_description)
    )
    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.smallSpacing)))
    Text(
        modifier = Modifier.wrapContentSize(),
        text = hotel.rating.toString(),
        style = MaterialTheme.typography.body2,
        color = WhiteColor
    )
}