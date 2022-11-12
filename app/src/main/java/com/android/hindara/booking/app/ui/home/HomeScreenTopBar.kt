package com.android.hindara.booking.app.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.ScreenBackgroundColor

@Composable
fun HomeScreenTopBar() {
    val topBarModifier = Modifier
        .fillMaxWidth()
        .background(ScreenBackgroundColor)
        .padding(dimensionResource(id = R.dimen.defaultSpacing))
    Row(modifier = topBarModifier, horizontalArrangement = Arrangement.SpaceBetween) {
        Image(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = stringResource(R.string.menu_icon_description)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = stringResource(R.string.notification_icon_description)
        )
    }
}