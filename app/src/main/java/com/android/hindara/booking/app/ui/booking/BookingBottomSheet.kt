package com.android.hindara.booking.app.ui.booking

import android.os.Build
import android.view.ContextThemeWrapper
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.*
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.chrono.ChronoLocalDate
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookingBottomSheet(
    viewModel: BookingBottomSheetViewModel = hiltViewModel(),
    sheetState: ModalBottomSheetState,
    function: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetBackgroundColor = BottomSheetBackgroundColor,
        sheetShape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.bottomSheetCornerSize),
            topEnd = dimensionResource(id = R.dimen.bottomSheetCornerSize)
        ),
        sheetContent = {
            Column(
                Modifier
                    .background(WhiteColor)
                    .padding(dimensionResource(id = R.dimen.defaultSpacing))
            ) {
                CalendarComposable()
                SelectedDaysComposable()
                ContinueButtonComposable()
            }
        },
    ) { function() }
}

@Composable
fun SelectedDaysComposable() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                modifier = Modifier.wrapContentWidth(),
                text = stringResource(R.string.check_in_label),
                style = MaterialTheme.typography.body1,
                color = DarkTextColor
            )

            Text(
                modifier = Modifier.wrapContentWidth(),
                text = "May 17",
                style = MaterialTheme.typography.h1,
                color = DarkTextColor
            )
        }

        Image(painter = painterResource(id = R.drawable.ic_from_to), contentDescription = null)

        Column {
            Text(
                modifier = Modifier.wrapContentWidth(),
                text = stringResource(R.string.check_out_label),
                style = MaterialTheme.typography.body1,
                color = DarkTextColor
            )

            Text(
                modifier = Modifier.wrapContentWidth(),
                text = "May 21",
                style = MaterialTheme.typography.h1,
                color = DarkTextColor
            )
        }
    }
}


@Composable
private fun ContinueButtonComposable() {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.defaultSpacing),
            bottom = dimensionResource(id = R.dimen.defaultSpacing)
        )
    Button(
        modifier = buttonModifier,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.buttonCornersSize))),
        onClick = {
        },
    ) {
        Text(stringResource(R.string.button_continue_text))
    }
}
