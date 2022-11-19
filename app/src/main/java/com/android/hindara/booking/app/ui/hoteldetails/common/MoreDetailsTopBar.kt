package com.android.hindara.booking.app.ui.hoteldetails.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.DarkTextColor

@Composable
fun MoreDetailsTopBar(navController: NavController, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(id = R.dimen.largeSpacing),
                start = dimensionResource(id = R.dimen.defaultSpacing),
                end = dimensionResource(id = R.dimen.defaultSpacing)
            )
            .height(dimensionResource(id = R.dimen.topBarHeight)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            modifier = Modifier.clickable {
                navController.popBackStack()
            },
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            colorFilter = ColorFilter.tint(DarkTextColor)
        )
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(dimensionResource(id = R.dimen.defaultSpacing))
        )
        Text(
            modifier = Modifier.wrapContentWidth(),
            text = title,
            style = MaterialTheme.typography.h1,
            color = DarkTextColor
        )
    }
}