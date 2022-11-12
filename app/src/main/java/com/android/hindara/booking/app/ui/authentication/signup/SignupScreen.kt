package com.android.hindara.booking.app.ui.authentication.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.*

@Composable
fun SignupScreen(
    viewModel: SignupViewModel = hiltViewModel(),
    navController: NavController,
) {
    val parentColumnModifier = Modifier
        .fillMaxSize()
        .background(ScreenBackgroundColor)
        .verticalScroll(rememberScrollState())
    Column(
        modifier = parentColumnModifier
    ) {
        SpacerComposable()
        UsernameTextFieldLabelComposable()
        UsernameFieldComposable()

        SpacerComposable()
        EmailTextFieldLabelComposable()
        EmailTextFieldComposable()

        SpacerComposable()
        PasswordTextFieldLabelComposable()
        PasswordTextFieldComposable()

        SpacerComposable()
        SignupButtonComposable()
    }
}

@Composable
private fun SpacerComposable() {
    val height = dimensionResource(id = R.dimen.defaultSpacing)
    Spacer(
        Modifier
            .fillMaxWidth()
            .height(height)
    )
}

@Composable
private fun UsernameTextFieldLabelComposable() {
    val emailLabelModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.defaultSpacing),
            start = dimensionResource(id = R.dimen.largeSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )
    Text(
        modifier = emailLabelModifier,
        text = stringResource(R.string.textfield_username_label),
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

    val emailTextFieldModifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.defaultSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )

    TextField(
        modifier = emailTextFieldModifier,
        value = textFieldEmailState.value,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.fieldCornersSize))),
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
    text = stringResource(R.string.textfield_username_placeholder),
    style = MaterialTheme.typography.body1,
    color = FieldPlaceholderColor
)

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

    val emailTextFieldModifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.defaultSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )

    TextField(
        modifier = emailTextFieldModifier,
        value = textFieldEmailState.value,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.fieldCornersSize))),
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
            top = dimensionResource(id = R.dimen.defaultSpacing),
            start = dimensionResource(id = R.dimen.largeSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )
    Text(
        modifier = passwordLabelModifier,
        text = stringResource(R.string.textField_password_label),
        style = MaterialTheme.typography.body1,
        color = DarkTextColor
    )
}

@Composable
fun PasswordTextFieldComposable() {
    val focus = LocalFocusManager.current
    val textFieldPasswordState = remember { mutableStateOf("") }
    val showPasswordState = remember { mutableStateOf(false) }

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
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.fieldCornersSize))),
        colors = getTextFieldColors(),
        singleLine = true,
        textStyle = MaterialTheme.typography.body1,
        keyboardOptions = getPasswordKeyboardOptions(),
        keyboardActions = onKeyboardAction(focus),
        placeholder = { PasswordPlaceholderContent(MaterialTheme.typography) },
        visualTransformation = getPasswordTransformation(showPasswordState),
        trailingIcon = {
            val (icon, iconColor) = getVisibilitySelectionPair(showPasswordState)
            PasswordVisibilityTrailingIcon(showPasswordState, icon, iconColor)
        }
    )
}

@Composable
private fun PasswordPlaceholderContent(typography: Typography) {
    Text(
        text = stringResource(R.string.textField_password_placeholder),
        style = typography.body1,
        color = FieldPlaceholderColor
    )
}

@Composable
private fun SignupButtonComposable() {
    val loginButtonModifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.defaultSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
            bottom = dimensionResource(id = R.dimen.defaultSpacing)
        )
    Button(
        modifier = loginButtonModifier,
        shape = RoundedCornerShape(CornerSize(100.dp)),
        onClick = { /*TODO*/ },
    ) {
        Text(stringResource(R.string.button_signup_text))
    }
}

@Composable
private fun getTextFieldColors() = TextFieldDefaults.textFieldColors(
    backgroundColor = FieldBackgroundColor,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent
)

@Composable
private fun onKeyboardAction(focus: FocusManager) = KeyboardActions(
    onNext = { focus.moveFocus(FocusDirection.Down) },
    onDone = { focus.clearFocus() }
)

@Composable
private fun getKeyboardOptions(imeAction: ImeAction, keyboardType: KeyboardType) = KeyboardOptions(
    imeAction = imeAction,
    keyboardType = keyboardType
)

@Composable
private fun getPasswordKeyboardOptions() = KeyboardOptions(
    imeAction = ImeAction.Done,
    keyboardType = KeyboardType.Password
)

@Composable
private fun getVisibilitySelectionPair(showPasswordState: MutableState<Boolean>) =
    if (showPasswordState.value) {
        Pair(Icons.Filled.Visibility, PrimaryColor)
    } else {
        Pair(Icons.Filled.VisibilityOff, DarkTextColor)
    }

@Composable
private fun getPasswordTransformation(showPasswordState: MutableState<Boolean>) =
    if (showPasswordState.value) VisualTransformation.None else PasswordVisualTransformation()

@Composable
private fun PasswordVisibilityTrailingIcon(
    showPasswordState: MutableState<Boolean>,
    icon: ImageVector,
    iconColor: Color
) {
    IconButton(onClick = { showPasswordState.value = !showPasswordState.value }) {
        Icon(
            imageVector = icon,
            tint = iconColor,
            contentDescription = stringResource(R.string.description_password_visibility),
        )
    }
}
