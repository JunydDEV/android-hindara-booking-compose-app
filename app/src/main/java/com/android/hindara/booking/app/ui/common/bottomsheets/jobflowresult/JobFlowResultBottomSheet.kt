package com.android.hindara.booking.app.ui.common.bottomsheets.jobflowresult

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.bottomsheets.states.BottomSheetState
import com.android.hindara.booking.app.ui.common.bottomsheets.composables.HindaraBottomSheet
import com.android.hindara.booking.app.ui.common.bottomsheets.states.TransactionResultState
import com.android.hindara.booking.app.ui.theme.DarkTextColor

/**
 * Bottom sheet UI to show the job flow result.
 *
 * @param viewModel provides data to UI from outside.
 * @param modelBottomSheetState state of the bottom sheet.
 * @param bottomSheetState holds the state of current bottom sheet.
 * @param resultState indicates success/failure of job flow result.
 * @param mainScreenContent indicates the main screen content.
 * */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun JobFlowResultBottomSheet(
    viewModel: JobFlowResultViewModel = hiltViewModel(),
    modelBottomSheetState: ModalBottomSheetState,
    bottomSheetState: MutableState<BottomSheetState>,
    resultState: TransactionResultState,
    mainScreenContent: @Composable () -> Unit
) {
    HindaraBottomSheet(
        sheetState = modelBottomSheetState,
        sheetContent = {
            JobFlowResultBottomSheetContent(
                bottomSheetState,
                viewModel,
                resultState
            )
        },
        content = { mainScreenContent() }
    )
}

@Composable
fun JobFlowResultBottomSheetContent(
    bottomSheetState: MutableState<BottomSheetState>,
    viewModel: JobFlowResultViewModel,
    result: TransactionResultState
) {
    val parentColumnModifier = Modifier
        .padding(dimensionResource(id = R.dimen.defaultSpacing))
        .verticalScroll(rememberScrollState())
        .fillMaxWidth()
    Column(
        modifier = parentColumnModifier
    ) {
        val jobFlowResult = viewModel.getResult(bottomSheetState.value as TransactionResultState)
        jobFlowResult?.let {
            SuccessImageComposable(it.icon)
            SpacerComposable()
            ResetPasswordSuccessTitle(it.title)
            ResetPasswordSuccessDescription(it.description)
            ContinueButtonComposable(bottomSheetState, it.buttonText, result)
        }
    }
}

@Composable
private fun SuccessImageComposable(resultIcon: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.largeSpacing)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = resultIcon),
            contentDescription = stringResource(
                R.string.reset_password_success_image_description
            )
        )
    }
}

@Composable
private fun SpacerComposable() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.largeSpacing))
    )
}

@Composable
private fun ResetPasswordSuccessTitle(title: Int) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(title),
        style = MaterialTheme.typography.h1,
        color = DarkTextColor
    )
}

@Composable
private fun ResetPasswordSuccessDescription(description: Int) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(description),
        style = MaterialTheme.typography.body2,
        color = DarkTextColor
    )
}

@Composable
private fun ContinueButtonComposable(
    loginBottomSheetState: MutableState<BottomSheetState>,
    buttonText: Int,
    result: TransactionResultState
) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.defaultSpacing))
    Button(
        modifier = buttonModifier,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.buttonCornersSize))),
        onClick = {
            when(result) {
                TransactionResultState.PaymentResultSuccess -> {
                    loginBottomSheetState.value = TransactionResultState.BookingCompleted
                }
                TransactionResultState.PaymentResultFailure -> {
                    loginBottomSheetState.value = TransactionResultState.PaymentResultSuccess
                }
                TransactionResultState.ResetPasswordSuccess -> {
                    loginBottomSheetState.value = TransactionResultState.ResetPasswordCompleted
                }
                TransactionResultState.ResetPasswordFailure -> {
                    loginBottomSheetState.value = TransactionResultState.ResetPasswordSuccess
                }
                else -> {
                    loginBottomSheetState.value = TransactionResultState.ResetPasswordSuccess
                }
            }
        },
    ) {
        Text(stringResource(buttonText))
    }
}