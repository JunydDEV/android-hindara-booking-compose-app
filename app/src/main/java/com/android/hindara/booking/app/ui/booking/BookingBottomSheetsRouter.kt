package com.android.hindara.booking.app.ui.booking

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.hindara.booking.app.data.bottomsheets.BookingBottomSheetState
import com.android.hindara.booking.app.data.bottomsheets.BottomSheetState
import com.android.hindara.booking.app.ui.booking.dateselection.DateSelectionBottomSheet
import com.android.hindara.booking.app.ui.booking.paymentconfirmation.PaymentConfirmationBottomSheet
import com.android.hindara.booking.app.ui.booking.paymentselection.PaymentMethodsBottomSheet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookingBottomSheetsRouter(
    bookingSharedViewModel: BookingSharedViewModel,
    bottomSheetsVisibilityState: MutableState<BottomSheetState>,
    modalBottomSheetState: ModalBottomSheetState,
    function: @Composable (MutableState<BottomSheetState>, ModalBottomSheetState, CoroutineScope) -> Unit,
    coroutineScope: CoroutineScope
) {
    when (bottomSheetsVisibilityState.value) {
        BookingBottomSheetState.DateSelection -> {
            DateSelectionBottomSheet(
                viewModel = bookingSharedViewModel,
                sheetState = modalBottomSheetState,
                bookingBottomSheetState = bottomSheetsVisibilityState
            ) {
                function(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
            }
        }
        BookingBottomSheetState.PaymentMethodSelection -> {
            PaymentMethodsBottomSheet(
                viewModel = bookingSharedViewModel,
                sheetState = modalBottomSheetState,
                bookingBottomSheetState = bottomSheetsVisibilityState
            ) {
                function(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
            }
        }
        BookingBottomSheetState.PaymentConfirmation -> {
            PaymentConfirmationBottomSheet(
                viewModel = bookingSharedViewModel,
                sheetState = modalBottomSheetState,
                bookingBottomSheetState = bottomSheetsVisibilityState
            ) {
                function(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
            }
        }
        else -> {
            LaunchedEffect(key1 = modalBottomSheetState) {
                coroutineScope.launch {
                    modalBottomSheetState.hide()
                }
            }
            function(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
        }
    }
}