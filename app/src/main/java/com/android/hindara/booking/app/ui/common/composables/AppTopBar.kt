package com.android.hindara.booking.app.ui.common.composables

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
fun AppTopBar(
    navController: NavController,
    title: String,
    menuItemToShow: AppBarMenuItem? = null,
    menuItemClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(id = R.dimen.large_spacing),
                start = dimensionResource(id = R.dimen.default_spacing),
                end = dimensionResource(id = R.dimen.default_spacing)
            )
            .height(dimensionResource(id = R.dimen.application_bar_height)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
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
                    .width(dimensionResource(id = R.dimen.default_spacing))
            )
            Text(
                modifier = Modifier.wrapContentWidth(),
                text = title,
                style = MaterialTheme.typography.h1,
                color = DarkTextColor
            )
        }

        menuItemToShow?.let {
            Image(
                modifier = Modifier.clickable {
                    menuItemClick?.invoke()
                },
                painter = painterResource(id = getMenuItemIcon(menuItemToShow)),
                contentDescription = null,
                colorFilter = ColorFilter.tint(DarkTextColor)
            )
        }
    }
}

fun getMenuItemIcon(menuItemToShow: AppBarMenuItem): Int {
    return when(menuItemToShow) {
        AppBarMenuItem.FILTER -> {
            R.drawable.ic_filter
        }
        AppBarMenuItem.NOTIFICATION -> {
            R.drawable.ic_notification
        }
        AppBarMenuItem.BOOKMARKS -> {
            R.drawable.ic_bookmark
        }
    }
}

enum class AppBarMenuItem {
    FILTER,
    NOTIFICATION,
    BOOKMARKS
}