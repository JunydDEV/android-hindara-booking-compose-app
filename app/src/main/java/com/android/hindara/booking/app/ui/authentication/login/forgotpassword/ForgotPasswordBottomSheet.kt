package com.android.hindara.booking.app.ui.authentication.login

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
import com.android.hindara.booking.app.ui.authentication.login.forgotpassword.ForgotPasswordViewModel
import com.android.hindara.booking.app.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ForgotPasswordBottomSheet(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    sheetState: ModalBottomSheetState,
    loginBottomSheetState: MutableState<LoginBottomSheet>,
    MainScreenContent: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetBackgroundColor = BottomSheetBackgroundColor,
        sheetShape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.bottomSheetCornerSize),
            topEnd = dimensionResource(id = R.dimen.bottomSheetCornerSize)
        ),
        sheetContent = { ForgotPasswordBottomSheetContent(loginBottomSheetState) },
    ) { MainScreenContent() }
}

@Composable
fun ForgotPasswordBottomSheetContent(loginBottomSheetState: MutableState<LoginBottomSheet>) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.defaultSpacing))
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.forgot_password_title),
            style = MaterialTheme.typography.h1,
            color = DarkTextColor
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.forgot_password_description),
            style = MaterialTheme.typography.body2,
            color = DarkTextColor
        )

        ForgotPasswordEmailTextFieldLabelComposable()
        ForgotPasswordEmailTextFieldComposable()
        ContinueButtonComposable(loginBottomSheetState)
    }
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
        keyboardActions = onKeyboardAction(focus, textFieldEmailState),
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
private fun onKeyboardAction(focus: FocusManager, fieldState: MutableState<String>) =
    KeyboardActions(
        onNext = { focus.moveFocus(FocusDirection.Down) },
        onDone = {
            focus.clearFocus()
            fieldState.value = ""
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
private fun ContinueButtonComposable(loginBottomSheetState: MutableState<LoginBottomSheet>) {
    val continueButtonModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.defaultSpacing))
    Button(
        modifier = continueButtonModifier,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.buttonCornersSize))),
        onClick = {
            loginBottomSheetState.value = LoginBottomSheet.VerifyEmail
        },
    ) {
        Text(stringResource(R.string.button_continue_text))
    }
}
