package com.android.hindara.booking.app.ui.booking.dateselection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.android.hindara.booking.app.ui.home.HomeViewModel
import com.android.hindara.booking.app.ui.theme.*
import com.android.hindara.booking.app.utils.getFormattedDate
import java.time.LocalDate

/**
 * Bottom sheet to select the booking dates.
 *
 * @param viewModel provides data to UI from outside.
 * */
@Composable
fun DateSelectionBottomSheet(
    navController: NavController,
    viewModel: BookingSharedViewModel,
    homeViewModel: HomeViewModel
) {
    val selectionState = remember {
        mutableStateOf(Pair<LocalDate?, LocalDate?>(null, null))
    }
    DateSelectionContentComposable(navController, viewModel, selectionState, homeViewModel)
}

@Composable
private fun DateSelectionContentComposable(
    navController: NavController,
    viewModel: BookingSharedViewModel,
    selectionState: MutableState<Pair<LocalDate?, LocalDate?>>,
    homeViewModel: HomeViewModel
) {
    val mainModifier = Modifier
        .background(WhiteColor)
        .padding(dimensionResource(id = R.dimen.default_spacing))

    Column(modifier = mainModifier) {
        CalendarComposable(selectionState)
        SelectedDaysComposable(selectionState)
        if (hasBookingDatesChosen(selectionState)) {
            ContinueButtonComposable(navController, viewModel, selectionState, homeViewModel)
        }
    }
}

@Composable
private fun hasBookingDatesChosen(selectionState: MutableState<Pair<LocalDate?, LocalDate?>>) =
    selectionState.value.first != null && selectionState.value.second != null

@Composable
fun SelectedDaysComposable(selectionState: MutableState<Pair<LocalDate?, LocalDate?>>) {
    val currentSelectionState = selectionState.value
    val checkInDate = currentSelectionState.first
    val checkOutDate = currentSelectionState.second

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
                style = MaterialTheme.typography.body1,
                color = LightTextColor
            )

            Text(
                modifier = Modifier.wrapContentWidth(),
                text = checkInDate.getFormattedDate(),
                style = MaterialTheme.typography.h1,
                color = DarkTextColor
            )
        }

        Image(painter = painterResource(id = R.drawable.ic_from_to), contentDescription = null)

        Column {
            Text(
                modifier = Modifier.wrapContentWidth(),
                text = stringResource(R.string.label_check_out),
                style = MaterialTheme.typography.body1,
                color = LightTextColor
            )

            Text(
                modifier = Modifier.wrapContentWidth(),
                text = checkOutDate.getFormattedDate(),
                style = MaterialTheme.typography.h1,
                color = DarkTextColor
            )
        }
    }
}

@Composable
private fun ContinueButtonComposable(
    navController: NavController,
    viewModel: BookingSharedViewModel,
    selectionState: MutableState<Pair<LocalDate?, LocalDate?>>,
    homeViewModel: HomeViewModel
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
            val checkInDate = selectionState.value.first!!
            val checkOutDate = selectionState.value.second!!
            viewModel.checkInDate = checkInDate
            viewModel.checkOutDate = checkOutDate
            viewModel.chosenHotel = homeViewModel.getSelectedHotel()

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
