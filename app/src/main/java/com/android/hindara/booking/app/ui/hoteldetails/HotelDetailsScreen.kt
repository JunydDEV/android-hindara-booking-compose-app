package com.android.hindara.booking.app.ui.hoteldetails

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.booking.dateselection.calendarBottomSheetRoute
import com.android.hindara.booking.app.ui.description.moreDescriptionRoute
import com.android.hindara.booking.app.ui.home.HomeViewModel
import com.android.hindara.booking.app.ui.home.Hotel
import com.android.hindara.booking.app.ui.hoteldetails.common.ReviewItemComposable
import com.android.hindara.booking.app.ui.reviews.reviewsRoute
import com.android.hindara.booking.app.ui.theme.*
import com.android.hindara.booking.app.utils.getHeaderImageHeightInDp
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*

@Composable
fun HotelDetailsScreen(
    homeViewModel: HomeViewModel,
    navController: NavController,
) {
    val hotel = homeViewModel.getSelectedHotel()
    HotelDetailsContent(navController, hotel)
}

@Composable
private fun HotelDetailsContent(
    navController: NavController,
    hotel: Hotel
) {
    Scaffold(
        bottomBar = {
            BookingBottomBar(navController, hotel)
        }
    ) {
        val mainModifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(it)

        ConstraintLayout(modifier = mainModifier) {
            val (headerImage, backButton, bookMarkButton, hotelBoardCard, contentSection) = createRefs()
            HeaderImageComposable(headerImage, hotel)
            BackButtonComposable(navController, backButton)
            BookmarkButtonComposable(bookMarkButton)
            ContentfulSectionComposable(navController, contentSection, hotel)
            HotelBannerComposable(hotelBoardCard, hotel)
        }
    }
}

@Composable
fun ConstraintLayoutScope.HeaderImageComposable(
    headerImage: ConstrainedLayoutReference,
    hotel: Hotel
) {
    val headerImageModifier = Modifier
        .constrainAs(headerImage) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        .height(getHeaderImageHeightInDp())
    Image(
        modifier = headerImageModifier,
        painter = painterResource(id = hotel.image),
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(R.string.image_hotel_header)
    )
}

@Composable
fun ConstraintLayoutScope.BackButtonComposable(
    navController: NavController,
    backButton: ConstrainedLayoutReference
) {
    val extraLargeSpacing = dimensionResource(id = R.dimen.extra_large_spacing)
    val defaultSpacing = dimensionResource(id = R.dimen.default_spacing)
    val backButtonBoxModifier = Modifier
        .size(50.dp, 50.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(BlackGradientColor)
        .padding(dimensionResource(id = R.dimen.tiny_spacing))
        .constrainAs(backButton) {
            top.linkTo(parent.top, margin = extraLargeSpacing)
            start.linkTo(parent.start, margin = defaultSpacing)
        }
    Box(
        modifier = backButtonBoxModifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.clickable {
                navController.popBackStack()
            },
            painter = painterResource(id = R.drawable.ic_back),
            colorFilter = tint(WhiteColor),
            contentDescription = stringResource(R.string.image_back)
        )
    }
}

@Composable
fun ConstraintLayoutScope.BookmarkButtonComposable(bookmarkButton: ConstrainedLayoutReference) {
    val extraLargeSpacing = dimensionResource(id = R.dimen.extra_large_spacing)
    val defaultSpacing = dimensionResource(id = R.dimen.default_spacing)
    val bookMarkButtonBoxModifier = Modifier
        .size(50.dp, 50.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(BlackGradientColor)
        .padding(dimensionResource(id = R.dimen.tiny_spacing))
        .constrainAs(bookmarkButton) {
            top.linkTo(parent.top, margin = extraLargeSpacing)
            end.linkTo(parent.end, margin = defaultSpacing)
        }
    Box(
        modifier = bookMarkButtonBoxModifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_bookmark),
            colorFilter = tint(WhiteColor),
            contentDescription = stringResource(R.string.image_back)
        )
    }
}


@Composable
fun ConstraintLayoutScope.ContentfulSectionComposable(
    navController: NavController,
    contentSection: ConstrainedLayoutReference,
    hotel: Hotel
) {

    val detailsSheetCornersSize = dimensionResource(id = R.dimen.hotel_content_corners_size)
    val offset = getHeaderImageHeightInDp() - detailsSheetCornersSize
    val topGuidelineForContentView = createGuidelineFromTop(offset)

    val modifier = Modifier
        .clip(
            RoundedCornerShape(
                topStart = dimensionResource(id = R.dimen.hotel_content_corners_size),
                topEnd = dimensionResource(id = R.dimen.hotel_content_corners_size)
            )
        )
        .background(ScreenBackgroundColor)
        .fillMaxSize()
        .padding(dimensionResource(id = R.dimen.default_spacing))
        .constrainAs(contentSection) {
            top.linkTo(topGuidelineForContentView)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_spacing))
    ) {
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = dimensionResource(id = R.dimen.half_height_of_banner_layout)),
            text = stringResource(R.string.label_hotel_description),
            style = MaterialTheme.typography.h1,
            color = DarkTextColor
        )
        Text(
            modifier = Modifier.wrapContentWidth(),
            text = hotel.description,
            style = MaterialTheme.typography.body1,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            color = DarkTextColor
        )
        if (hotel.description.length > 150) {
            Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable {
                        navController.navigate(moreDescriptionRoute)
                    },
                text = stringResource(R.string.label_read_more),
                style = MaterialTheme.typography.body1,
                color = SuccessColor
            )
        }
        GoogleMapsComposable(hotel)
        Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.default_spacing)))
        Text(
            modifier = Modifier.wrapContentWidth(),
            text = stringResource(R.string.label_reviews),
            style = MaterialTheme.typography.h1,
            color = DarkTextColor
        )
        repeat(2) {
            ReviewItemComposable(review = hotel.reviewsList[it])
        }

        if (hotel.reviewsList.size > 2) {
            Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable {
                        navController.navigate(reviewsRoute)
                    },
                text = stringResource(R.string.label_see_more_reviews),
                style = MaterialTheme.typography.body1,
                color = SuccessColor
            )
        }
        Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.large_spacing)))
    }
}

