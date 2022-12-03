package com.android.hindara.booking.app.ui.common.bottomsheets.jobflowresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.bottomsheets.states.TransactionResultState
import com.android.hindara.booking.app.ui.theme.DarkTextColor

/**
 * Bottom sheet UI to show the job flow result.
 *
 * @param viewModel provides data to UI from outside.
 * */
@Composable
fun ResultBottomSheet(
    navController: NavController,
    viewModel: ResultViewModel = hiltViewModel(),
    type: String,
    ) {
    JobFlowResultBottomSheetContent(
        navController,
        viewModel,
        type
    )
}

@Composable
fun JobFlowResultBottomSheetContent(
    navController: NavController,
    viewModel: ResultViewModel,
    type: String
) {
    val parentColumnModifier = Modifier
        .padding(dimensionResource(id = R.dimen.defaultSpacing))
        .verticalScroll(rememberScrollState())
        .fillMaxWidth()
    Column(
        modifier = parentColumnModifier
    ) {
        val bottomSheetContent = viewModel.getSheetContent(type)
        bottomSheetContent?.let {
            SuccessImageComposable(it.icon)
            SpacerComposable()
            ResetPasswordSuccessTitle(it.title)
            ResetPasswordSuccessDescription(it.description)
            ContinueButtonComposable(navController, it.buttonText, type)
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
    navController: NavController,
    buttonText: Int,
    type: String
) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.defaultSpacing))
    Button(
        modifier = buttonModifier,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.buttonCornersSize))),
        onClick = {
            val resultType = when(type) {
                TransactionResultState.PaymentResultSuccess.javaClass.name -> {
                    TransactionResultState.BookingCompleted.javaClass.name
                }
                TransactionResultState.PaymentResultFailure.javaClass.name -> {
                    TransactionResultState.PaymentResultSuccess.javaClass.name
                }
                TransactionResultState.ResetPasswordSuccess.javaClass.name -> {
                    TransactionResultState.ResetPasswordCompleted.javaClass.name
                }
                TransactionResultState.ResetPasswordFailure.javaClass.name -> {
                    TransactionResultState.ResetPasswordSuccess.javaClass.name
                }
                else -> {
                    TransactionResultState.ResetPasswordSuccess.javaClass.name
                }
            }
            navController.navigate(resultBottomSheetRoute.replace("{type}", resultType))
        },
    ) {
        Text(stringResource(buttonText))
    }
}