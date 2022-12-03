package com.android.hindara.booking.app.ui.appmenu.mybookings.bottomsheets.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.BottomSheetContentWithTitle
import com.android.hindara.booking.app.ui.HindaraBottomSheet
import com.android.hindara.booking.app.ui.HindaraCard
import com.android.hindara.booking.app.ui.booking.PaymentMethod
import com.android.hindara.booking.app.ui.common.bottomsheets.states.BottomSheetState
import com.android.hindara.booking.app.ui.home.Hotel
import com.android.hindara.booking.app.ui.hoteldetails.common.*
import com.android.hindara.booking.app.ui.theme.DarkTextColor
import com.android.hindara.booking.app.ui.theme.SuccessColor
import java.time.LocalDate

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookingDetailsBottomSheet(
    viewModel: BookingDetailsViewModel = hiltViewModel(),
    hotel: Hotel,
    checkInDate: LocalDate,
    checkoutDate: LocalDate,
    sheetState: ModalBottomSheetState,
    myBookingsBottomSheetState: MutableState<BottomSheetState>,
    mainScreenContent: @Composable () -> Unit
) {
    HindaraBottomSheet(
        sheetState = sheetState,
        sheetContent = { BookingDetailsContent(myBookingsBottomSheetState, hotel, checkInDate, checkoutDate) },
        content = { mainScreenContent() }
    )
}

@Composable
fun BookingDetailsContent(
    myBookingsBottomSheetState: MutableState<BottomSheetState>,
    hotel: Hotel,
    checkInDate: LocalDate,
    checkOutDate: LocalDate
) {
    BottomSheetContentWithTitle(title = stringResource(R.string.booking_details)) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            HotelInfoComposable(hotel)
            BookingDatesComposable(checkInDate, checkOutDate)
            PaymentStatusComposable()
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