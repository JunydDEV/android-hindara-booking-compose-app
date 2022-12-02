package com.android.hindara.booking.app.ui.appmenu.mybookmarks

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.composables.AppTopBar
import com.android.hindara.booking.app.ui.hoteldetails.common.HotelShortHeader
import com.android.hindara.booking.app.ui.theme.ScreenBackgroundColor

@Composable
fun BookmarksScreen(
    viewModel: BookmarksViewModel = hiltViewModel(),
    navController: NavController
) {
    Scaffold(
        backgroundColor = ScreenBackgroundColor,
        topBar = {
            AppTopBar(navController, stringResource(id = R.string.menu_item_bookmarks))
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(dimensionResource(id = R.dimen.defaultSpacing))
        ){
            val bookmarkList = viewModel.getBookmarkedHotelsList()
            items(bookmarkList.size) { index ->
                HotelShortHeader(hotel = bookmarkList[index])
            }
        }
    }
}