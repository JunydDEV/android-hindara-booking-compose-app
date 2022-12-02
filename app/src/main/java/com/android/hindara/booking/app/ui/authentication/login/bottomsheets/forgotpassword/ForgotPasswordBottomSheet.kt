package com.android.hindara.booking.app.ui.authentication.login.bottomsheets.forgotpassword

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.bottomsheets.states.BottomSheetState
import com.android.hindara.booking.app.ui.common.bottomsheets.states.AuthenticationBottomSheetState
import com.android.hindara.booking.app.ui.BottomSheetContentWithTitle
import com.android.hindara.booking.app.ui.HindaraBottomSheet
import com.android.hindara.booking.app.ui.theme.*

/**
 * Bottom sheet to get the email from user for resetting password.
 *
 * @param viewModel provides data to UI from outside.
 * @param sheetState state of the bottom sheet.
 * @param loginBottomSheetState holds the state of current bottom sheet.
 * @param mainScreenContent indicates the main screen content.
 * */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ForgotPasswordBottomSheet(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    sheetState: ModalBottomSheetState,
    loginBottomSheetState: MutableState<BottomSheetState>,
    mainScreenContent: @Composable () -> Unit
) {
    HindaraBottomSheet(
        sheetState = sheetState,
        sheetContent = { ForgotPasswordBottomSheetContent(loginBottomSheetState) },
        content = { mainScreenContent() }
    )
}

@Composable
fun ForgotPasswordBottomSheetContent(loginBottomSheetState: MutableState<BottomSheetState>) {
    BottomSheetContentWithTitle(stringResource(R.string.forgot_password_title)) {
        ForgotPasswordDescriptionComposable()
        ForgotPasswordEmailTextFieldLabelComposable()
        ForgotPasswordEmailTextFieldComposable()
        ContinueButtonComposable(loginBottomSheetState)
    }
}

@Composable
private fun ForgotPasswordDescriptionComposable() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.forgot_password_description),
        style = MaterialTheme.typography.body2,
        color = DarkTextColor
    )
}

@Composable
private fun ForgotPasswordEmailTextFieldLabelComposable() {
    val emailLabelModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.defaultSpacing),
            start = dimensionResource(id = R.dimen.smallSpacing)
        )
    Text(
        modifier = emailLabelModifier,
        text = stringResource(R.string.textField_email_label),
        style = MaterialTheme.typography.body1,
        color = DarkTextColor
    )
}


@Composable
fun ForgotPasswordEmailTextFieldComposable() {
    val focus = LocalFocusManager.current
    val textFieldEmailState = remember {
        mutableStateOf("")
    }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textFieldEmailState.value,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.fieldCornersSize)),
        singleLine = true,
        textStyle = MaterialTheme.typography.body1,
        onValueChange = { textFieldEmailState.value = it },
        keyboardActions = onKeyboardAction(focus),
        keyboardOptions = getEmailKeyboardOptions(),
        colors = getTextFieldColors(),
        placeholder = {
            EmailPlaceholderContent(MaterialTheme.typography)
        },
    )
}

@Composable
private fun getTextFieldColors() = TextFieldDefaults.textFieldColors(
    backgroundColor = FieldBackgroundColor,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent
)

@Composable
private fun onKeyboardAction(focus: FocusManager) =
    KeyboardActions(
        onNext = { focus.moveFocus(FocusDirection.Down) },
        onDone = {
            focus.clearFocus()
        }
    )

@Composable
private fun EmailPlaceholderContent(typography: Typography) {
    Text(
        text = stringResource(R.string.textField_email_placeholder),
        style = typography.body1,
        color = FieldPlaceholderColor
    )
}

@Composable
private fun getEmailKeyboardOptions() = KeyboardOptions(
    imeAction = ImeAction.Done,
    keyboardType = KeyboardType.Email
)

@Composable
private fun ContinueButtonComposable(loginBottomSheetState: MutableState<BottomSheetState>) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.defaultSpacing))
    Button(
        modifier = buttonModifier,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.buttonCornersSize))),
        onClick = {
            loginBottomSheetState.value = AuthenticationBottomSheetState.VerifyEmail
        },
    ) {
        Text(stringResource(R.string.button_continue_text))
    }
}
