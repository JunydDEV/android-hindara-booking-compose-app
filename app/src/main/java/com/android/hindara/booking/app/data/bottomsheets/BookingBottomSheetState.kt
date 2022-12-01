package com.android.hindara.booking.app.data.bottomsheets

sealed class BookingBottomSheetState : BottomSheetState() {
    object DateSelection : BookingBottomSheetState()
    object PaymentMethodSelection : BookingBottomSheetState()
}