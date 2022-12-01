package com.android.hindara.booking.app.utils

import java.time.LocalDate

fun LocalDate.getFormattedDate(): String {
    val monthInTitleCase = this.month.name.toTitleCase()
    val monthShortName = monthInTitleCase.subSequence(0, 3)
    val selectedDay = this.dayOfMonth
    val selectedYear = this.year
    return "$monthShortName $selectedDay, $selectedYear"
}