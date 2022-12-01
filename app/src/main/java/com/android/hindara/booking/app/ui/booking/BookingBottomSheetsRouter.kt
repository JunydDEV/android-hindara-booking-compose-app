package com.android.hindara.booking.app.ui.booking

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.android.hindara.booking.app.data.bottomsheets.BookingBottomSheetState
import com.android.hindara.booking.app.data.bottomsheets.BottomSheetState
import com.android.hindara.booking.app.ui.booking.dateselection.DateSelectionBottomSheet
import com.android.hindara.booking.app.ui.booking.paymentselection.PaymentMethodsBottomSheet
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookingBottomSheetsRouter(
    bottomSheetsVisibilityState: MutableState<BottomSheetState>,
    modelBottomSheetState: ModalBottomSheetState,
    function: @Composable (MutableState<BottomSheetState>, ModalBottomSheetState, CoroutineScope) -> Unit,
    coroutineScope: CoroutineScope
) {
    when (bottomSheetsVisibilityState.value) {
        BookingBottomSheetState.DateSelection -> {
            DateSelectionBottomSheet(
                sheetState = modelBottomSheetState,
                bookingBottomSheetState = bottomSheetsVisibilityState
            ) {
                function(bottomSheetsVisibilityState, modelBottomSheetState, coroutineScope)
            }
        }
        BookingBottomSheetState.PaymentMethodSelection -> {
            PaymentMethodsBottomSheet(
                sheetState = modelBottomSheetState,
                bookingBottomSheetState = bottomSheetsVisibilityState
            ) {
                function(bottomSheetsVisibilityState, modelBottomSheetState, coroutineScope)
            }
        }
        else -> {

        }
    }
}