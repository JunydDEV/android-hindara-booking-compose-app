package com.android.hindara.booking.app.data
sealed class JobFlow {
    object Authentication : JobFlow()
    object Booking : JobFlow()
}