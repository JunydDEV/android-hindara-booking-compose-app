package com.android.hindara.booking.app.ui.appmenu.mybookings

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.appmenu.mybookings.bottomsheets.details.bookingDetailsBottomSheetRoute
import com.android.hindara.booking.app.ui.common.composables.AppTopBar
import com.android.hindara.booking.app.ui.hoteldetails.common.BookedHotelCardComposable
import com.core.model.booking.MyBooking

@Composable
fun MyBookingsScreen(
    viewModel: MyBookingsViewModel,
    navController: NavController) {
    val bookingHotelsState = viewModel.getMyBookings(LocalContext.current).collectAsState()

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            AppTopBar(navController, stringResource(id = R.string.label_my_bookings))
        }
    ) {
        MyBookingsListComposable(
            navController = navController,
            paddingValues =  it,
            viewModel = viewModel,
            bookingHotelsState = bookingHotelsState
        )
    }
}

@Composable
private fun MyBookingsListComposable(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: MyBookingsViewModel,
    bookingHotelsState: State<List<MyBooking>>
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.default_spacing))
    ) {
        val hotelsList = bookingHotelsState.value
        items(hotelsList.size) { index ->
            val myBooking = hotelsList[index]
            BookedHotelCardComposable(
                checkInDate = viewModel.getCheckInDate(),
                hotel = myBooking.hotel,
                onClick = {
                    viewModel.chosenBooking = hotelsList[index]
                    navController.navigate(bookingDetailsBottomSheetRoute)
                }
            )
        }
    }
}