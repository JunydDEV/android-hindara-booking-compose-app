package com.android.hindara.booking.app.ui.common.bottomsheets.jobflowresult

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.bottomsheets.states.JobFlowResultState
import javax.inject.Inject

class JobFlowResultViewModel @Inject constructor() : ViewModel() {

    fun getResult(state: JobFlowResultState): JobFlowResult? {
        return when (state) {
            JobFlowResultState.ResetPasswordSuccess -> {
                JobFlowResult(
                    icon = R.drawable.ic_success,
                    title = R.string.reset_password_success_title,
                    description = R.string.reset_password_success_description,
                    buttonText = R.string.button_login_now_text
                )
            }
            JobFlowResultState.ResetPasswordFailure -> {
                JobFlowResult(
                    icon = R.drawable.ic_failed,
                    title = R.string.reset_password_failure_title,
                    description = R.string.reset_password_failure_description,
                    buttonText = R.string.button_try_again_text
                )
            }
            JobFlowResultState.PaymentResultSuccess -> {
                JobFlowResult(
                    icon = R.drawable.ic_success,
                    title = R.string.payment_result_success_title,
                    description = R.string.payment_result_success_description,
                    buttonText = R.string.button_back_to_home_text
                )
            }
            JobFlowResultState.PaymentResultFailure -> {
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