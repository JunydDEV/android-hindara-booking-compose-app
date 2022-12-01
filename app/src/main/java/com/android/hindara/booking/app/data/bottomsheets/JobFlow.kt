package com.android.hindara.booking.app.data.bottomsheets
sealed class JobFlow {
    object Authentication : JobFlow()
    object Booking : JobFlow()
}