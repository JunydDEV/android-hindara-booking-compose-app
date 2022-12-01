package com.android.hindara.booking.app.data

sealed class BookingBottomSheetState : BottomSheetState() {
    object DateSelection : BookingBottomSheetState()
    object PaymentSelection : BookingBottomSheetState()
}