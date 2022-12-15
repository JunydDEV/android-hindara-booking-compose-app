package com.android.hindara.booking.app.ui.booking.dateselection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.booking.BookingSharedViewModel
import com.android.hindara.booking.app.ui.booking.paymentselection.paymentSelectionBottomSheetRoute
import com.android.hindara.booking.app.utils.getFormattedDate
import com.core.model.hotel_details.Hotel
import java.time.LocalDate

@Composable
fun DateSelectionBottomSheet(
    navController: NavController,
    viewModel: BookingSharedViewModel,
    hotel: Hotel
) {
    val selectionState = remember {
        mutableStateOf(Pair<LocalDate?, LocalDate?>(null, null))
    }
    DateSelectionContentComposable(
        navController = navController,
        viewModel = viewModel,
        selectedDate = selectionState.value,
        onSelectDateValue = { selectionState.value = it },
        hotel = hotel
    )
}

@Composable
private fun DateSelectionContentComposable(
    navController: NavController,
    viewModel: BookingSharedViewModel,
    selectedDate: Pair<LocalDate?, LocalDate?>,
    onSelectDateValue: (Pair<LocalDate?, LocalDate?>) -> Unit,
    hotel: Hotel
) {
    val mainModifier = Modifier
        .background(MaterialTheme.colors.background)
        .padding(dimensionResource(id = R.dimen.default_spacing))

    Column(modifier = mainModifier) {
        CalendarComposable(selectedDate, onSelectDateValue)
        SelectedDaysComposable(selectedDate)
        if (hasBookingDatesChosen(selectedDate)) {
            ContinueButtonComposable(navController, viewModel, selectedDate, hotel)
        }
    }
}

@Composable
private fun hasBookingDatesChosen(selectedDate: Pair<LocalDate?, LocalDate?>) =
    selectedDate.first != null && selectedDate.second != null

@Composable
fun SelectedDaysComposable(selectedDate: Pair<LocalDate?, LocalDate?>) {
    val checkInDate = selectedDate.first
    val checkOutDate = selectedDate.second

    if (checkInDate == null || checkOutDate == null)
        return

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                modifier = Modifier.wrapContentWidth(),
                text = stringResource(R.string.label_check_in),
                style = MaterialTheme.typography.body1
            )

            Text(
                modifier = Modifier.wrapContentWidth(),
                text = checkInDate.getFormattedDate(),
                style = MaterialTheme.typography.h1
            )
        }

        Image(painter = painterResource(id = R.drawable.ic_from_to), contentDescription = null)

        Column {
            Text(
                modifier = Modifier.wrapContentWidth(),
                text = stringResource(R.string.label_check_out),
                style = MaterialTheme.typography.body1
            )

            Text(
                modifier = Modifier.wrapContentWidth(),
                text = checkOutDate.getFormattedDate(),
                style = MaterialTheme.typography.h1
            )
        }
    }
}

@Composable
private fun ContinueButtonComposable(
    navController: NavController,
    viewModel: BookingSharedViewModel,
    selectedDate: Pair<LocalDate?, LocalDate?>,
    hotel: Hotel
) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.default_spacing),
            bottom = dimensionResource(id = R.dimen.default_spacing)
        )
    Button(
        modifier = buttonModifier,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.primary_button_corners_size))),
        onClick = {
            val checkInDate = selectedDate.first!!
            val checkOutDate = selectedDate.second!!
            viewModel.checkInDate = checkInDate
            viewModel.checkOutDate = checkOutDate
            viewModel.chosenHotel = hotel

            navController.navigate(paymentSelectionBottomSheetRoute) {
                this.popUpTo(calendarBottomSheetRoute) {
                    inclusive = true
                }
            }
        },
    ) {
        Text(stringResource(R.string.button_continue_label))
    }
}
