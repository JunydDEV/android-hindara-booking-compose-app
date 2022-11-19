package com.android.hindara.booking.app.ui.hoteldetails

import android.widget.RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.home.HomeViewModel
import com.android.hindara.booking.app.ui.home.Hotel
import com.android.hindara.booking.app.ui.theme.*
import com.android.hindara.booking.app.utils.getHeaderImageHeightInDp

@Composable
fun HotelDetailsScreen(
    homeViewModel: HomeViewModel,
    navController: NavController,
) {
    val hotel = homeViewModel.getChosenHotel()

    ConstraintLayout {
        val (headerImage, backButton, bookMarkButton, transparentSpacer, hotelBoardCard, contentSection) = createRefs()

        HeaderImageComposable(headerImage, hotel)
        BackButtonComposable(backButton)
        BookmarkButtonComposable(bookMarkButton)
        ContentfulSectionComposable(contentSection)
        HotelBannerComposable(hotelBoardCard, hotel)
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
        contentDescription = stringResource(R.string.hotel_header_image_description)
    )
}

@Composable
fun ConstraintLayoutScope.BackButtonComposable(backButton: ConstrainedLayoutReference) {
    val extraLargeSpacing = dimensionResource(id = R.dimen.extraLargeSpacing)
    val defaultSpacing = dimensionResource(id = R.dimen.defaultSpacing)
    val backButtonBoxModifier = Modifier
        .size(50.dp, 50.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(BlackGradientColor)
        .padding(dimensionResource(id = R.dimen.verySmallSpacing))
        .constrainAs(backButton) {
            top.linkTo(parent.top, margin = extraLargeSpacing)
            start.linkTo(parent.start, margin = defaultSpacing)
        }
    Box(
        modifier = backButtonBoxModifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            colorFilter = tint(WhiteColor),
            contentDescription = stringResource(R.string.back_button_description)
        )
    }
}

@Composable
fun ConstraintLayoutScope.BookmarkButtonComposable(bookmarkButton: ConstrainedLayoutReference) {
    val extraLargeSpacing = dimensionResource(id = R.dimen.extraLargeSpacing)
    val defaultSpacing = dimensionResource(id = R.dimen.defaultSpacing)
    val bookMarkButtonBoxModifier = Modifier
        .size(50.dp, 50.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(BlackGradientColor)
        .padding(dimensionResource(id = R.dimen.verySmallSpacing))
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
            contentDescription = stringResource(R.string.back_button_description)
        )
    }
}


@Composable
fun ConstraintLayoutScope.ContentfulSectionComposable(
    contentSection: ConstrainedLayoutReference
) {
    val topGuidelineForContentView = createGuidelineFromTop(0.4f)

    val modifier = Modifier
        .clip(
            RoundedCornerShape(
                topStart = dimensionResource(id = R.dimen.detailsSheetCornersSize),
                topEnd = dimensionResource(id = R.dimen.detailsSheetCornersSize)
            )
        )
        .background(WhiteColor)
        .fillMaxSize()
        .constrainAs(contentSection) {
            top.linkTo(topGuidelineForContentView)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        // Content will be shown here
    }
}

@Composable
fun ConstraintLayoutScope.HotelBannerComposable(
    hotelBoardCard: ConstrainedLayoutReference,
    hotel: Hotel
) {
    val eightyPercentWidth = 0.8f
    val thirtyFivePercent = 0.33F
    val topGuidelineForHotelBanner = createGuidelineFromTop(thirtyFivePercent)

    val modifier = Modifier
        .fillMaxWidth(eightyPercentWidth)
        .constrainAs(hotelBoardCard) {
            top.linkTo(topGuidelineForHotelBanner)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    Card(
        modifier = modifier,
        backgroundColor = WhiteColor,
        elevation = dimensionResource(id = R.dimen.cardElevation),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.detailsSheetCornersSize))
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.defaultSpacing)),
            verticalArrangement = Arrangement.Center
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
    Text(
        modifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.smallSpacing))
            .wrapContentWidth(),
        text = hotel.address,
        style = MaterialTheme.typography.body1,
        color = DarkTextColor
    )
}

@Composable
fun HotelRatingAndReviewComposable(hotel: Hotel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.smallSpacing)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RatingBarComposable()
        RatingTextComposable(hotel)
        ReviewsCount(hotel)
    }
}

@Composable
fun RatingBarComposable() {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(5) {
            Image(
                modifier = Modifier.padding(end = dimensionResource(id = R.dimen.verySmallSpacing)),
                painter = painterResource(id = R.drawable.ic_star), contentDescription = null
            )
        }
    }
}

@Composable
fun RatingTextComposable(hotel: Hotel) {
    Text(
        modifier = Modifier
            .wrapContentWidth()
            .padding(start = dimensionResource(id = R.dimen.smallSpacing)),
        text = hotel.rating.toString(),
        style = MaterialTheme.typography.body1,
        color = YellowColor
    )
}

@Composable
fun ReviewsCount(hotel: Hotel) {
    Text(
        modifier = Modifier
            .wrapContentWidth()
            .padding(start = dimensionResource(id = R.dimen.smallSpacing)),
        text = "(${hotel.reviewsList?.size.toString()} Reviews)",
        style = MaterialTheme.typography.body1,
        color = Color.LightGray
    )
}


