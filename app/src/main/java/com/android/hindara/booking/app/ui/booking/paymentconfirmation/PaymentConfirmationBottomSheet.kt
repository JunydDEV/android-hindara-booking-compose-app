package com.android.hindara.booking.app.ui.booking.paymentconfirmation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.data.bottomsheets.BottomSheetState
import com.android.hindara.booking.app.ui.BottomSheetContentWithTitle

/**
 * Bottom sheet to select the booking dates.
 *
 * @param viewModel provides data to UI from outside.
 * @param sheetState state of the bottom sheet.
 * @param bookingBottomSheetState holds the state of current bottom sheet.
 * @param function indicates the main screen content.
 * */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PaymentConfirmationBottomSheet(
    viewModel: PaymentConfirmationViewModel = hiltViewModel(),
    sheetState: ModalBottomSheetState,
    bookingBottomSheetState: MutableState<BottomSheetState>,
    function: @Composable () -> Unit
) {
    BottomSheetContentWithTitle(title = stringResource(id = R.string.title_confirm_payment)) {
        
    }
}