package com.android.hindara.booking.app.ui.common.bottomsheets.states

sealed class LoginBottomSheetState : BottomSheetState() {
    object ForgotPassword : LoginBottomSheetState()
    object VerifyEmail : LoginBottomSheetState()
    object ResetPassword : LoginBottomSheetState()
}