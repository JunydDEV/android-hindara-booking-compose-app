package com.android.hindara.booking.app.ui.booking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.*
import com.android.hindara.booking.app.utils.toTitleCase
import java.time.LocalDate
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookingBottomSheet(
    viewModel: BookingBottomSheetViewModel = hiltViewModel(),
    sheetState: ModalBottomSheetState,
    function: @Composable () -> Unit
) {
    val selectionState = remember {
        mutableStateOf(Pair<LocalDate?, LocalDate?>(null, null))
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetBackgroundColor = BottomSheetBackgroundColor,
        sheetShape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.bottomSheetCornerSize),
            topEnd = dimensionResource(id = R.dimen.bottomSheetCornerSize)
        ),
        sheetContent = {
            val mainModifier = Modifier
                .background(WhiteColor)
                .padding(dimensionResource(id = R.dimen.defaultSpacing))
            Column(modifier = mainModifier) {
                CalendarComposable(selectionState)
                SelectedDaysComposable(selectionState)
                ContinueButtonComposable()
            }
        },
    ) { function() }
}

@Composable
fun SelectedDaysComposable(selectionState: MutableState<Pair<LocalDate?, LocalDate?>>) {
    val currentSelectionState = selectionState.value
    val checkInDate = currentSelectionState.first
    val checkOutDate = currentSelectionState.second

    if(checkInDate == null || checkOutDate == null)
        return

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
                text = "${
                    checkInDate.month?.name?.toTitleCase().toString().subSequence(0, 3)
                } ${checkInDate.dayOfMonth}",
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
                text = "${checkOutDate.month?.name?.subSequence(0, 3)} ${checkOutDate.dayOfMonth}",
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
