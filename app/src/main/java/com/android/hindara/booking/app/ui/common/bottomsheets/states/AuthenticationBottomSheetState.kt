package com.android.hindara.booking.app.ui.common.bottomsheets.states

sealed class AuthenticationBottomSheetState : BottomSheetState() {
    object ForgotPassword : AuthenticationBottomSheetState()
    object VerifyEmail : AuthenticationBottomSheetState()
    object ResetPassword : AuthenticationBottomSheetState()
}