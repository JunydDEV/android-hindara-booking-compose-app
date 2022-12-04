package com.android.hindara.booking.app.ui.common.bottomsheets.jobflowresult

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.bottomsheets.states.TransactionResultState
import javax.inject.Inject

class ResultViewModel @Inject constructor() : ViewModel() {

    fun getSheetContent(type: String): BottomSheetContent? {
        return when (type) {
            TransactionResultState.resetPasswordSuccess -> {
                BottomSheetContent(
                    icon = R.drawable.ic_success,
                    title = R.string.reset_password_success_title,
                    description = R.string.reset_password_success_description,
                    buttonText = R.string.button_login_now_text
                )
            }
            TransactionResultState.resetPasswordFailure -> {
                BottomSheetContent(
                    icon = R.drawable.ic_failed,
                    title = R.string.reset_password_failure_title,
                    description = R.string.reset_password_failure_description,
                    buttonText = R.string.button_try_again_text
                )
            }
            TransactionResultState.paymentResultSuccess -> {
                BottomSheetContent(
                    icon = R.drawable.ic_success,
                    title = R.string.payment_result_success_title,
                    description = R.string.payment_result_success_description,
                    buttonText = R.string.button_back_to_home_text
                )
            }
            TransactionResultState.paymentResultFailure -> {
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

    fun getResultType(type: String): String =
        when (type) {
            TransactionResultState.paymentResultFailure -> {
                TransactionResultState.paymentResultSuccess
            }
            TransactionResultState.paymentResultSuccess -> {
                TransactionResultState.bookingCompleted
            }
            TransactionResultState.resetPasswordFailure -> {
                TransactionResultState.resetPasswordSuccess
            }
            TransactionResultState.resetPasswordSuccess -> {
                TransactionResultState.resetPasswordCompleted
            }
            else -> {
                TransactionResultState.resetPasswordSuccess
            }
        }

    fun isTransactionCompleted(type: String): Boolean {
        return type == TransactionResultState.bookingCompleted
                || type == TransactionResultState.resetPasswordCompleted
    }
}

data class BottomSheetContent(
    val icon: Int,
    val title: Int,
    val description: Int,
    val buttonText: Int
)