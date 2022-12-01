package com.android.hindara.booking.app.data

sealed class LoginBottomSheetState : BottomSheetState() {
    object ForgotPassword : LoginBottomSheetState()
    object VerifyEmail : LoginBottomSheetState()
    object ResetPassword : LoginBottomSheetState()
    object ResetPasswordSuccess : LoginBottomSheetState()
    object ResetPasswordFailure : LoginBottomSheetState()
    object ResetPasswordCompleted : LoginBottomSheetState()
}