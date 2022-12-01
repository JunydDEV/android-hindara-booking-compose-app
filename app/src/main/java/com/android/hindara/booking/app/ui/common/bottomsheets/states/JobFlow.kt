package com.android.hindara.booking.app.ui.common.bottomsheets.states
sealed class JobFlow {
    object Authentication : JobFlow()
    object Booking : JobFlow()
}