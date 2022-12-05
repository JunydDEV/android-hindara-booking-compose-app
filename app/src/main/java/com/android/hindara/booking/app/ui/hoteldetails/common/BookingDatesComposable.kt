package com.android.hindara.booking.app.ui.hoteldetails.common

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.composables.HindaraCard
import com.android.hindara.booking.app.utils.getFormattedDate
import java.time.LocalDate

@Composable
fun BookingDatesComposable(checkInDate: LocalDate, checkOutDate: LocalDate) {
    HindaraCard {
        Column {
            HindaraCommonRow(
                label = stringResource(id = R.string.check_in_label),
                value = checkInDate.getFormattedDate()
            )
            HindaraCommonRow(
                label = stringResource(id = R.string.check_out_label),
                value = checkOutDate.getFormattedDate()
            )
        }
    }
}