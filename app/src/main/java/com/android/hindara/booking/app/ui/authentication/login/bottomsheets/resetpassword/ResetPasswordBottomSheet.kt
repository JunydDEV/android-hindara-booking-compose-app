package com.android.hindara.booking.app.ui.authentication.login.bottomsheets.resetpassword

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.authentication.authenticationRoute
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.forgotpassword.forgotPasswordBottomSheetRoute
import com.android.hindara.booking.app.ui.common.bottomsheets.composables.BottomSheetContentWithTitle
import com.android.hindara.booking.app.ui.common.bottomsheets.jobflowresult.alertBottomSheetRoute
import com.android.hindara.booking.app.ui.common.bottomsheets.states.AlertType
import com.android.hindara.booking.app.ui.theme.*


@Composable
fun ResetPasswordBottomSheet(
    viewModel: ResetPasswordViewModel = hiltViewModel(),
    navController: NavController
) {
    ResetPasswordBottomSheetContent(navController)
}

@Composable
fun ResetPasswordBottomSheetContent(navController: NavController) {
    BottomSheetContentWithTitle(stringResource(R.string.label_reset_password)) {
        ResetPasswordDescriptionComposable()
        NewPasswordTextFieldLabelComposable()
        NewPasswordTextFieldComposable()
        ConfirmPasswordTextFieldLabelComposable()
        ConfirmPasswordTextFieldComposable()
        ResetButtonComposable(navController)
    }
}

@Composable
private fun ResetPasswordDescriptionComposable() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.label_reset_password_description),
        style = MaterialTheme.typography.body1,
        color = DarkTextColor
    )
}

@Composable
private fun NewPasswordTextFieldLabelComposable() {
    val passwordLabelModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.default_spacing),
            start = dimensionResource(id = R.dimen.small_spacing)
        )
    Text(
        modifier = passwordLabelModifier,
        text = stringResource(id = R.string.textField_password_label),
        style = MaterialTheme.typography.body1,
        color = DarkTextColor
    )
}


@Composable
fun NewPasswordTextFieldComposable() {
    val focus = LocalFocusManager.current
    val textFieldPasswordState = remember {
        mutableStateOf("")
    }
    val showPasswordState = remember { mutableStateOf(false) }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textFieldPasswordState.value,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.textField_corners_size)),
        singleLine = true,
        textStyle = MaterialTheme.typography.body1,
        onValueChange = { textFieldPasswordState.value = it },
        keyboardActions = onKeyboardAction(
            focus = focus,
            focusDirection = FocusDirection.Next
        ),
        keyboardOptions = getPasswordKeyboardOptions(ImeAction.Next),
        colors = getTextFieldColors(),
        visualTransformation = getPasswordTransformation(showPasswordState),
        trailingIcon = {
            val (icon, iconColor) = getVisibilitySelectionPair(showPasswordState)
            PasswordVisibilityTrailingIcon(showPasswordState, icon, iconColor)
        },
        placeholder = {
            PasswordPlaceholderContent(
                typography =  MaterialTheme.typography,
                placeholderId = R.string.textField_password_placeholder
            )
        },
    )
}

@Composable
private fun ConfirmPasswordTextFieldLabelComposable() {
    val passwordLabelModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.default_spacing),
            start = dimensionResource(id = R.dimen.small_spacing)
        )
    Text(
        modifier = passwordLabelModifier,
        text = stringResource(R.string.textField_confirm_password_label),
        style = MaterialTheme.typography.body1,
        color = DarkTextColor
    )
}


@Composable
fun ConfirmPasswordTextFieldComposable() {
    val focus = LocalFocusManager.current
    val textFieldPasswordState = remember {
        mutableStateOf("")
    }
    val showPasswordState = remember { mutableStateOf(false) }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textFieldPasswordState.value,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.textField_corners_size)),
        singleLine = true,
        textStyle = MaterialTheme.typography.body1,
        onValueChange = { textFieldPasswordState.value = it },
        keyboardActions = onKeyboardAction(
            focus = focus,
            focusDirection = FocusDirection.Down
        ),
        keyboardOptions = getPasswordKeyboardOptions(ImeAction.Done),
        colors = getTextFieldColors(),
        visualTransformation = getPasswordTransformation(showPasswordState),
        trailingIcon = {
            val (icon, iconColor) = getVisibilitySelectionPair(showPasswordState)
            PasswordVisibilityTrailingIcon(showPasswordState, icon, iconColor)
        },
        placeholder = {
            PasswordPlaceholderContent(
                typography =  MaterialTheme.typography,
                placeholderId = R.string.textField_confirm_password_placeholder
            )
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
private fun onKeyboardAction(
    focus: FocusManager,
    focusDirection: FocusDirection
) =
    KeyboardActions(
        onNext = { focus.moveFocus(focusDirection) },
        onDone = {
            focus.clearFocus()
        }
    )

@Composable
private fun PasswordPlaceholderContent(typography: Typography, placeholderId: Int) {
    Text(
        text = stringResource(placeholderId),
        style = typography.body1,
        color = FieldPlaceholderColor
    )
}

@Composable
private fun getPasswordKeyboardOptions(imeOptions: ImeAction) = KeyboardOptions(
    imeAction = imeOptions,
    keyboardType = KeyboardType.Password
)

@Composable
private fun ResetButtonComposable(navController: NavController) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.default_spacing))
    Button(
        modifier = buttonModifier,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.primary_button_corners_size))),
        onClick = {
            navController.navigate(
                alertBottomSheetRoute.replace("{type}", AlertType.resetPasswordFailure)
            ) {
                popUpTo(authenticationRoute)
            }
        },
    ) {
        Text(stringResource(R.string.button_reset_password_label))
    }
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
            contentDescription = stringResource(R.string.image_password_visibility),
        )
    }
}

@Composable
private fun getVisibilitySelectionPair(showPasswordState: MutableState<Boolean>) =
    if (showPasswordState.value) {
        Pair(Icons.Filled.Visibility, PrimaryColor)
    } else {
        Pair(Icons.Filled.VisibilityOff, DarkTextColor)
    }

