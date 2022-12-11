package com.android.hindara.booking.app.ui.common.bottomsheets.alertbottomsheet

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import com.core.model.alert_bottomsheet.AlertType
import com.core.model.alert_bottomsheet.BottomSheetContent
import javax.inject.Inject

class AlertViewModel @Inject constructor() : ViewModel() {

    fun getSheetContent(type: String): BottomSheetContent? {
        return when (type) {
            AlertType.resetPasswordSuccess -> {
                BottomSheetContent(
                    icon = R.drawable.ic_success,
                    title = R.string.label_reset_password_success,
                    description = R.string.label_reset_password_success_description,
                    buttonText = R.string.button_login_now_label
                )
            }
            AlertType.resetPasswordFailure -> {
                BottomSheetContent(
                    icon = R.drawable.ic_failed,
                    title = R.string.label_reset_password_failure,
                    description = R.string.label_reset_password_failure_description,
                    buttonText = R.string.button_try_again_label
                )
            }
            AlertType.paymentResultSuccess -> {
                BottomSheetContent(
                    icon = R.drawable.ic_success,
                    title = R.string.label_payment_result_success,
                    description = R.string.label_cancellation_success_description,
                    buttonText = R.string.button_back_to_home_label
                )
            }
            AlertType.paymentResultFailure -> {
                BottomSheetContent(
                    icon = R.drawable.ic_failed,
                    title = R.string.label_payment_result_failure,
                    description = R.string.label_cancellation_failure_description,
                    buttonText = R.string.button_try_again_label
                )
            }
            AlertType.cancelBookingConfirmation -> {
                BottomSheetContent(
                    icon = R.drawable.ic_alert,
                    title = R.string.label_cancel_booking,
                    description = R.string.label_cancel_booking_description,
                    buttonText = R.string.button_cancel_booking_label
                )
            }
            AlertType.cancelBookingFailure -> {
                BottomSheetContent(
                    icon = R.drawable.ic_failed,
                    title = R.string.label_cancellation_failure,
                    description = R.string.label_cancellation_failure_description,
                    buttonText = R.string.button_try_again_label
                )
            }
            AlertType.cancelBookingSuccess -> {
                BottomSheetContent(
                    icon = R.drawable.ic_success,
                    title = R.string.label_booking_cancellation_success,
                    description = R.string.label_cancellation_success_description,
                    buttonText = R.string.button_done_label
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