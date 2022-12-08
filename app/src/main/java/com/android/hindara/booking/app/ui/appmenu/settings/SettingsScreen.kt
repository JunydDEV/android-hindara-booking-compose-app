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
import com.android.hindara.booking.app.ui.common.composables.ApplicationCard
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
            AppTopBar(navController, stringResource(id = R.string.label_settings))
        }
    ) {
        val modifier = Modifier
            .padding(it)
            .padding(dimensionResource(id = R.dimen.default_spacing))
            .fillMaxSize()

        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.default_spacing))
        ) {
            SettingsItemComposable(
                icon = R.drawable.ic_user,
                label = R.string.label_user_settings,
                onClick = {}
            )
            SettingsItemComposable(
                icon = R.drawable.ic_notification,
                label = R.string.label_notification,
                onClick = {}
            )
            SettingsItemComposable(
                icon = R.drawable.ic_phone,
                label = R.string.label_contact_us,
                onClick = {}
            )
            SettingsItemComposable(
                icon = R.drawable.ic_credit_card,
                label = R.string.label_payment_settings,
                onClick = {}
            )
        }
    }
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
        .padding(dimensionResource(id = R.dimen.default_spacing))

    ApplicationCard {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = stringResource(R.string.image_settings)
                )
                Text(
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.default_spacing)
                    ),
                    text = stringResource(label),
                    style = MaterialTheme.typography.body1
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = stringResource(R.string.image_right_arrow)
            )
        }
    }
}