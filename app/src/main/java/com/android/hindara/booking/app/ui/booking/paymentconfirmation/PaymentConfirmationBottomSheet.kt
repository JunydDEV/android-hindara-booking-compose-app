package com.android.hindara.booking.app.ui.booking.paymentconfirmation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.booking.BookingSharedViewModel
import com.android.hindara.booking.app.ui.common.bottomsheets.alertbottomsheet.alertBottomSheetRoute
import com.android.hindara.booking.app.ui.common.bottomsheets.composables.BottomSheetContentWithTitle
import com.android.hindara.booking.app.ui.common.composables.ApplicationCard
import com.android.hindara.booking.app.ui.hoteldetails.common.BookingDatesComposable
import com.android.hindara.booking.app.ui.hoteldetails.common.HindaraCommonRow
import com.android.hindara.booking.app.ui.hoteldetails.common.HotelInfoComposable
import com.android.hindara.booking.app.ui.theme.borders_color
import com.android.hindara.booking.app.ui.theme.success_color
import com.core.model.alert_bottomsheet.AlertType
import com.core.model.booking.PaymentMethod

@Composable
fun PaymentConfirmationBottomSheet(
    navController: NavController,
    viewModel: BookingSharedViewModel,
) {
    PaymentConfirmationContentComposable(navController, viewModel)
}

@Composable
private fun PaymentConfirmationContentComposable(
    navController: NavController,
    viewModel: BookingSharedViewModel,
) {
    BottomSheetContentWithTitle(title = stringResource(id = R.string.label_confirm_payment)) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            HotelInfoComposable(viewModel.chosenHotel)
            BookingDatesComposable(viewModel.checkInDate, viewModel.checkOutDate)
            BookingBillComposable(viewModel)
            SelectedPaymentMethodComposable(viewModel.paymentMethod)
            ContinueButtonComposable(navController)
        }
    }
}

@Composable
fun BookingBillComposable(viewModel: BookingSharedViewModel) {
    ApplicationCard {
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
        .padding(dimensionResource(id = R.dimen.default_spacing))

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.wrapContentWidth(),
            text = stringResource(R.string.label_total_amount),
            style = MaterialTheme.typography.h2
        )
        Text(
            modifier = Modifier.wrapContentWidth(),
            text = "$${viewModel.getTotalBill()}",
            style = MaterialTheme.typography.h2
        )
    }
}

@Composable
private fun HorizontalLineComposable() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.menu_screen_horizontal_line_thickness))
            .background(borders_color)
    )
}

@Composable
fun TaxesAndFeesComposable(viewModel: BookingSharedViewModel) {
    val taxFormatter = stringResource(id = R.string.label_taxes_and_fees)
    val tax = taxFormatter.format(viewModel.chosenHotel.tax)
    val taxTotal = viewModel.getTaxTotal()
    HindaraCommonRow(label = tax, value = "$$taxTotal")
}

@Composable
fun BookedNightsComposable(viewModel: BookingSharedViewModel) {
    val nightsCountFormatter = stringResource(id = R.string.label_nights)
    val nightsCount = nightsCountFormatter.format(viewModel.getReservedNightsCount())
    val totalOfReservedNights = viewModel.getTotalOfReservedNights()

    HindaraCommonRow(label = nightsCount, value = "$$totalOfReservedNights")
}

@Composable
private fun SelectedPaymentMethodComposable(paymentMethod: PaymentMethod) {
    ApplicationCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.default_spacing)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = paymentMethod.icon), contentDescription = null)
            Text(
                text = stringResource(id = paymentMethod.name),
                style = MaterialTheme.typography.h2
            )
            Text(
                text = stringResource(R.string.label_change_payment_method),
                style = MaterialTheme.typography.h2,
                color = success_color
            )
        }
    }
}

@Composable
private fun ContinueButtonComposable(
    navController: NavController
) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.default_spacing),
            bottom = dimensionResource(id = R.dimen.default_spacing)
        )
    Button(
        modifier = buttonModifier,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.primary_button_corners_size))),
        onClick = {
            navController.navigate(
                alertBottomSheetRoute.replace(
                    "{type}", AlertType.paymentResultFailure)
            ) {
                popUpTo(paymentConfirmationBottomSheetRoute) {
                    inclusive = true
                }
            }
        },
    ) {
        Text(stringResource(R.string.button_continue_label))
    }
}
