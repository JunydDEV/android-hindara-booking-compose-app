package com.android.hindara.booking.app.ui.common.bottomsheets.states

sealed class JobFlowResultState : BottomSheetState() {
    object ResetPasswordSuccess : JobFlowResultState()
    object ResetPasswordFailure : JobFlowResultState()
    object ResetPasswordCompleted : JobFlowResultState()

    object PaymentResultSuccess : JobFlowResultState()
    object PaymentResultFailure : JobFlowResultState()
    object BookingCompleted : JobFlowResultState()
}
