package com.android.hindara.booking.app.ui.common.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.android.hindara.booking.app.R

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
                start = dimensionResource(id = R.dimen.tiny_spacing),
                end = dimensionResource(id = R.dimen.tiny_spacing)
            )
            .height(dimensionResource(id = R.dimen.application_bar_height)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = stringResource(R.string.image_back_button),
                    tint = MaterialTheme.colors.onSurface
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(dimensionResource(id = R.dimen.default_spacing))
            )
            Text(
                modifier = Modifier.wrapContentWidth(),
                text = title,
                style = MaterialTheme.typography.h1
            )
        }


        menuItemToShow?.let {
            IconButton(onClick = { menuItemClick?.invoke() }) {
                Icon(
                    painter = painterResource(id = getMenuItemIcon(menuItemToShow)),
                    contentDescription = stringResource(id = R.string.image_filter_button),
                    tint = MaterialTheme.colors.onSurface
                )
            }
        } ?: Spacer(modifier = Modifier.wrapContentSize())
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