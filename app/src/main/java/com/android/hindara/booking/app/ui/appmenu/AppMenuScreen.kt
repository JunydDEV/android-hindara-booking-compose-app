package com.android.hindara.booking.app.ui.appmenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.appmenu.mybookings.myBookingsRoute
import com.android.hindara.booking.app.ui.appmenu.bookmarks.bookmarksRoute
import com.android.hindara.booking.app.ui.appmenu.settings.settingsRoute
import com.android.hindara.booking.app.ui.authentication.authenticationRoute
import com.android.hindara.booking.app.ui.home.homeRoute
import com.android.hindara.booking.app.ui.theme.DarkTextColor
import com.android.hindara.booking.app.ui.theme.ScreenBackgroundColor
import com.android.hindara.booking.app.ui.theme.SelectedContentContentColor
import com.android.hindara.booking.app.ui.theme.WhiteColor

@Composable
fun AppMenuScreen(
    viewModel: AppMenuViewModel = hiltViewModel(),
    navController: NavController
) {
    val parentColumnModifier = Modifier
        .fillMaxSize()
        .background(ScreenBackgroundColor)
    Column(modifier = parentColumnModifier) {
        SpacerComposable()
        SpacerComposable()
        ProfileInfoComposable(viewModel)
        SpacerComposable()
        MenuItemsComposable(navController, viewModel)
    }
}

@Composable
private fun SpacerComposable() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.large_spacing))
    )
}

@Composable
fun ProfileInfoComposable(viewModel: AppMenuViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.profile_layout_height))
    ) {

        val defaultSpacing = dimensionResource(id = R.dimen.default_spacing)
        val profileInfo = viewModel.getProfileInfo()
        val (profileImage, name, country) = createRefs()

        val profilePictureModifier = Modifier
            .clip(CircleShape)
            .size(
                width = dimensionResource(id = R.dimen.profile_image_width),
                height = dimensionResource(id = R.dimen.profile_image_height)
            )
            .constrainAs(profileImage) {
                start.linkTo(parent.start, margin = defaultSpacing)
                top.linkTo(parent.top, margin = defaultSpacing)
                bottom.linkTo(parent.bottom, margin = defaultSpacing)
            }
        Image(
            modifier = profilePictureModifier,
            contentScale = ContentScale.Crop,
            painter = painterResource(id = profileInfo.picture),
            contentDescription = stringResource(R.string.image_profile)
        )

        val nameModifier = Modifier.constrainAs(name) {
            start.linkTo(profileImage.end, margin = defaultSpacing)
            bottom.linkTo(country.top)
            top.linkTo(profileImage.top)
        }
        Text(
            modifier = nameModifier,
            text = stringResource(id = profileInfo.name),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h1,
            color = DarkTextColor
        )

        val countryModifier = Modifier.constrainAs(country) {
            start.linkTo(profileImage.end, margin = defaultSpacing)
            top.linkTo(name.bottom)
            bottom.linkTo(profileImage.bottom)
        }
        Text(
            modifier = countryModifier,
            text = stringResource(id = profileInfo.country),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.body1,
            color = DarkTextColor
        )
    }
}

@Composable
fun MenuItemsComposable(navController: NavController, viewModel: AppMenuViewModel) {
    Column {
        val items = viewModel.getMenuItems()
        MenuItemRow(items[MenuList.BOOKMARKS]) {
            navController.navigate(bookmarksRoute)
        }
        MenuItemRow(items[MenuList.MY_BOOKINGS]) {
            navController.navigate(myBookingsRoute)
        }
        MenuItemRow(items[MenuList.SETTINGS]) {
            navController.navigate(settingsRoute)
        }
        SpacerComposable()
        SpacerComposable()
        MenuItemRow(items[MenuList.HELP]) {
            // TODO: needs to be implemented
        }
        MenuItemRow(items[MenuList.LOGOUT]) {
            navController.navigate(authenticationRoute) {
                popUpTo(homeRoute) { inclusive = true }
            }
        }
    }
}

@Composable
private fun MenuItemRow(item: MenuItem, onClick: ()-> Unit) {
    val itemRowModifier = Modifier
        .fillMaxWidth()
        .height(dimensionResource(id = R.dimen.menu_screen_horizontal_line_width))
        .background(WhiteColor)

    Row(
        modifier = itemRowModifier.clickable {
            onClick()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        MenuIconComposable(item)
        RowItemsDividerComposable()
        Text(
            text = stringResource(id = item.title),
            style = MaterialTheme.typography.body1
        )
    }
    HorizontalLineComposable()
}

@Composable
private fun MenuIconComposable(item: MenuItem) {
    val iconModifier = Modifier
        .padding(
            start = dimensionResource(id = R.dimen.default_spacing),
            end = dimensionResource(id = R.dimen.default_spacing)
        )
        .size(
            width = dimensionResource(id = R.dimen.menu_item_size),
            height = dimensionResource(id = R.dimen.menu_item_size)
        )

    Image(
        modifier = iconModifier,
        painter = painterResource(id = item.icon),
        contentDescription = null,
        colorFilter = ColorFilter.tint(SelectedContentContentColor)
    )
}

@Composable
private fun RowItemsDividerComposable() {
    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_spacing)))
}

@Composable
private fun HorizontalLineComposable() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.menu_screen_horizontal_line_thickness))
            .background(Color.LightGray)
    )
}

class MenuList {
    companion object {
        const val BOOKMARKS = 0
        const val MY_BOOKINGS = 1
        const val SETTINGS = 2
        const val HELP = 3
        const val LOGOUT = 4
    }
}
