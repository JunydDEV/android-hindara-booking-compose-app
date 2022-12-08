package com.android.hindara.booking.app.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.appmenu.appMenuRoute
import com.android.hindara.booking.app.ui.theme.ScreenBackgroundColor
import com.android.hindara.booking.app.utils.noRippleClickable

@Composable
fun HomeScreenTopBar(navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        SpacerComposable()

        val topBarModifier = Modifier
            .fillMaxWidth()
            .background(ScreenBackgroundColor)
            .padding(dimensionResource(id = R.dimen.default_spacing))
        Row(modifier = topBarModifier, horizontalArrangement = Arrangement.SpaceBetween) {
            Image(
                modifier = Modifier.noRippleClickable {
                    navController.navigate(appMenuRoute)
                },
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = stringResource(R.string.image_menu)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = stringResource(R.string.image_notification)
            )
        }
    }
}

@Composable
private fun SpacerComposable() {
    val topSpacerModifier = Modifier
        .fillMaxWidth()
        .height(dimensionResource(id = R.dimen.extra_large_spacing))
    Spacer(modifier = topSpacerModifier)
}