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
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.data.bottomsheets.BookingBottomSheetState
import com.android.hindara.booking.app.data.bottomsheets.BottomSheetState
import com.android.hindara.booking.app.ui.HindaraBottomSheet
import com.android.hindara.booking.app.ui.booking.BookingSharedViewModel
import com.android.hindara.booking.app.ui.theme.*
import com.android.hindara.booking.app.utils.getFormattedDate
import com.android.hindara.booking.app.utils.toTitleCase
import java.time.LocalDate
import java.util.*

/**
 * Bottom sheet to select the booking dates.
 *
 * @param viewModel provides data to UI from outside.
 * @param sheetState state of the bottom sheet.
 * @param bookingBottomSheetState holds the state of current bottom sheet.
 * @param mainScreenContent indicates the main screen content.
 * */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DateSelectionBottomSheet(
    viewModel: BookingSharedViewModel,
    sheetState: ModalBottomSheetState,
    bookingBottomSheetState: MutableState<BottomSheetState>,
    mainScreenContent: @Composable () -> Unit
) {
    val selectionState = remember {
        mutableStateOf(Pair<LocalDate?, LocalDate?>(null, null))
    }

    HindaraBottomSheet(
        sheetState = sheetState,
        sheetContent = {
            DateSelectionContentComposable(viewModel, selectionState, bookingBottomSheetState)
        },
        content = { mainScreenContent() }
    )
}

@Composable
private fun DateSelectionContentComposable(
    viewModel: BookingSharedViewModel,
    selectionState: MutableState<Pair<LocalDate?, LocalDate?>>,
    bookingBottomSheetState: MutableState<BottomSheetState>
) {
    val mainModifier = Modifier
        .background(WhiteColor)
        .padding(dimensionResource(id = R.dimen.defaultSpacing))

    Column(modifier = mainModifier) {
        CalendarComposable(selectionState)
        SelectedDaysComposable(selectionState)
        if (hasBookingDatesChosen(selectionState)) {
            ContinueButtonComposable(viewModel, bookingBottomSheetState, selectionState)
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
                text = stringResource(R.string.check_in_label),
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
                text = stringResource(R.string.check_out_label),
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
    viewModel: BookingSharedViewModel,
    bookingBottomSheetState: MutableState<BottomSheetState>,
    selectionState: MutableState<Pair<LocalDate?, LocalDate?>>
) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.defaultSpacing),
            bottom = dimensionResource(id = R.dimen.defaultSpacing)
        )
    Button(
        modifier = buttonModifier,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.buttonCornersSize))),
        onClick = {
            val checkInDate = selectionState.value.first!!
            val checkOutDate = selectionState.value.second!!
            viewModel.checkInDate = checkInDate
            viewModel.checkOutDate = checkOutDate

            bookingBottomSheetState.value = BookingBottomSheetState.PaymentMethodSelection
        },
    ) {
        Text(stringResource(R.string.button_continue_text))
    }
}
