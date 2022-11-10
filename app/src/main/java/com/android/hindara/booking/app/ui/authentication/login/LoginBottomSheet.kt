package com.android.hindara.booking.app.ui.authentication.login

sealed class LoginBottomSheet {
    object ForgotPassword : LoginBottomSheet()
    object VerifyEmail: LoginBottomSheet()
    object ResetPassword: LoginBottomSheet()
}
