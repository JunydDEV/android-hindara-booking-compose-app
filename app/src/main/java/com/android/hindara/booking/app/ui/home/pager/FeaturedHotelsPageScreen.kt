package com.android.hindara.booking.app.ui.home.pager

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.hoteldetails.hotelDetailsRoute
import com.android.hindara.booking.app.ui.theme.black_transparent_color
import com.android.hindara.booking.app.ui.theme.primary_color
import com.android.hindara.booking.app.ui.theme.white_color
import com.android.hindara.booking.app.utils.mapImageToDrawable
import com.core.model.hotel_details.FeaturedCategory
import com.core.model.hotel_details.Hotel

@Composable
fun FeaturedHotelsPageScreen(
    navController: NavController,
    category: FeaturedCategory,
    onHotelSelect: (Hotel) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyRow {
            items(category.hotelsList.size) {
                HotelItemComposable(
                    navController = navController,
                    hotel = category.hotelsList[it],
                    onHotelSelect = onHotelSelect
                )
            }
        }
    }
}

@Composable
fun HotelItemComposable(
    navController: NavController,
    hotel: Hotel,
    onHotelSelect: (Hotel) -> Unit,
) {
    val defaultSpacing = dimensionResource(id = R.dimen.default_spacing)
    val largeSpacing = dimensionResource(id = R.dimen.large_spacing)

    val constrainLayoutModifier = Modifier
        .wrapContentSize()
        .padding(top = defaultSpacing, bottom = defaultSpacing)

    ConstraintLayout(modifier = constrainLayoutModifier) {

        val (image, gradientLayer, title, description, rating) = createRefs()

        val featuredImageModifier = Modifier
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.home_screen_hotel_image_corners_size)))
            .clickable {
                onHotelSelect(hotel)
            }
            .width(dimensionResource(id = R.dimen.home_screen_hotel_image_width))
            .height(dimensionResource(id = R.dimen.home_screen_hotel_image_height))
            .constrainAs(image) {
                top.linkTo(parent.top, margin = defaultSpacing)
                start.linkTo(parent.start, margin = defaultSpacing)
                end.linkTo(parent.end, margin = defaultSpacing)
                bottom.linkTo(parent.bottom, margin = defaultSpacing)
            }
        Image(
            modifier = featuredImageModifier,
            contentScale = ContentScale.Crop,
            painter = painterResource(
                id = mapImageToDrawable(hotel.image)
            ),
            contentDescription = stringResource(R.string.image_hotel)
        )

        Spacer(
            modifier = Modifier
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.home_screen_hotel_image_corners_size)))
                .width(dimensionResource(id = R.dimen.home_screen_hotel_image_width))
                .height(dimensionResource(id = R.dimen.home_screen_hotel_image_height))
                .background(black_transparent_color)
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
            color = white_color
        )

        val hotelAddressTextModifier = Modifier
            .fillMaxWidth()
            .constrainAs(description) {
                start.linkTo(image.start, margin = defaultSpacing)
                bottom.linkTo(parent.bottom, margin = largeSpacing)
            }
        Text(
            modifier = hotelAddressTextModifier,
            text = hotel.address.locationTitle,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = white_color
        )

        val ratingViewModifier = Modifier
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.rating_bar_corners_size)))
            .background(black_transparent_color)
            .padding(
                start = dimensionResource(id = R.dimen.small_spacing),
                end = dimensionResource(id = R.dimen.small_spacing),
                top = dimensionResource(id = R.dimen.tiny_spacing),
                bottom = dimensionResource(id = R.dimen.tiny_spacing)
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
        contentDescription = stringResource(R.string.image_star)
    )
    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_spacing)))
    Text(
        modifier = Modifier.wrapContentSize(),
        text = hotel.rating.toString(),
        style = MaterialTheme.typography.body2,
        color = white_color
    )
}