package com.android.hindara.booking.app.ui.common.bottomsheets.jobflowresult

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.bottomsheets.states.TransactionResultState
import javax.inject.Inject

class ResultViewModel @Inject constructor() : ViewModel() {

    fun getSheetContent(type: String): BottomSheetContent? {
        return when (type) {
            TransactionResultState.ResetPasswordSuccess.javaClass.name -> {
                BottomSheetContent(
                    icon = R.drawable.ic_success,
                    title = R.string.reset_password_success_title,
                    description = R.string.reset_password_success_description,
                    buttonText = R.string.button_login_now_text
                )
            }
            TransactionResultState.ResetPasswordFailure.javaClass.name -> {
                BottomSheetContent(
                    icon = R.drawable.ic_failed,
                    title = R.string.reset_password_failure_title,
                    description = R.string.reset_password_failure_description,
                    buttonText = R.string.button_try_again_text
                )
            }
            TransactionResultState.PaymentResultSuccess.javaClass.name -> {
                BottomSheetContent(
                    icon = R.drawable.ic_success,
                    title = R.string.payment_result_success_title,
                    description = R.string.payment_result_success_description,
                    buttonText = R.string.button_back_to_home_text
                )
            }
            TransactionResultState.PaymentResultFailure.javaClass.name -> {
                BottomSheetContent(
                    icon = R.drawable.ic_failed,
                    title = R.string.payment_result_failure_title,
                    description = R.string.payment_result_failure_description,
                    buttonText = R.string.button_try_again_text
                )
            }
            else -> {
                null
            }
        }
    }
}

data class BottomSheetContent(
    val icon: Int,
    val title: Int,
    val description: Int,
    val buttonText: Int
)