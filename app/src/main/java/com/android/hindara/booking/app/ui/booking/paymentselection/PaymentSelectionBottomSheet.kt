package com.android.hindara.booking.app.ui.booking.paymentselection

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.bottomsheets.composables.BottomSheetContentWithTitle
import com.android.hindara.booking.app.ui.common.composables.ApplicationCard
import com.android.hindara.booking.app.ui.booking.BookingSharedViewModel
import com.android.hindara.booking.app.ui.booking.paymentconfirmation.paymentConfirmationBottomSheetRoute
import com.core.model.booking.PaymentMethod

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
        paymentMethodsList = paymentMethods,
        paymentMethod = paymentMethodSelectionState.value,
        onPaymentSelection = { paymentMethodSelectionState.value = it }
    )
}

@Composable
private fun PaymentSelectionContentComposable(
    navController: NavController,
    viewModel: BookingSharedViewModel,
    paymentMethodsList: List<PaymentMethod>,
    paymentMethod: PaymentMethod?,
    onPaymentSelection: (PaymentMethod?) -> Unit,
) {
    BottomSheetContentWithTitle(stringResource(R.string.label_payment_methods)) {
        PaymentMethodsListComposable(
            viewModel = viewModel,
            paymentMethods = paymentMethodsList,
            paymentMethod = paymentMethod,
            onPaymentSelection = onPaymentSelection
        )
        if (paymentMethod != null) {
            ContinueButtonComposable(
                sharedViewModel = viewModel,
                navController = navController,
                paymentMethod =  paymentMethod
            )
        }
    }
}

@Composable
fun PaymentMethodsListComposable(
    viewModel: BookingSharedViewModel,
    paymentMethods: List<PaymentMethod>,
    paymentMethod: PaymentMethod?,
    onPaymentSelection: (PaymentMethod?) -> Unit
) {
    val defaultSpacing = dimensionResource(id = R.dimen.default_spacing)
    LazyColumn(
        contentPadding = PaddingValues(top = defaultSpacing, bottom = defaultSpacing),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        items(paymentMethods.size) {
            PaymentMethodComposable(paymentMethods[it], paymentMethod, onPaymentSelection)
        }
    }
}

@Composable
fun PaymentMethodComposable(
    newSelectedPaymentMethod: PaymentMethod,
    lastSelectedPaymentMethod: PaymentMethod?,
    onPaymentSelection: (PaymentMethod?) -> Unit
) {
    val modifier = Modifier
        .clickable {
            onPaymentSelection(newSelectedPaymentMethod)
        }
        .padding(dimensionResource(id = R.dimen.default_spacing))

    ApplicationCard {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val selectionState =
                lastSelectedPaymentMethod != null && lastSelectedPaymentMethod.name == newSelectedPaymentMethod.name

            Image(painter = painterResource(id = newSelectedPaymentMethod.icon), contentDescription = null)
            Text(text = stringResource(id = newSelectedPaymentMethod.name))
            RadioButton(selected = selectionState, enabled = true, onClick = {
                val item = newSelectedPaymentMethod.copy(isSelected = !newSelectedPaymentMethod.isSelected)
                onPaymentSelection(item)
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
            top = dimensionResource(id = R.dimen.default_spacing),
            bottom = dimensionResource(id = R.dimen.default_spacing)
        )
    Button(
        modifier = buttonModifier,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.primary_button_corners_size))),
        onClick = {
            sharedViewModel.paymentMethod = paymentMethod
            navController.navigate(paymentConfirmationBottomSheetRoute){
                this.popUpTo(paymentSelectionBottomSheetRoute) {
                    inclusive = true
                }
            }
        },
    ) {
        Text(stringResource(R.string.button_continue_label))
    }
}
