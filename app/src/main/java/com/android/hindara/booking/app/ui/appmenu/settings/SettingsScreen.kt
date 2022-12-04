package com.android.hindara.booking.app.ui.appmenu.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.HindaraCard
import com.android.hindara.booking.app.ui.common.composables.AppTopBar
import com.android.hindara.booking.app.ui.theme.ScreenBackgroundColor

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    navController: NavController,
) {
    Scaffold(
        backgroundColor = ScreenBackgroundColor,
        topBar = {
            AppTopBar(navController, stringResource(id = R.string.menu_item_settings))
        }
    ) {
        val modifier = Modifier
            .padding(it)
            .padding(dimensionResource(id = R.dimen.defaultSpacing))
            .fillMaxSize()

        Column(modifier) {
            SettingsItemComposable(
                icon = R.drawable.ic_user,
                label = R.string.user_settings_option,
                onClick = {}
            )
            DefaultSpacer()
            SettingsItemComposable(
                icon = R.drawable.ic_notification,
                label = R.string.notification_menu,
                onClick = {}
            )
            DefaultSpacer()
            SettingsItemComposable(
                icon = R.drawable.ic_phone,
                label = R.string.contact_us_option,
                onClick = {}
            )
            DefaultSpacer()
            SettingsItemComposable(
                icon = R.drawable.ic_credit_card,
                label = R.string.payment_settings_option,
                onClick = {}
            )
        }
    }
}

@Composable
private fun DefaultSpacer() {
    Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.defaultSpacing)))
}

@Composable
private fun SettingsItemComposable(
    icon: Int,
    label: Int,
    onClick: () -> Unit
) {
    val modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onClick()
        }
        .padding(dimensionResource(id = R.dimen.defaultSpacing))

    HindaraCard {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = stringResource(R.string.settings_icon_description)
                )
                Text(
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.defaultSpacing)
                    ),
                    text = stringResource(label),
                    style = MaterialTheme.typography.body1
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = stringResource(R.string.right_arrow_icon_description)
            )
        }
    }
}