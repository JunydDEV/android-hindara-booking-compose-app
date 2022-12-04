package com.android.hindara.booking.app.ui.common.bottomsheets.jobflowresult

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.bottomsheets.states.AlertType
import javax.inject.Inject

class AlertViewModel @Inject constructor() : ViewModel() {

    fun getSheetContent(type: String): BottomSheetContent? {
        return when (type) {
            AlertType.resetPasswordSuccess -> {
                BottomSheetContent(
                    icon = R.drawable.ic_success,
                    title = R.string.reset_password_success_title,
                    description = R.string.reset_password_success_description,
                    buttonText = R.string.button_login_now_text
                )
            }
            AlertType.resetPasswordFailure -> {
                BottomSheetContent(
                    icon = R.drawable.ic_failed,
                    title = R.string.reset_password_failure_title,
                    description = R.string.reset_password_failure_description,
                    buttonText = R.string.button_try_again_text
                )
            }
            AlertType.paymentResultSuccess -> {
                BottomSheetContent(
                    icon = R.drawable.ic_success,
                    title = R.string.payment_result_success_title,
                    description = R.string.result_success_description,
                    buttonText = R.string.button_back_to_home_text
                )
            }
            AlertType.paymentResultFailure -> {
                BottomSheetContent(
                    icon = R.drawable.ic_failed,
                    title = R.string.payment_result_failure_title,
                    description = R.string.result_failure_description,
                    buttonText = R.string.button_try_again_text
                )
            }
            AlertType.cancelBookingConfirmation -> {
                BottomSheetContent(
                    icon = R.drawable.ic_alert,
                    title = R.string.title_cancel_booking,
                    description = R.string.description_cancel_booking,
                    buttonText = R.string.button_cancel_booking
                )
            }
            AlertType.cancelBookingFailure -> {
                BottomSheetContent(
                    icon = R.drawable.ic_failed,
                    title = R.string.booking_cancellation_result_failure_title,
                    description = R.string.result_failure_description,
                    buttonText = R.string.button_try_again_text
                )
            }
            AlertType.cancelBookingSuccess -> {
                BottomSheetContent(
                    icon = R.drawable.ic_success,
                    title = R.string.booking_cancellation_result_success_title,
                    description = R.string.result_success_description,
                    buttonText = R.string.button_done
                )
            }
            else -> {
                null
            }
        }
    }

    fun getResultType(type: String): String =
        when (type) {
            AlertType.paymentResultFailure -> {
                AlertType.paymentResultSuccess
            }
            AlertType.paymentResultSuccess -> {
                AlertType.bookingCompleted
            }
            AlertType.resetPasswordFailure -> {
                AlertType.resetPasswordSuccess
            }
            AlertType.resetPasswordSuccess -> {
                AlertType.resetPasswordCompleted
            }
            AlertType.cancelBookingConfirmation -> {
                AlertType.cancelBookingFailure
            }
            AlertType.cancelBookingFailure -> {
                AlertType.cancelBookingSuccess
            }
            AlertType.cancelBookingSuccess -> {
                AlertType.cancellationCompleted
            }
            else -> {
                AlertType.resetPasswordSuccess
            }
        }

    fun isTransactionCompleted(type: String): Boolean {
        return type == AlertType.bookingCompleted
                || type == AlertType.resetPasswordCompleted
                || type == AlertType.cancellationCompleted
    }
}

data class BottomSheetContent(
    val icon: Int,
    val title: Int,
    val description: Int,
    val buttonText: Int
)