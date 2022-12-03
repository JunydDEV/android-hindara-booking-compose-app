package com.android.hindara.booking.app.ui.common.bottomsheets.states
sealed class JobFlow {
    object AuthenticationFlow : JobFlow()
    object BookingFlow : JobFlow()
}