@Composable
private fun SmallSpacer() {
    Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.small_spacing)))
}

@Composable
fun GoogleMapsComposable(hotel: Hotel) {
    val address = hotel.address
    val singapore = LatLng(address.latitude, address.longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    val googleMapModifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.hotel_content_corners_size)))
        .height(dimensionResource(id = R.dimen.google_maps_height))

    GoogleMap(
        modifier = googleMapModifier,
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
            zoomGesturesEnabled = true
        ),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = singapore),
            title = address.locationTitle,
            icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)
        )
    }
}


@Composable
fun ConstraintLayoutScope.HotelBannerComposable(
    hotelBoardCard: ConstrainedLayoutReference,
    hotel: Hotel
) {
    val eightyPercentWidthOfScreen = 0.8f
    val bannerHeight = dimensionResource(id = R.dimen.hotel_banner_layout_height)
    val halfOfBannerHeight = dimensionResource(id = R.dimen.half_height_of_banner_layout)
    val detailsSheetCornersSize = dimensionResource(id = R.dimen.hotel_content_corners_size)

    val spaceFromBottomOfHeaderImage = halfOfBannerHeight + detailsSheetCornersSize
    val offset = getHeaderImageHeightInDp() - spaceFromBottomOfHeaderImage
    val topGuidelineForHotelBanner = createGuidelineFromTop(offset)

    val modifier = Modifier
        .fillMaxWidth(eightyPercentWidthOfScreen)
        .height(bannerHeight)
        .constrainAs(hotelBoardCard) {
            top.linkTo(topGuidelineForHotelBanner)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    Card(
        modifier = modifier,
        backgroundColor = WhiteColor,
        elevation = dimensionResource(id = R.dimen.card_default_elevation),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.hotel_content_corners_size))
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.default_spacing)),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            HotelNameComposable(hotel)
            HotelAddressComposable(hotel)
            HotelRatingAndReviewComposable(hotel)
        }
    }
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
    val modifier = Modifier
        .wrapContentWidth()

    Text(
        modifier = modifier,
        text = hotel.address.locationTitle,
        style = MaterialTheme.typography.body1,
        color = LightTextColor
    )
}

@Composable
fun HotelRatingAndReviewComposable(hotel: Hotel) {
    val modifier = Modifier
        .fillMaxWidth()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RatingBarComposable()
        RatingTextComposable(hotel)
        ReviewsCount(hotel)
    }
}

@Composable
fun RatingBarComposable() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5) {
            Image(
                modifier = Modifier.padding(end = dimensionResource(id = R.dimen.tiny_spacing)),
                painter = painterResource(id = R.drawable.ic_star), contentDescription = null
            )
        }
    }
}

@Composable
fun RatingTextComposable(hotel: Hotel) {
    val modifier = Modifier
        .wrapContentWidth()
        .padding(start = dimensionResource(id = R.dimen.small_spacing))

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
        .padding(start = dimensionResource(id = R.dimen.small_spacing))

    Text(
        modifier = modifier,
        text = "(${hotel.reviewsList.size} ${stringResource(id = R.string.label_reviews)})",
        style = MaterialTheme.typography.body1,
        color = Color.LightGray
    )
}


@Composable
fun BookingBottomBar(
    navController: NavController,
    hotel: Hotel
) {
    val modifier = Modifier
        .wrapContentHeight()
        .clip(
            RoundedCornerShape(
                topStart = dimensionResource(id = R.dimen.home_screen_hotel_image_corners_size),
                topEnd = dimensionResource(id = R.dimen.home_screen_hotel_image_corners_size)
            )
        )
        .fillMaxWidth()
        .background(WhiteColor)
        .padding(dimensionResource(id = R.dimen.default_spacing))

    ConstraintLayout(
        modifier = modifier
    ) {
        val (priceLabel, price, bookButton) = createRefs()
        val priceLabelModifier = Modifier
            .wrapContentWidth()
            .constrainAs(priceLabel) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
        Text(
            modifier = priceLabelModifier,
            text = stringResource(R.string.label_per_night_price),
            style = MaterialTheme.typography.body1,
            color = LightTextColor
        )

        val priceModifier = Modifier
            .wrapContentWidth()
            .constrainAs(price) {
                start.linkTo(parent.start)
                top.linkTo(priceLabel.bottom)
            }
        Text(
            modifier = priceModifier,
            text = "$ ${hotel.pricePerNight}",
            style = MaterialTheme.typography.h1,
            maxLines = 1,
            color = DarkTextColor
        )

        val bookNowButton = Modifier
            .wrapContentWidth()
            .constrainAs(bookButton) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
        Button(
            modifier = bookNowButton,
            shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.primary_button_corners_size))),
            onClick = {
                navController.navigate(calendarBottomSheetRoute)
            },
        ) {
            Text(stringResource(R.string.button_book_now_label))
        }
    }
}


