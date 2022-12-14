package com.android.hindara.booking.app.ui.appmenu.bookmarks

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.composables.AppTopBar
import com.android.hindara.booking.app.ui.hoteldetails.common.HotelCardComposable
import com.core.model.hotel_details.Hotel

@Composable
fun BookmarksScreen(
    viewModel: BookmarksViewModel = hiltViewModel(),
    navController: NavController,
    onHotelSelect: (Hotel) -> Unit
) {
    val hotelsState = viewModel.getBookmarkedHotelsList(LocalContext.current).collectAsState()

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            AppTopBar(navController, stringResource(id = R.string.label_bookmarks))
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(dimensionResource(id = R.dimen.default_spacing))
        ){
            val hotelsList = hotelsState.value
            items(hotelsList.size) { index ->
                HotelCardComposable(hotel = hotelsList[index], onHotelSelect = onHotelSelect)
            }
        }
    }
}