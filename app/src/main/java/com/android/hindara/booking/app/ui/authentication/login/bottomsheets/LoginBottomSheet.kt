package com.android.hindara.booking.app.ui.authentication.login.bottomsheets

sealed class LoginBottomSheet {
    object ForgotPassword : LoginBottomSheet()
    object VerifyEmail: LoginBottomSheet()
    object ResetPassword: LoginBottomSheet()
    object ResetPasswordSuccess: LoginBottomSheet()
    object ResetPasswordFailure: LoginBottomSheet()
}
