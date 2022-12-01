package com.android.hindara.booking.app.ui.booking.paymentselection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.data.bottomsheets.BookingBottomSheetState
import com.android.hindara.booking.app.data.bottomsheets.BottomSheetState
import com.android.hindara.booking.app.ui.theme.BottomSheetBackgroundColor
import com.android.hindara.booking.app.ui.theme.DarkTextColor
import com.android.hindara.booking.app.ui.theme.WhiteColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PaymentMethodsBottomSheet(
    viewModel: PaymentSelectionViewModel = hiltViewModel(),
    sheetState: ModalBottomSheetState,
    bookingBottomSheetState: MutableState<BottomSheetState>,
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
            val mainModifier = Modifier
                .background(BottomSheetBackgroundColor)
                .padding(dimensionResource(id = R.dimen.defaultSpacing))
            Column(modifier = mainModifier) {
                PaymentMethodsTitleComposable()
                PaymentMethodsListComposable(viewModel)
                ContinueButtonComposable(bookingBottomSheetState)
            }
        },
    ) { function() }
}

@Composable
fun PaymentMethodsTitleComposable() {
    val smallSpacing = dimensionResource(id = R.dimen.smallSpacing)
    val defaultSpacing  = dimensionResource(id = R.dimen.defaultSpacing)
    val modifier = Modifier
        .fillMaxWidth()
        .padding(start = smallSpacing, end = smallSpacing, top = defaultSpacing)
    Text(
        modifier = modifier,
        text = stringResource(R.string.title_payment_methods),
        style = MaterialTheme.typography.h1,
        color = DarkTextColor
    )
}

@Composable
fun PaymentMethodsListComposable(viewModel: PaymentSelectionViewModel) {
    val paymentMethods = viewModel.getPaymentMethodsList()

    val paymentMethodSelectionState = remember {
        mutableStateOf(paymentMethods.first())
    }

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
    paymentMethod: PaymentMethod,
    paymentMethodSelectionState: MutableState<PaymentMethod>
) {
    val cardModifier = Modifier
        .fillMaxWidth()
        .padding(bottom = dimensionResource(id = R.dimen.defaultSpacing))

    Card(
        modifier = cardModifier,
        backgroundColor = WhiteColor,
        elevation = 0.dp,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.bottomSheetCornerSize))
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.defaultSpacing)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = paymentMethod.icon), contentDescription = null)
            Text(text = stringResource(id = paymentMethod.name))

            val selectionState =
                paymentMethod.name == paymentMethodSelectionState.value.name && paymentMethodSelectionState.value.isSelected

            RadioButton(selected = selectionState, enabled = true, onClick = {
                val item = paymentMethod.copy(isSelected = !paymentMethod.isSelected)
                paymentMethodSelectionState.value = item
            })
        }
    }
}

@Composable
private fun ContinueButtonComposable(bookingBottomSheetState: MutableState<BottomSheetState>) {
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
            bookingBottomSheetState.value = BookingBottomSheetState.PaymentMethodSelection
        },
    ) {
        Text(stringResource(R.string.button_continue_text))
    }
}
