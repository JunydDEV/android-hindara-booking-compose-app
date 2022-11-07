package com.android.hindara.booking.app.ui.authentication.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.DarkTextColor
import com.android.hindara.booking.app.ui.theme.WhiteColor

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SpacerComposable()
        EmailTextFieldLabelComposable()
        EmailTextFieldComposable()

        SpacerComposable()
        PasswordTextFieldLabelComposable()
        PasswordTextFieldComposable()

        ForgotPasswordTextComposable()
        SpacerComposable()
        LoginButtonComposable()
    }
}

@Composable
private fun EmailTextFieldLabelComposable() {
    val emailLabelModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.defaultSpacing),
            start = dimensionResource(id = R.dimen.largeSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )
    Text(
        modifier = emailLabelModifier,
        text = stringResource(R.string.textfield_email_label),
        style = typography.body1,
        color = DarkTextColor
    )
}

@Composable
fun EmailTextFieldComposable() {
    val focus = LocalFocusManager.current
    val textFieldEmailState = remember {
        mutableStateOf("")
    }

    val emailTextFieldModifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.defaultSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )

    TextField(
        modifier = emailTextFieldModifier,
        value = textFieldEmailState.value,
        shape = RoundedCornerShape(CornerSize(50.dp)),
        singleLine = true,
        textStyle = typography.body1,
        onValueChange = { textFieldEmailState.value = it },
        keyboardActions = onKeyboardAction(focus),
        keyboardOptions = getEmailKeyboardOptions(),
        colors = getTextFieldColors(),
        placeholder = {
            EmailPlaceholderContent(typography)
        }
    )
}

@Composable
private fun PasswordTextFieldLabelComposable() {
    val passwordLabelModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.defaultSpacing),
            start = dimensionResource(id = R.dimen.largeSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )
    Text(
        modifier = passwordLabelModifier,
        text = stringResource(R.string.textfield_password_label),
        style = typography.body1,
        color = DarkTextColor
    )
}

@Composable
fun PasswordTextFieldComposable() {
    val focus = LocalFocusManager.current
    val textFieldPasswordState = remember { mutableStateOf("") }

    val passwordTextFieldModifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.defaultSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )

    TextField(
        modifier = passwordTextFieldModifier,
        value = textFieldPasswordState.value,
        onValueChange = { textFieldPasswordState.value = it },
        shape = RoundedCornerShape(CornerSize(50.dp)),
        colors = getTextFieldColors(),
        singleLine = true,
        textStyle = typography.body1,
        keyboardOptions = getPasswordKeyboardOptions(),
        keyboardActions = onKeyboardAction(focus),
        placeholder = { PasswordPlaceholderContent(typography) },
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun ForgotPasswordTextComposable() {
    val forgotPasswordTextModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.defaultSpacing),
            start = dimensionResource(id = R.dimen.largeSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )
    Text(
        modifier = forgotPasswordTextModifier,
        text = stringResource(R.string.text_forgot_password_label),
        style = typography.body1,
        color = DarkTextColor,
        textAlign = TextAlign.End
    )
}


@Composable
private fun LoginButtonComposable() {
    val loginButtonModifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.defaultSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )
    Button(
        modifier = loginButtonModifier,
        shape = RoundedCornerShape(CornerSize(100.dp)),
        onClick = { /*TODO*/ },
    ) {
        Text(text = stringResource(R.string.button_login_text))
    }
}

@Composable
private fun SpacerComposable() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.smallSpacing))
    )
}

@Composable
private fun EmailPlaceholderContent(typography: Typography) {
    Text(
        text = stringResource(R.string.textfield_email_placeholder),
        style = typography.body1,
        color = Color.Gray
    )
}

@Composable
private fun getEmailKeyboardOptions() = KeyboardOptions(
    imeAction = ImeAction.Next,
    keyboardType = KeyboardType.Email
)

@Composable
private fun PasswordPlaceholderContent(typography: Typography) {
    Text(
        text = stringResource(R.string.textfield_password_placeholder),
        style = typography.body1,
        color = Color.Gray
    )
}

@Composable
private fun getTextFieldColors() = TextFieldDefaults.textFieldColors(
    backgroundColor = WhiteColor,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent
)

@Composable
private fun onKeyboardAction(focus: FocusManager) = KeyboardActions(
    onNext = { focus.moveFocus(FocusDirection.Down) }
)

@Composable
private fun getPasswordKeyboardOptions() = KeyboardOptions(
    imeAction = ImeAction.Next,
    keyboardType = KeyboardType.Password
)

