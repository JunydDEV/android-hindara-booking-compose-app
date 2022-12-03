package com.android.hindara.booking.app.ui.booking.paymentconfirmation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.bottomsheets.states.BottomSheetState
import com.android.hindara.booking.app.ui.BottomSheetContentWithTitle
import com.android.hindara.booking.app.ui.HindaraBottomSheet
import com.android.hindara.booking.app.ui.HindaraCard
import com.android.hindara.booking.app.ui.booking.BookingSharedViewModel
import com.android.hindara.booking.app.ui.booking.PaymentMethod
import com.android.hindara.booking.app.ui.common.bottomsheets.states.TransactionResultState
import com.android.hindara.booking.app.ui.home.Hotel
import com.android.hindara.booking.app.ui.hoteldetails.common.*
import com.android.hindara.booking.app.ui.theme.*
import com.android.hindara.booking.app.utils.getFormattedDate

/**
 * Bottom sheet to select the booking dates.
 *
 * @param viewModel provides data to UI from outside.
 * @param sheetState state of the bottom sheet.
 * @param bookingBottomSheetState holds the state of current bottom sheet.
 * @param mainScreenContent indicates the main screen content.
 * */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PaymentConfirmationBottomSheet(
    viewModel: BookingSharedViewModel,
    sheetState: ModalBottomSheetState,
    bookingBottomSheetState: MutableState<BottomSheetState>,
    mainScreenContent: @Composable () -> Unit
) {
    HindaraBottomSheet(
        sheetState = sheetState,
        sheetContent = {
            PaymentConfirmationContentComposable(viewModel, bookingBottomSheetState)
        },
        content = { mainScreenContent() }
    )
}

@Composable
private fun PaymentConfirmationContentComposable(
    viewModel: BookingSharedViewModel,
    bookingBottomSheetState: MutableState<BottomSheetState>
) {
    BottomSheetContentWithTitle(title = stringResource(id = R.string.title_confirm_payment)) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            HotelInfoComposable(viewModel.chosenHotel)
            BookingDatesComposable(viewModel.checkInDate, viewModel.checkOutDate)
            BookingBillComposable(viewModel)
            SelectedPaymentMethodComposable(viewModel.paymentMethod)
            ContinueButtonComposable(bookingBottomSheetState)
        }
    }
}

@Composable
fun BookingBillComposable(viewModel: BookingSharedViewModel) {
    HindaraCard {
        Column {
            BookedNightsComposable(viewModel)
            TaxesAndFeesComposable(viewModel)
            HorizontalLineComposable()
            TotalBillComposable(viewModel)
        }
    }
}

@Composable
fun TotalBillComposable(viewModel: BookingSharedViewModel) {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(dimensionResource(id = R.dimen.defaultSpacing))

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.wrapContentWidth(),
            text = stringResource(R.string.total_label),
            style = MaterialTheme.typography.h2,
            color = DarkTextColor
        )
        Text(
            modifier = Modifier.wrapContentWidth(),
            text = "$${viewModel.getTotalBill()}",
            style = MaterialTheme.typography.h2,
            color = DarkTextColor
        )
    }
}

@Composable
private fun HorizontalLineComposable() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.lineThickness))
            .background(BordersColor)
    )
}

@Composable
fun TaxesAndFeesComposable(viewModel: BookingSharedViewModel) {
    val tax = viewModel.chosenHotel.tax
    val taxTotal = viewModel.getTaxTotal()
    HindaraCommonRow(label = "Taxes & Fees ($tax%)", value = "$$taxTotal")
}

@Composable
fun BookedNightsComposable(viewModel: BookingSharedViewModel) {
    val nightsCount = viewModel.getReservedNightsCount()
    val totalOfReservedNights = viewModel.getTotalOfReservedNights()

    HindaraCommonRow(label = "$nightsCount Nights", value = "$$totalOfReservedNights")
}

@Composable
private fun SelectedPaymentMethodComposable(paymentMethod: PaymentMethod) {
    HindaraCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.defaultSpacing)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = paymentMethod.icon), contentDescription = null)
            Text(
                text = stringResource(id = paymentMethod.name),
                style = MaterialTheme.typography.h2,
                color = DarkTextColor
            )
            Text(
                text = stringResource(R.string.change_payment_method_label),
                style = MaterialTheme.typography.h2,
                color = SuccessColor
            )
        }
    }
}

@Composable
private fun ContinueButtonComposable(
    bookingBottomSheetState: MutableState<BottomSheetState>
) {
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
            bookingBottomSheetState.value = TransactionResultState.PaymentResultFailure
        },
    ) {
        Text(stringResource(R.string.button_continue_text))
    }
}
