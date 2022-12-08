package com.android.hindara.booking.app.ui.authentication.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.authentication.authenticationRoute
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.PasswordTextFieldComposable
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.getTextFieldColors
import com.android.hindara.booking.app.ui.home.homeRoute
import com.android.hindara.booking.app.ui.theme.DarkTextColor
import com.android.hindara.booking.app.ui.theme.FieldPlaceholderColor
import com.android.hindara.booking.app.ui.theme.ScreenBackgroundColor

@Composable
fun SignupScreen(
    viewModel: SignupViewModel = hiltViewModel(),
    navController: NavController,
) {
    val parentColumnModifier = Modifier
        .fillMaxSize()
        .background(ScreenBackgroundColor)
        .padding(dimensionResource(id = R.dimen.default_spacing))
        .verticalScroll(rememberScrollState())
    Column(
        modifier = parentColumnModifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.large_spacing))
    ) {
        UserNameComposable()
        EmailComposable()
        PasswordComposable()
        SignupButtonComposable(navController)
    }
}

@Composable
private fun UserNameComposable() {
    Column(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.default_spacing))) {
        UsernameTextFieldLabelComposable()
        UsernameFieldComposable()
    }
}

@Composable
private fun EmailComposable() {
    Column {
        EmailTextFieldLabelComposable()
        EmailTextFieldComposable()
    }
}


@Composable
private fun PasswordComposable() {
    Column {
        PasswordTextFieldLabelComposable()
        PasswordTextFieldComposable()
    }
}

@Composable
private fun UsernameTextFieldLabelComposable() {
    val emailLabelModifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.small_spacing),
            end = dimensionResource(id = R.dimen.small_spacing),
        )
    Text(
        modifier = emailLabelModifier,
        text = stringResource(R.string.textField_username_label),
        style = MaterialTheme.typography.body1,
        color = DarkTextColor
    )
}

@Composable
fun UsernameFieldComposable() {
    val focus = LocalFocusManager.current
    val textFieldEmailState = remember {
        mutableStateOf("")
    }

    val emailTextFieldModifier = Modifier.fillMaxWidth()

    TextField(
        modifier = emailTextFieldModifier,
        value = textFieldEmailState.value,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.textField_corners_size))),
        singleLine = true,
        textStyle = MaterialTheme.typography.body1,
        onValueChange = { textFieldEmailState.value = it },
        keyboardActions = onKeyboardAction(focus),
        keyboardOptions = getKeyboardOptions(ImeAction.Next, KeyboardType.Text),
        colors = getTextFieldColors(),
        placeholder = {
            UsernamePlaceholderContent()
        }
    )
}

@Composable
private fun UsernamePlaceholderContent() = Text(
    text = stringResource(R.string.textField_username_placeholder),
    style = MaterialTheme.typography.body1,
    color = FieldPlaceholderColor
)

@Composable
private fun EmailTextFieldLabelComposable() {
    val emailLabelModifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.small_spacing),
        )
    Text(
        modifier = emailLabelModifier,
        text = stringResource(R.string.textField_email_label),
        style = MaterialTheme.typography.body1,
        color = DarkTextColor
    )
}

@Composable
fun EmailTextFieldComposable() {
    val focus = LocalFocusManager.current
    val textFieldEmailState = remember {
        mutableStateOf("")
    }

    val emailTextFieldModifier = Modifier.fillMaxWidth()

    TextField(
        modifier = emailTextFieldModifier,
        value = textFieldEmailState.value,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.textField_corners_size))),
        singleLine = true,
        textStyle = MaterialTheme.typography.body1,
        onValueChange = { textFieldEmailState.value = it },
        keyboardActions = onKeyboardAction(focus),
        keyboardOptions = getKeyboardOptions(ImeAction.Next, KeyboardType.Email),
        colors = getTextFieldColors(),
        placeholder = {
            EmailPlaceholderContent()
        }
    )
}

@Composable
private fun EmailPlaceholderContent() = Text(
    text = stringResource(R.string.textField_email_placeholder),
    style = MaterialTheme.typography.body1,
    color = FieldPlaceholderColor
)

@Composable
private fun PasswordTextFieldLabelComposable() {
    val passwordLabelModifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.small_spacing),
        )
    Text(
        modifier = passwordLabelModifier,
        text = stringResource(R.string.textField_password_label),
        style = MaterialTheme.typography.body1,
        color = DarkTextColor
    )
}

@Composable
private fun SignupButtonComposable(navController: NavController) {
    val loginButtonModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.default_spacing),
            bottom = dimensionResource(id = R.dimen.default_spacing)
        )
    Button(
        modifier = loginButtonModifier,
        shape = RoundedCornerShape(CornerSize(100.dp)),
        onClick = {
            navigationToHomeScreen(navController)
        },
    ) {
        Text(stringResource(R.string.button_signup_label))
    }
}

@Composable
private fun getKeyboardOptions(imeAction: ImeAction, keyboardType: KeyboardType) = KeyboardOptions(
    imeAction = imeAction,
    keyboardType = keyboardType
)

@Composable
private fun onKeyboardAction(focus: FocusManager) = KeyboardActions(
    onNext = { focus.moveFocus(FocusDirection.Down) },
    onDone = { focus.clearFocus() }
)

private fun navigationToHomeScreen(navController: NavController) {
    navController.navigate(homeRoute) {
        popUpTo(authenticationRoute) {
            inclusive = true
        }
    }
}