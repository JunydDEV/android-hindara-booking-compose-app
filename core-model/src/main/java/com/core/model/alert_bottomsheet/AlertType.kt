package com.core.model.alert_bottomsheet

class AlertType {
    companion object {
        const val resetPasswordSuccess = "ResetPasswordSuccess"
        const val resetPasswordFailure = "ResetPasswordFailure"
        const val resetPasswordCompleted = "ResetPasswordCompleted"

        const val paymentResultSuccess = "PaymentResultSuccess"
        const val paymentResultFailure = "PaymentResultFailure"
        const val bookingCompleted = "BookingCompleted"

        const val cancelBookingConfirmation = "CancelBookingConfirmation"
        const val cancelBookingSuccess = "CancelBookingSuccess"
        const val cancelBookingFailure = "CancelBookingFailure"
        const val cancellationCompleted = "CancellationCompleted"
    }
}
