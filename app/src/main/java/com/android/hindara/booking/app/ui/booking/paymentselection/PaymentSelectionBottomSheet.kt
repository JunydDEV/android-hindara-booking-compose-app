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
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.bottomsheets.states.BookingBottomSheetState
import com.android.hindara.booking.app.ui.BottomSheetContentWithTitle
import com.android.hindara.booking.app.ui.HindaraCard
import com.android.hindara.booking.app.ui.booking.BookingSharedViewModel
import com.android.hindara.booking.app.ui.booking.PaymentMethod
import com.android.hindara.booking.app.ui.booking.dateselection.calendarBottomSheetRoute
import com.android.hindara.booking.app.ui.booking.paymentconfirmation.paymentConfirmationBottomSheetRoute

/**
 * Bottom sheet to choose the payment method.
 *
 * @param viewModel provides data to UI from outside.
 * */
@Composable
fun PaymentMethodsBottomSheet(
    navController: NavController,
    viewModel: BookingSharedViewModel,
) {
    val paymentMethods = viewModel.getPaymentMethodsList()

    val paymentMethodSelectionState = remember {
        mutableStateOf<PaymentMethod?>(null)
    }

    PaymentSelectionContentComposable(
        navController = navController,
        viewModel = viewModel,
        paymentMethods = paymentMethods,
        paymentMethodSelectionState = paymentMethodSelectionState,
    )
}

@Composable
private fun PaymentSelectionContentComposable(
    navController: NavController,
    viewModel: BookingSharedViewModel,
    paymentMethods: List<PaymentMethod>,
    paymentMethodSelectionState: MutableState<PaymentMethod?>,
) {
    BottomSheetContentWithTitle(stringResource(R.string.title_payment_methods)) {
        PaymentMethodsListComposable(viewModel, paymentMethods, paymentMethodSelectionState)
        if (paymentMethodSelectionState.value != null) {
            ContinueButtonComposable(
                sharedViewModel = viewModel,
                navController = navController,
                paymentMethod =  paymentMethodSelectionState.value!!
            )
        }
    }
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
    navController: NavController,
    sharedViewModel: BookingSharedViewModel,
    paymentMethod: PaymentMethod
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
            sharedViewModel.paymentMethod = paymentMethod
            navController.navigate(paymentConfirmationBottomSheetRoute){
                this.popUpTo(paymentSelectionBottomSheetRoute) {
                    inclusive = true
                }
            }
        },
    ) {
        Text(stringResource(R.string.button_continue_text))
    }
}
