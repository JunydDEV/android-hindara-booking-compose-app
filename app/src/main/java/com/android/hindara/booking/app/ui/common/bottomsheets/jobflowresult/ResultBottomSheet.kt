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
import com.android.hindara.booking.app.ui.booking.dateselection.calendarBottomSheetRoute
import com.android.hindara.booking.app.ui.booking.paymentconfirmation.paymentConfirmationBottomSheetRoute
import com.android.hindara.booking.app.ui.common.bottomsheets.states.TransactionResultState
import com.android.hindara.booking.app.ui.home.HomeViewModel
import com.android.hindara.booking.app.ui.hoteldetails.hotelDetailsRoute
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
        navController = navController,
        viewModel = viewModel,
        type = type
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
            ContinueButtonComposable(
                viewModel = viewModel,
                navController = navController,
                buttonText = it.buttonText,
                type = type
            )
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
    viewModel: ResultViewModel,
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
            onClickEvent(viewModel, type, navController)
        },
    ) {
        Text(stringResource(buttonText))
    }
}

private fun onClickEvent(
    viewModel: ResultViewModel,
    type: String,
    navController: NavController
) {
    val resultType = viewModel.getResultType(type)

    if (viewModel.isTransactionCompleted(resultType)) {
        navController.popBackStack(hotelDetailsRoute, inclusive = false)
    } else {
        navigateToNextScreen(type, resultType, navController)
    }
}

private fun navigateToNextScreen(
    type: String,
    resultType: String,
    navController: NavController
) {
    val currentDestination = resultBottomSheetRoute.replace("{type}", type)
    val nextDestination = resultBottomSheetRoute.replace("{type}", resultType)
    navController.navigate(nextDestination) {
        this.popUpTo(currentDestination) {
            inclusive = true
        }
    }
}