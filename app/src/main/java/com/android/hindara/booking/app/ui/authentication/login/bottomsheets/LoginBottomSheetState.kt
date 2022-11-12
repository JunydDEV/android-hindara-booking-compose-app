package com.android.hindara.booking.app.ui.authentication.login.bottomsheets

sealed class LoginBottomSheetState {
    object ForgotPassword : LoginBottomSheetState()
    object VerifyEmail: LoginBottomSheetState()
    object ResetPassword: LoginBottomSheetState()
    object ResetPasswordSuccess: LoginBottomSheetState()
    object ResetPasswordFailure: LoginBottomSheetState()
}
