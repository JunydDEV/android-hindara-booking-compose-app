package com.android.hindara.booking.app.ui.description

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.composables.AppTopBar
import com.android.hindara.booking.app.ui.hoteldetails.common.HotelCardComposable
import com.core.model.hotel_details.Hotel

@Composable
fun MoreDescriptionComposable(
    navController: NavController,
    selectedHotel: Hotel
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            AppTopBar(navController, stringResource(id = R.string.label_hotel_description))
        }
    ) {
        val modifier = Modifier
            .padding(it)
            .padding(dimensionResource(id = R.dimen.default_spacing))
            .verticalScroll(rememberScrollState())

        Column(modifier = modifier) {
            HotelCardComposable(hotel = selectedHotel)
            Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.default_spacing)))
            Text(
                modifier = Modifier.wrapContentWidth(),
                text = selectedHotel.description,
                style = MaterialTheme.typography.body1
            )
        }
    }
}
