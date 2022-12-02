package com.android.hindara.booking.app.ui.booking

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import com.android.hindara.booking.app.ui.common.bottomsheets.states.BookingBottomSheetState
import com.android.hindara.booking.app.ui.common.bottomsheets.states.BottomSheetState
import com.android.hindara.booking.app.ui.booking.dateselection.DateSelectionBottomSheet
import com.android.hindara.booking.app.ui.booking.paymentconfirmation.PaymentConfirmationBottomSheet
import com.android.hindara.booking.app.ui.booking.paymentselection.PaymentMethodsBottomSheet
import com.android.hindara.booking.app.ui.common.bottomsheets.jobflowresult.JobFlowResultBottomSheet
import com.android.hindara.booking.app.ui.common.bottomsheets.states.TransactionResultState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookingBottomSheetsRouter(
    bookingSharedViewModel: BookingSharedViewModel,
    bottomSheetsVisibilityState: MutableState<BottomSheetState>,
    modalBottomSheetState: ModalBottomSheetState,
    mainScreenContent: @Composable (MutableState<BottomSheetState>, ModalBottomSheetState, CoroutineScope) -> Unit,
    coroutineScope: CoroutineScope
) {
    when (bottomSheetsVisibilityState.value) {
        BookingBottomSheetState.DateSelection -> {
            DateSelectionBottomSheet(
                viewModel = bookingSharedViewModel,
                sheetState = modalBottomSheetState,
                bookingBottomSheetState = bottomSheetsVisibilityState
            ) {
                mainScreenContent(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
            }
        }
        BookingBottomSheetState.PaymentMethodSelection -> {
            PaymentMethodsBottomSheet(
                viewModel = bookingSharedViewModel,
                sheetState = modalBottomSheetState,
                bookingBottomSheetState = bottomSheetsVisibilityState
            ) {
                mainScreenContent(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
            }
        }
        BookingBottomSheetState.PaymentConfirmation -> {
            PaymentConfirmationBottomSheet(
                viewModel = bookingSharedViewModel,
                sheetState = modalBottomSheetState,
                bookingBottomSheetState = bottomSheetsVisibilityState
            ) {
                mainScreenContent(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
            }
        }
        TransactionResultState.PaymentResultSuccess -> {
            JobFlowResultBottomSheet(
                modelBottomSheetState = modalBottomSheetState,
                bottomSheetState = bottomSheetsVisibilityState,
                resultState = TransactionResultState.PaymentResultSuccess
            ) {
                mainScreenContent(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
            }
        }
        TransactionResultState.PaymentResultFailure -> {
            JobFlowResultBottomSheet(
                modelBottomSheetState = modalBottomSheetState,
                bottomSheetState = bottomSheetsVisibilityState,
                resultState = TransactionResultState.PaymentResultFailure
            ) {
                mainScreenContent(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
            }
        }
        else -> {
            LaunchedEffect(key1 = modalBottomSheetState) {
                coroutineScope.launch {
                    modalBottomSheetState.hide()
                }
            }
            mainScreenContent(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
        }
    }
}