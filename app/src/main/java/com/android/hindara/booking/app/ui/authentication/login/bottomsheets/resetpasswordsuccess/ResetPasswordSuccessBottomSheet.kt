package com.android.hindara.booking.app.ui.authentication.login.bottomsheets.resetpasswordsuccess

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
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.LoginBottomSheet
import com.android.hindara.booking.app.ui.theme.BottomSheetBackgroundColor
import com.android.hindara.booking.app.ui.theme.DarkTextColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ResetPasswordSuccessBottomSheet(
    viewModel: ResetPasswordSuccessViewModel = hiltViewModel(),
    sheetState: ModalBottomSheetState,
    loginBottomSheetState: MutableState<LoginBottomSheet>,
    function: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetBackgroundColor = BottomSheetBackgroundColor,
        sheetShape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.bottomSheetCornerSize),
            topEnd = dimensionResource(id = R.dimen.bottomSheetCornerSize)
        ),
        sheetContent = { ResetPasswordSuccessBottomSheetContent(loginBottomSheetState) },
    ) { function() }
}

@Composable
fun ResetPasswordSuccessBottomSheetContent(loginBottomSheetState: MutableState<LoginBottomSheet>) {
    val parentColumnModifier = Modifier
        .padding(dimensionResource(id = R.dimen.defaultSpacing))
        .verticalScroll(rememberScrollState())
        .fillMaxWidth()
    Column(
        modifier = parentColumnModifier
    ) {

        SuccessImageComposable()
        SpacerComposable()
        ResetPasswordSuccessTitle()
        ResetPasswordSuccessDescription()
        ContinueButtonComposable(loginBottomSheetState)
    }
}

@Composable
private fun SuccessImageComposable() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.largeSpacing)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_success),
            contentDescription = stringResource(
                R.string.reset_password_sucess_image_description
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
private fun ResetPasswordSuccessTitle() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.reset_password_success_title),
        style = MaterialTheme.typography.h1,
        color = DarkTextColor
    )
}

@Composable
private fun ResetPasswordSuccessDescription() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.reset_password_success_description),
        style = MaterialTheme.typography.body2,
        color = DarkTextColor
    )
}

@Composable
private fun ContinueButtonComposable(loginBottomSheetState: MutableState<LoginBottomSheet>) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.defaultSpacing))
    Button(
        modifier = buttonModifier,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.buttonCornersSize))),
        onClick = {
            loginBottomSheetState.value = LoginBottomSheet.VerifyEmail
        },
    ) {
        Text(stringResource(R.string.button_login_now_text))
    }
}