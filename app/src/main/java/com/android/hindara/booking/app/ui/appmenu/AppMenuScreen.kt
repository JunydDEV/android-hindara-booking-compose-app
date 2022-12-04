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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.appmenu.mybookings.myBookingsRoute
import com.android.hindara.booking.app.ui.appmenu.mybookmarks.bookmarksRoute
import com.android.hindara.booking.app.ui.appmenu.settings.settingsRoute
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
            .height(dimensionResource(id = R.dimen.largeSpacing))
    )
}

@Composable
fun ProfileInfoComposable(viewModel: AppMenuViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.profileContainerHeight))
    ) {

        val defaultSpacing = dimensionResource(id = R.dimen.defaultSpacing)
        val profileInfo = viewModel.getProfileInfo()
        val (profileImage, name, country) = createRefs()

        val profilePictureModifier = Modifier
            .clip(CircleShape)
            .size(
                width = dimensionResource(id = R.dimen.userImageWidth),
                height = dimensionResource(id = R.dimen.userImageHeight)
            )
            .constrainAs(profileImage) {
                start.linkTo(parent.start, margin = defaultSpacing)
                top.linkTo(parent.top, margin = defaultSpacing)
                bottom.linkTo(parent.bottom, margin = defaultSpacing)
            }
        Image(
            modifier = profilePictureModifier,
            painter = painterResource(id = profileInfo.picture),
            contentDescription = stringResource(R.string.profile_picture_description)
        )

        val nameModifier = Modifier.constrainAs(name) {
            start.linkTo(profileImage.end, margin = defaultSpacing)
            bottom.linkTo(country.top)
            top.linkTo(profileImage.top)
        }
        Text(
            modifier = nameModifier,
            text = profileInfo.name,
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
            text = profileInfo.country,
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
        MenuItemRow(navController, items[MenuList.BOOKMARKS], bookmarksRoute)
        MenuItemRow(navController, items[MenuList.MY_BOOKINGS], myBookingsRoute)
        MenuItemRow(navController, items[MenuList.SETTINGS], settingsRoute)
        SpacerComposable()
        SpacerComposable()
        MenuItemRow(navController, items[MenuList.HELP])
        MenuItemRow(navController, items[MenuList.LOGOUT])
    }
}

@Composable
private fun MenuItemRow(navController: NavController, item: MenuItem, route: String? = null) {
    val itemRowModifier = Modifier
        .fillMaxWidth()
        .height(dimensionResource(id = R.dimen.sectionSeparatorSize))
        .background(WhiteColor)

    Row(
        modifier = itemRowModifier.clickable {
            if (route != null) {
                navController.navigate(route)
            }
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
            start = dimensionResource(id = R.dimen.defaultSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing)
        )
        .size(
            width = dimensionResource(id = R.dimen.menuItemIconWidth),
            height = dimensionResource(id = R.dimen.menuItemIconHeight)
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
    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.smallSpacing)))
}

@Composable
private fun HorizontalLineComposable() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.lineThickness))
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
