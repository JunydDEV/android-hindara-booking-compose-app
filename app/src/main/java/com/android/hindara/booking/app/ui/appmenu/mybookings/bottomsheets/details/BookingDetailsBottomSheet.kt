package com.android.hindara.booking.app.ui.appmenu.mybookings.bottomsheets.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.BottomSheetContentWithTitle
import com.android.hindara.booking.app.ui.HindaraCard
import com.android.hindara.booking.app.ui.appmenu.mybookings.MyBookingsViewModel
import com.android.hindara.booking.app.ui.common.bottomsheets.states.AuthenticationBottomSheetState
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
    BottomSheetContentWithTitle(title = stringResource(R.string.booking_details)) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            HotelInfoComposable(hotel)
            BookingDatesComposable(checkInDate, checkOutDate)
            PaymentStatusComposable()
            CancelButtonComposable(navController)
        }
    }
}

@Composable
private fun PaymentStatusComposable() {
    HindaraCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.defaultSpacing)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.payment_status),
                style = MaterialTheme.typography.h2,
                color = DarkTextColor
            )
            Text(
                text = stringResource(R.string.status_paid),
                style = MaterialTheme.typography.h2,
                color = SuccessColor
            )
        }
    }
}


@Composable
fun CancelButtonComposable(navController: NavController) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.largeSpacing))

    Button(
        modifier = buttonModifier,
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.buttonBordersWidth),
            brush = SolidColor(CancelButtonColor)
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = ScreenBackgroundColor,
            contentColor = CancelButtonColor,
            disabledContentColor = LightTextColor
        ),
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.buttonCornersSize))),
        onClick = {

        },
    ) {
        Text(stringResource(R.string.button_cancel_booking))
    }
}