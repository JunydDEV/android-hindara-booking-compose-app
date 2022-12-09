package com.android.hindara.booking.app.ui.appmenu.mybookings

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.appmenu.mybookings.bottomsheets.details.bookingDetailsBottomSheetRoute
import com.android.hindara.booking.app.ui.common.composables.AppTopBar
import com.android.hindara.booking.app.ui.hoteldetails.common.BookedHotelCardComposable
import com.android.hindara.booking.app.ui.theme.ScreenBackgroundColorLight

@Composable
fun MyBookingsScreen(
    viewModel: MyBookingsViewModel,
    navController: NavController) {
    Scaffold(
        backgroundColor = ScreenBackgroundColorLight,
        topBar = {
            AppTopBar(navController, stringResource(id = R.string.label_my_bookings))
        }
    ) {
        MyBookingsListComposable(
            navController = navController,
            paddingValues =  it,
            viewModel = viewModel
        )
    }
}

@Composable
private fun MyBookingsListComposable(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: MyBookingsViewModel
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.default_spacing))
    ) {
        val myBookingsList = viewModel.getMyBookings()
        items(myBookingsList.size) { index ->
            val hotel = myBookingsList[index].hotel
            BookedHotelCardComposable(
                checkInDate = viewModel.getCheckInDate(),
                hotel = hotel,
                onClick = {
                    viewModel.chosenBooking = myBookingsList[index]
                    navController.navigate(bookingDetailsBottomSheetRoute)
                }
            )
        }
    }
}