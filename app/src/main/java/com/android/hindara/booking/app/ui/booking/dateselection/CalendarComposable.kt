package com.android.hindara.booking.app.ui.booking.dateselection

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.hindara.booking.app.ui.theme.PrimaryColor
import com.android.hindara.booking.app.ui.theme.RangeBackgroundColor
import com.android.hindara.booking.app.ui.theme.WhiteColor
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import com.kizitonwose.calendar.core.CalendarMonth
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.utils.toTitleCase

@Composable
fun CalendarComposable(selectionState: MutableState<Pair<LocalDate?, LocalDate?>>) {
    CalendarContent(selectionState)
}
@Composable
fun CalendarContent(selectionState: MutableState<Pair<LocalDate?, LocalDate?>>) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) }
    val endMonth = remember { currentMonth.plusMonths(100) }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }

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
        MonthHeader(daysOfWeek = daysOfWeek, month = month)
    })
}

@Composable
fun MonthHeader(daysOfWeek: List<DayOfWeek>, month: CalendarMonth) {
    val headerModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.largeSpacing))

    Column(modifier = headerModifier) {
        CalendarTitle(month)
        val monthYearModifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.defaultSpacing))
        Row(modifier = monthYearModifier) {
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
}

@Composable
fun CalendarTitle(month: CalendarMonth) {
    val year = month.yearMonth.year
    val monthName = month.yearMonth.month.name.toTitleCase()
    val titleModifier = Modifier.padding(start = dimensionResource(id = R.dimen.defaultSpacing))
    Text(
        modifier = titleModifier,
        text = "$monthName $year",
        style = MaterialTheme.typography.h2,
    )
}


@Composable
fun Day(day: CalendarDay, selectionState: MutableState<Pair<LocalDate?, LocalDate?>>) {

    val backgroundShape = getCalendarDayBackgroundShape(day, selectionState)
    val backgroundColor = getCalendarDayBackgroundColor(day, selectionState)
    val selectedTextColor = getSelectionTextColor(day, selectionState)
    val rangeBackgroundColor = getRangeSelectionBackgroundColor(day, selectionState)

    val calendarDayModifier = Modifier
        .aspectRatio(1f)
        .clickable {
            if (isSelectionInvalid(day)) {
                return@clickable
            }
            onDaySelection(day, selectionState)
        }
    Box(modifier = calendarDayModifier, contentAlignment = Alignment.Center) {
        val rangeSelectionModifier =
            getRangeSelectionModifier(day, selectionState, rangeBackgroundColor)

        val daySelectionModifier = Modifier
            .clip(backgroundShape)
            .background(backgroundColor)
            .fillMaxSize()

        Spacer(modifier = rangeSelectionModifier)
        Spacer(modifier = daySelectionModifier)
        Text(text = day.date.dayOfMonth.toString(), color = selectedTextColor)
    }
}

@Composable
private fun getRangeSelectionModifier(
    day: CalendarDay,
    selectionState: MutableState<Pair<LocalDate?, LocalDate?>>,
    backgroundColor: Color
) = if (isSelectedDay(day, selectionState)) {
    val shape = if (isStartSelectedDay(day, selectionState)) {
        RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp)
    } else {
        RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp)

    }
    Modifier
        .fillMaxWidth()
        .clip(shape)
        .height(30.dp)
        .background(backgroundColor)
} else {
    Modifier
        .fillMaxWidth()
        .height(30.dp)
        .background(backgroundColor)
}

fun getRangeSelectionBackgroundColor(
    day: CalendarDay, selectionState: MutableState<Pair<LocalDate?, LocalDate?>>
): Color {
    val currentSelectionState = selectionState.value
    val startDate = currentSelectionState.first
    val endDate = currentSelectionState.second

    if (startDate == null || endDate == null) {
        return WhiteColor
    }

    return if (
        (day.date == startDate || day.date == endDate) ||
        (day.date.isAfter(startDate) && day.date.isBefore(endDate))
    ) {
        RangeBackgroundColor
    } else {
        Color.Transparent
    }
}

fun getSelectionTextColor(
    day: CalendarDay, selectionState: MutableState<Pair<LocalDate?, LocalDate?>>
): Color {
    return if (isSelectedDay(day, selectionState)) {
        WhiteColor
    } else {
        getCalendarDayTextColor(day)
    }
}

private fun isSelectedDay(
    day: CalendarDay,
    selectionState: MutableState<Pair<LocalDate?, LocalDate?>>
): Boolean {
    val currentSelectionState = selectionState.value
    val startDate = currentSelectionState.first
    val endDate = currentSelectionState.second

    return startDate == day.date || endDate == day.date
}

private fun isStartSelectedDay(
    day: CalendarDay,
    selectionState: MutableState<Pair<LocalDate?, LocalDate?>>
): Boolean {
    val currentSelectionState = selectionState.value
    val startDate = currentSelectionState.first

    return startDate?.dayOfMonth == day.date.dayOfMonth
}

private fun isEndSelectedDay(
    day: CalendarDay,
    selectionState: MutableState<Pair<LocalDate?, LocalDate?>>
): Boolean {
    val currentSelectionState = selectionState.value
    val endDate = currentSelectionState.second

    return endDate?.dayOfMonth == day.date.dayOfMonth
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
        Color.Transparent
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
        endDate = null // reset endDate
    }

    val newPair = Pair(startDate, endDate)
    selectionState.value = newPair
}

private fun isSelected(startDaySelection: LocalDate?) = startDaySelection != null
