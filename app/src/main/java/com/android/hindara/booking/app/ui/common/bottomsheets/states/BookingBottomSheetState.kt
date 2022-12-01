package com.android.hindara.booking.app.ui.common.bottomsheets.states

sealed class BookingBottomSheetState : BottomSheetState() {
    object DateSelection : BookingBottomSheetState()
    object PaymentMethodSelection : BookingBottomSheetState()
    object PaymentConfirmation : BookingBottomSheetState()
}