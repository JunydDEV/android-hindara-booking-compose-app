package com.android.hindara.booking.app.ui.authentication.login.bottomsheets.resetpasswordresult

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.LoginBottomSheet
import javax.inject.Inject

class ResetPasswordResultViewModel @Inject constructor() : ViewModel() {

    fun getResultIcon(bottomSheet: LoginBottomSheet) =
        if(bottomSheet == LoginBottomSheet.ResetPasswordSuccess) {
            R.drawable.ic_success
        } else {
            R.drawable.ic_failed
        }


    fun getTitle(bottomSheet: LoginBottomSheet) =
        if(bottomSheet == LoginBottomSheet.ResetPasswordSuccess) {
            R.string.reset_password_success_title
        } else {
            R.string.reset_password_failure_title
        }

    fun getDescription(bottomSheet: LoginBottomSheet) =
        if(bottomSheet == LoginBottomSheet.ResetPasswordSuccess) {
            R.string.reset_password_success_description
        } else {
            R.string.reset_password_failure_description
        }

    fun getButtonText(bottomSheet: LoginBottomSheet) =
        if(bottomSheet == LoginBottomSheet.ResetPasswordSuccess) {
            R.string.button_login_now_text
        } else {
            R.string.button_try_again_text
        }

}