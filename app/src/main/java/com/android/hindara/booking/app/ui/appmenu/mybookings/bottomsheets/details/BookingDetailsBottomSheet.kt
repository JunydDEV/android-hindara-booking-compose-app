package com.android.hindara.booking.app.ui.appmenu.mybookings.bottomsheets.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.bottomsheets.composables.BottomSheetContentWithTitle
import com.android.hindara.booking.app.ui.common.composables.ApplicationCard
import com.android.hindara.booking.app.ui.appmenu.mybookings.MyBookingsViewModel
import com.android.hindara.booking.app.ui.common.bottomsheets.composables.CancelButtonComposable
import com.android.hindara.booking.app.ui.common.bottomsheets.alertbottomsheet.alertBottomSheetRoute
import com.android.hindara.booking.app.data.AlertType
import com.android.hindara.booking.app.ui.home.Hotel
import com.android.hindara.booking.app.ui.hoteldetails.common.BookingDatesComposable
import com.android.hindara.booking.app.ui.hoteldetails.common.HotelInfoComposable
import com.android.hindara.booking.app.ui.theme.*
import java.time.LocalDate

@Composable
fun BookingDetailsBottomSheet(
    navController: NavController,
    myBookingsViewModel: MyBookingsViewModel
) {
    BookingDetailsContent(
        navController = navController,
        hotel = myBookingsViewModel.chosenBooking.hotel,
        checkInDate = myBookingsViewModel.chosenBooking.checkInDate,
        checkOutDate = myBookingsViewModel.chosenBooking.checkOutDate
    )
}

@Composable
fun BookingDetailsContent(
    navController: NavController,
    hotel: Hotel,
    checkInDate: LocalDate,
    checkOutDate: LocalDate
) {
    BottomSheetContentWithTitle(title = stringResource(R.string.label_booking_details)) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            HotelInfoComposable(hotel)
            BookingDatesComposable(checkInDate, checkOutDate)
            PaymentStatusComposable()
            CancelButtonComposable {
                val destination = alertBottomSheetRoute.replace("{type}", AlertType.cancelBookingConfirmation)
                navController.navigate(destination){
                    popUpTo(bookingDetailsBottomSheetRoute){
                        inclusive = true
                    }
                }
            }
        }
    }
}

@Composable
private fun PaymentStatusComposable() {
    ApplicationCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.default_spacing)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.label_payment_status),
                style = MaterialTheme.typography.h2
            )
            Text(
                text = stringResource(R.string.label_payment_status_paid),
                style = MaterialTheme.typography.h2,
                color = success_color
            )
        }
    }
}
