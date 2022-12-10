package com.android.hindara.booking.app.ui.booking.dateselection

import androidx.compose.foundation.background
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
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.PrimaryColor
import com.android.hindara.booking.app.ui.theme.RangeBackgroundColor
import com.android.hindara.booking.app.ui.theme.WhiteColor
import com.android.hindara.booking.app.utils.noRippleClickable
import com.android.hindara.booking.app.utils.toTitleCase
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarComposable(
    selectedDate: Pair<LocalDate?, LocalDate?>,
    onSelectDateValue: (Pair<LocalDate?, LocalDate?>) -> Unit,
) {
    CalendarContent(selectedDate, onSelectDateValue)
}
@Composable
fun CalendarContent(
    selectedDate: Pair<LocalDate?, LocalDate?>,
    onSelectDateValue: (Pair<LocalDate?, LocalDate?>) -> Unit
) {
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
        Day(it, selectedDate, onSelectDateValue)
    }, monthHeader = { month ->
        val daysOfWeek = month.weekDays.first().map { it.date.dayOfWeek }
        MonthHeader(daysOfWeek = daysOfWeek, month = month)
    })
}

@Composable
fun MonthHeader(daysOfWeek: List<DayOfWeek>, month: CalendarMonth) {
    val headerModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.large_spacing))

    Column(modifier = headerModifier) {
        CalendarTitle(month)
        val monthYearModifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.default_spacing))
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
    val titleModifier = Modifier.padding(start = dimensionResource(id = R.dimen.default_spacing))
    Text(
        modifier = titleModifier,
        text = "$monthName $year",
        style = MaterialTheme.typography.h2,
    )
}


@Composable
fun Day(
    day: CalendarDay,
    selectedDate: Pair<LocalDate?, LocalDate?>,
    onSelectDateValue: (Pair<LocalDate?, LocalDate?>) -> Unit
) {

    val backgroundShape = getCalendarDayBackgroundShape(day, selectedDate)
    val backgroundColor = getCalendarDayBackgroundColor(day, selectedDate)
    val selectedTextColor = getSelectionTextColor(day, selectedDate)
    val rangeBackgroundColor = getRangeSelectionBackgroundColor(day, selectedDate)

    val calendarDayModifier = Modifier
        .aspectRatio(1f)
        .noRippleClickable {
            if (isSelectionInvalid(day)) {
                return@noRippleClickable
            }
            onDaySelection(day, selectedDate, onSelectDateValue)
        }
    Box(modifier = calendarDayModifier, contentAlignment = Alignment.Center) {
        val rangeSelectionModifier =
            getRangeSelectionModifier(day, selectedDate, rangeBackgroundColor)

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
    selectedDate: Pair<LocalDate?, LocalDate?>,
    backgroundColor: Color
) = if (isSelectedDay(day, selectedDate)) {
    val shape = if (isStartSelectedDay(day, selectedDate)) {
        RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.hotel_content_corners_size),
            bottomStart = dimensionResource(id = R.dimen.hotel_content_corners_size))
    } else {
        RoundedCornerShape(
            topEnd = dimensionResource(id = R.dimen.hotel_content_corners_size),
            bottomEnd = dimensionResource(id = R.dimen.hotel_content_corners_size)
        )

    }
    Modifier
        .fillMaxWidth()
        .clip(shape)
        .height(dimensionResource(id = R.dimen.range_selection_shape_size))
        .background(backgroundColor)
} else {
    Modifier
        .fillMaxWidth()
        .height(dimensionResource(id = R.dimen.range_selection_shape_size))
        .background(backgroundColor)
}

@Composable
fun getRangeSelectionBackgroundColor(
    day: CalendarDay,
    selectedDate: Pair<LocalDate?, LocalDate?>
): Color {
    val startDate = selectedDate.first
    val endDate = selectedDate.second

    if (startDate == null || endDate == null) {
        return Color.Transparent
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

@Composable
fun getSelectionTextColor(
    day: CalendarDay,
    selectedDate: Pair<LocalDate?, LocalDate?>
): Color {
    return if (isSelectedDay(day, selectedDate)) {
        WhiteColor
    } else {
        getCalendarDayTextColor(day)
    }
}

private fun isSelectedDay(
    day: CalendarDay,
    selectedDate: Pair<LocalDate?, LocalDate?>
): Boolean {
    val startDate = selectedDate.first
    val endDate = selectedDate.second

    return startDate == day.date || endDate == day.date
}

private fun isStartSelectedDay(
    day: CalendarDay,
    selectedDate: Pair<LocalDate?, LocalDate?>
): Boolean {
    val startDate = selectedDate.first

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

@Composable
private fun getCalendarDayTextColor(day: CalendarDay) = when (day.position) {
    DayPosition.MonthDate -> Color.Unspecified
    DayPosition.InDate, DayPosition.OutDate -> Color.LightGray
}

@Composable
fun getCalendarDayBackgroundShape(
    day: CalendarDay,
    selectedDate: Pair<LocalDate?, LocalDate?>
): Shape {
    return if (isCalendarDayEqualsSelectedDay(day, selectedDate)) {
        CircleShape
    } else {
        RectangleShape
    }
}

@Composable
fun getCalendarDayBackgroundColor(
    day: CalendarDay,
    selectedDate: Pair<LocalDate?, LocalDate?>
): Color {
    return if (isCalendarDayEqualsSelectedDay(day, selectedDate)) {
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

fun onDaySelection(
    day: CalendarDay,
    selectedDate: Pair<LocalDate?, LocalDate?>,
    onSelectDateValue: (Pair<LocalDate?, LocalDate?>) -> Unit
) {

    var startDate = selectedDate.component1()
    var endDate = selectedDate.component2()

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
    onSelectDateValue(newPair)
}

private fun isSelected(startDaySelection: LocalDate?) = startDaySelection != null
