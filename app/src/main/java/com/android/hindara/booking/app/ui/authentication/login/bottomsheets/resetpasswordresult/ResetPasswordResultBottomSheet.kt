package com.android.hindara.booking.app.ui.authentication.login.bottomsheets.resetpasswordresult

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
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.LoginBottomSheetState
import com.android.hindara.booking.app.ui.theme.BottomSheetBackgroundColor
import com.android.hindara.booking.app.ui.theme.DarkTextColor

/**
 * Bottom sheet UI to show the reset password result.
 *
 * @param viewModel establishes communication between UI & data component.
 * @param sheetState state of the bottom sheet.
 * @param loginBottomSheetState holds the state of current bottom sheet.
 * @param result indicates success/failure of reset password.
 * @param function indicates the main screen content.
 * */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ResetPasswordResultBottomSheet(
    viewModel: ResetPasswordResultViewModel = hiltViewModel(),
    sheetState: ModalBottomSheetState,
    loginBottomSheetState: MutableState<LoginBottomSheetState>,
    result: LoginBottomSheetState,
    function: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetBackgroundColor = BottomSheetBackgroundColor,
        sheetShape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.bottomSheetCornerSize),
            topEnd = dimensionResource(id = R.dimen.bottomSheetCornerSize)
        ),
        sheetContent = { ResetPasswordSuccessBottomSheetContent(loginBottomSheetState, viewModel,result) },
    ) { function() }
}

@Composable
fun ResetPasswordSuccessBottomSheetContent(
    loginBottomSheetState: MutableState<LoginBottomSheetState>,
    viewModel: ResetPasswordResultViewModel,
    result: LoginBottomSheetState
) {
    val parentColumnModifier = Modifier
        .padding(dimensionResource(id = R.dimen.defaultSpacing))
        .verticalScroll(rememberScrollState())
        .fillMaxWidth()
    Column(
        modifier = parentColumnModifier
    ) {

        SuccessImageComposable(viewModel.getResultIcon(result))
        SpacerComposable()
        ResetPasswordSuccessTitle(viewModel.getTitle(result))
        ResetPasswordSuccessDescription(viewModel.getDescription(result))
        ContinueButtonComposable(loginBottomSheetState, viewModel.getButtonText(result), result)
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
        val icon  =
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
    loginBottomSheetState: MutableState<LoginBottomSheetState>,
    buttonText: Int,
    result: LoginBottomSheetState
) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.defaultSpacing))
    Button(
        modifier = buttonModifier,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.buttonCornersSize))),
        onClick = {
            if (result == LoginBottomSheetState.ResetPasswordFailure) {
                loginBottomSheetState.value = LoginBottomSheetState.ResetPasswordSuccess
            }
        },
    ) {
        Text(stringResource(buttonText))
    }
}