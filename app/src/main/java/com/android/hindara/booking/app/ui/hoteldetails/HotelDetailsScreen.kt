package com.android.hindara.booking.app.ui.hoteldetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.android.hindara.booking.app.ui.theme.BlackGradientColor
import com.android.hindara.booking.app.ui.theme.PrimaryColor
import com.android.hindara.booking.app.ui.theme.WhiteColor
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
        TransparentSpace(transparentSpacer, headerImage)
        BackButtonComposable(backButton)
        BookmarkButtonComposable(bookMarkButton)
        ContentfulSectionComposable(
            contentSection = contentSection,
            transparentSpacer = transparentSpacer
        )
        HotelBoardCard(hotelBoardCard = hotelBoardCard, transparentSpacer = transparentSpacer)
    }
}

@Composable
fun ConstraintLayoutScope.TransparentSpace(
    transparentSpacer: ConstrainedLayoutReference,
    headerImage: ConstrainedLayoutReference
) {
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(top = getHeaderImageHeightInDp() - 50.dp)
        .constrainAs(transparentSpacer) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(headerImage.bottom)
        })
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
    contentSection: ConstrainedLayoutReference,
    transparentSpacer: ConstrainedLayoutReference
) {
    val modifier = Modifier
        .clip(
            RoundedCornerShape(
                topStart = dimensionResource(id = R.dimen.fieldCornersSize),
                topEnd = dimensionResource(id = R.dimen.fieldCornersSize)
            )
        )
        .background(WhiteColor)
        .fillMaxSize()
        .constrainAs(contentSection) {
            top.linkTo(transparentSpacer.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        // Content will be shown here
    }
}

@Composable
fun ConstraintLayoutScope.HotelBoardCard(
    hotelBoardCard: ConstrainedLayoutReference,
    transparentSpacer: ConstrainedLayoutReference
) {
    val modifier = Modifier
        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.fieldCornersSize)))
        .background(PrimaryColor)
        .height(100.dp)
        .constrainAs(hotelBoardCard) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        // Content will be shown here
    }
}

