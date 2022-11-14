package com.android.hindara.booking.app.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.appmenu.appMenuRoute
import com.android.hindara.booking.app.ui.theme.ScreenBackgroundColor

@Composable
fun HomeScreenTopBar(navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        SpacerComposable()

        val topBarModifier = Modifier
            .fillMaxWidth()
            .background(ScreenBackgroundColor)
            .padding(dimensionResource(id = R.dimen.defaultSpacing))
        Row(modifier = topBarModifier, horizontalArrangement = Arrangement.SpaceBetween) {
            Image(
                modifier = Modifier.clickable {
                    navController.navigate(appMenuRoute)
                },
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = stringResource(R.string.menu_icon_description)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = stringResource(R.string.notification_icon_description)
            )
        }
    }
}

@Composable
private fun SpacerComposable() {
    val topSpacerModifier = Modifier
        .fillMaxWidth()
        .height(dimensionResource(id = R.dimen.extraLargeSpacing))
    Spacer(modifier = topSpacerModifier)
}