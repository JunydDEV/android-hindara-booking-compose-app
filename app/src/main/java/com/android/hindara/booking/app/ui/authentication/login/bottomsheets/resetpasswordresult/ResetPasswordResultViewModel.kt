package com.android.hindara.booking.app.ui.authentication.login.bottomsheets.resetpasswordresult

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.LoginBottomSheetState
import javax.inject.Inject

class ResetPasswordResultViewModel @Inject constructor() : ViewModel() {

    fun getResultIcon(bottomSheet: LoginBottomSheetState) =
        if(bottomSheet == LoginBottomSheetState.ResetPasswordSuccess) {
            R.drawable.ic_success
        } else {
            R.drawable.ic_failed
        }


    fun getTitle(bottomSheet: LoginBottomSheetState) =
        if(bottomSheet == LoginBottomSheetState.ResetPasswordSuccess) {
            R.string.reset_password_success_title
        } else {
            R.string.reset_password_failure_title
        }

    fun getDescription(bottomSheet: LoginBottomSheetState) =
        if(bottomSheet == LoginBottomSheetState.ResetPasswordSuccess) {
            R.string.reset_password_success_description
        } else {
            R.string.reset_password_failure_description
        }

    fun getButtonText(bottomSheet: LoginBottomSheetState) =
        if(bottomSheet == LoginBottomSheetState.ResetPasswordSuccess) {
            R.string.button_login_now_text
        } else {
            R.string.button_try_again_text
        }

}