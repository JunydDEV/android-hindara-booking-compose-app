package com.android.hindara.booking.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.appmenu.appMenuRoute

@Composable
fun HomeScreenTopBar(navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        SpacerComposable()
        val topBarModifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(
                top = dimensionResource(id = R.dimen.default_spacing),
                start = dimensionResource(id = R.dimen.tiny_spacing),
                end = dimensionResource(id = R.dimen.tiny_spacing)
            )
        Row(
            modifier = topBarModifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { navController.navigate(appMenuRoute) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    tint = MaterialTheme.colors.onSurface,
                    contentDescription = stringResource(R.string.image_menu),
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notification),
                    tint = MaterialTheme.colors.onSurface,
                    contentDescription = stringResource(R.string.image_notification)
                )
            }
        }
    }
}

@Composable
private fun SpacerComposable() {
    val topSpacerModifier = Modifier
        .fillMaxWidth()
        .height(dimensionResource(id = R.dimen.large_spacing))
    Spacer(modifier = topSpacerModifier)
}