package com.android.hindara.booking.app.ui.booking.paymentselection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.data.bottomsheets.BookingBottomSheetState
import com.android.hindara.booking.app.data.bottomsheets.BottomSheetState
import com.android.hindara.booking.app.ui.BottomSheetContentWithTitle
import com.android.hindara.booking.app.ui.HindaraCard
import com.android.hindara.booking.app.ui.booking.BookingSharedViewModel
import com.android.hindara.booking.app.ui.booking.PaymentMethod
import com.android.hindara.booking.app.ui.theme.BottomSheetBackgroundColor

/**
 * Bottom sheet to choose the payment method.
 *
 * @param viewModel provides data to UI from outside.
 * @param sheetState state of the bottom sheet.
 * @param bookingBottomSheetState holds the state of current bottom sheet.
 * @param function indicates the main screen content.
 * */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PaymentMethodsBottomSheet(
    viewModel: BookingSharedViewModel,
    sheetState: ModalBottomSheetState,
    bookingBottomSheetState: MutableState<BottomSheetState>,
    function: @Composable () -> Unit
) {
    val paymentMethods = viewModel.getPaymentMethodsList()

    val paymentMethodSelectionState = remember {
        mutableStateOf<PaymentMethod?>(null)
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetBackgroundColor = BottomSheetBackgroundColor,
        sheetShape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.bottomSheetCornerSize),
            topEnd = dimensionResource(id = R.dimen.bottomSheetCornerSize)
        ),
        sheetContent = {
            BottomSheetContentWithTitle(stringResource(R.string.title_payment_methods)) {
                PaymentMethodsListComposable(viewModel, paymentMethods, paymentMethodSelectionState)
                if (paymentMethodSelectionState.value != null) {
                    viewModel.paymentMethod = paymentMethodSelectionState.value!!
                    ContinueButtonComposable(bookingBottomSheetState)
                }
            }
        },
    ) { function() }
}

@Composable
fun PaymentMethodsListComposable(
    viewModel: BookingSharedViewModel,
    paymentMethods: List<PaymentMethod>,
    paymentMethodSelectionState: MutableState<PaymentMethod?>
) {
    val defaultSpacing = dimensionResource(id = R.dimen.defaultSpacing)
    LazyColumn(
        contentPadding = PaddingValues(top = defaultSpacing, bottom = defaultSpacing),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        items(paymentMethods.size) {
            PaymentMethodComposable(paymentMethods[it], paymentMethodSelectionState)
        }
    }
}

@Composable
fun PaymentMethodComposable(
    paymentMethod: PaymentMethod, paymentMethodSelectionState: MutableState<PaymentMethod?>
) {
    HindaraCard {
        Row(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.defaultSpacing)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val selectionState =
                paymentMethodSelectionState.value != null && paymentMethodSelectionState.value!!.name == paymentMethod.name

            Image(painter = painterResource(id = paymentMethod.icon), contentDescription = null)
            Text(text = stringResource(id = paymentMethod.name))
            RadioButton(selected = selectionState, enabled = true, onClick = {
                val item = paymentMethod.copy(isSelected = !paymentMethod.isSelected)
                paymentMethodSelectionState.value = item
            })
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

            bookingBottomSheetState.value = BookingBottomSheetState.PaymentConfirmation
        },
    ) {
        Text(stringResource(R.string.button_continue_text))
    }
}
