package com.android.hindara.booking.app.ui.booking

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import com.android.hindara.booking.app.ui.theme.PrimaryColor
import com.android.hindara.booking.app.ui.theme.WhiteColor
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarComposable() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        CalendarContent()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarContent() {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) }
    val endMonth = remember { currentMonth.plusMonths(100) }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }
    val selectionState = remember {
        mutableStateOf(Pair<LocalDate?, LocalDate?>(null, null))
    }
    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek
    )

    HorizontalCalendar(state = state, dayContent = {
        Day(it, selectionState)
    }, monthHeader = { month ->
        val daysOfWeek = month.weekDays.first().map { it.date.dayOfWeek }
        MonthHeader(daysOfWeek = daysOfWeek)
    })
}

@Composable
fun MonthHeader(daysOfWeek: List<DayOfWeek>) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                text = dayOfWeek.name[0].toString(),
                style = MaterialTheme.typography.h1,
                color = Color.LightGray,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Day(day: CalendarDay, selectionState: MutableState<Pair<LocalDate?, LocalDate?>>) {

    val backgroundShape = getCalendarDayBackgroundShape(day, selectionState)
    val backgroundColor = getCalendarDayBackgroundColor(day, selectionState)
    val selectedTextColor = getSelectionTextColor(day, selectionState)

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(backgroundShape)
            .background(backgroundColor)
            .clickable {
                if (isSelectionInvalid(day)) {
                    return@clickable
                }
                onDaySelection(day, selectionState)
            }, contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.date.dayOfMonth.toString(), color = selectedTextColor
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getSelectionTextColor(
    day: CalendarDay,
    selectionState: MutableState<Pair<LocalDate?, LocalDate?>>
): Color {
    val currentSelectionState = selectionState.value
    return if(currentSelectionState.first?.dayOfMonth == day.date.dayOfMonth ||
        currentSelectionState.second?.dayOfMonth == day.date.dayOfMonth) {
        WhiteColor
    } else{
        getCalendarDayTextColor(day)
    }
}

private fun getCalendarDayTextColor(day: CalendarDay) = when (day.position) {
    DayPosition.MonthDate -> Color.Unspecified
    DayPosition.InDate, DayPosition.OutDate -> Color.LightGray
}

@Composable
fun getCalendarDayBackgroundShape(
    day: CalendarDay,
    selectionState: MutableState<Pair<LocalDate?, LocalDate?>>
): Shape {
    val currentSelectionState = selectionState.value
    return if (isCalendarDayEqualsSelectedDay(day, currentSelectionState)) {
        CircleShape
    } else {
        RectangleShape
    }
}

@Composable
fun getCalendarDayBackgroundColor(
    day: CalendarDay,
    selectionState: MutableState<Pair<LocalDate?, LocalDate?>>
): Color {
    val currentSelectionState = selectionState.value
    return if (isCalendarDayEqualsSelectedDay(day, currentSelectionState)) {
        PrimaryColor
    } else {
        WhiteColor
    }
}

@Composable
private fun isCalendarDayEqualsSelectedDay(
    day: CalendarDay,
    currentSelectionState: Pair<LocalDate?, LocalDate?>
) = day.date == currentSelectionState.component1() ||
        day.date == currentSelectionState.component2()

fun isSelectionInvalid(day: CalendarDay): Boolean {
    val dayPosition = day.position
    return dayPosition == DayPosition.InDate || dayPosition == DayPosition.OutDate
}

@RequiresApi(Build.VERSION_CODES.O)
fun onDaySelection(day: CalendarDay, selectionState: MutableState<Pair<LocalDate?, LocalDate?>>) {
    val previousPair = selectionState.value

    var startDate = previousPair.component1()
    var endDate = previousPair.component2()

    if (!isSelected(startDate)) {
        startDate = day.date
    } else if (!isSelected(endDate)) {
        if (day.date.isBefore(startDate)) {
            startDate = day.date
        } else {
            endDate = day.date
        }
    } else {
        startDate = day.date // restart selection
        endDate = null // reset endData
    }

    val newPair = Pair(startDate, endDate)
    selectionState.value = newPair
}

private fun isSelected(startDaySelection: LocalDate?) = startDaySelection != null
