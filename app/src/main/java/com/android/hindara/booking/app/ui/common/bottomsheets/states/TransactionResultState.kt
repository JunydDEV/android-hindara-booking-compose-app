package com.android.hindara.booking.app.ui.common.bottomsheets.states

sealed class TransactionResultState : BottomSheetState() {
    object ResetPasswordSuccess : TransactionResultState()
    object ResetPasswordFailure : TransactionResultState()
    object ResetPasswordCompleted : TransactionResultState()

    object PaymentResultSuccess : TransactionResultState()
    object PaymentResultFailure : TransactionResultState()
    object BookingCompleted : TransactionResultState()
}
