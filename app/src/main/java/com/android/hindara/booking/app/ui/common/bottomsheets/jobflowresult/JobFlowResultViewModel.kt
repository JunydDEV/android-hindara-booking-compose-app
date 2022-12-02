package com.android.hindara.booking.app.ui.common.bottomsheets.jobflowresult

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.bottomsheets.states.TransactionResultState
import javax.inject.Inject

class JobFlowResultViewModel @Inject constructor() : ViewModel() {

    fun getResult(state: TransactionResultState): JobFlowResult? {
        return when (state) {
            TransactionResultState.ResetPasswordSuccess -> {
                JobFlowResult(
                    icon = R.drawable.ic_success,
                    title = R.string.reset_password_success_title,
                    description = R.string.reset_password_success_description,
                    buttonText = R.string.button_login_now_text
                )
            }
            TransactionResultState.ResetPasswordFailure -> {
                JobFlowResult(
                    icon = R.drawable.ic_failed,
                    title = R.string.reset_password_failure_title,
                    description = R.string.reset_password_failure_description,
                    buttonText = R.string.button_try_again_text
                )
            }
            TransactionResultState.PaymentResultSuccess -> {
                JobFlowResult(
                    icon = R.drawable.ic_success,
                    title = R.string.payment_result_success_title,
                    description = R.string.payment_result_success_description,
                    buttonText = R.string.button_back_to_home_text
                )
            }
            TransactionResultState.PaymentResultFailure -> {
                JobFlowResult(
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

data class JobFlowResult(
    val icon: Int,
    val title: Int,
    val description: Int,
    val buttonText: Int
)