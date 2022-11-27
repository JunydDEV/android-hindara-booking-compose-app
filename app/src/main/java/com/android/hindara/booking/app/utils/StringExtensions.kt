package com.android.hindara.booking.app.utils

import java.util.*

fun String.toTitleCase(): String {
    return lowercase(Locale.ROOT)
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
}