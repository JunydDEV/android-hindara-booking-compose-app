package com.android.hindara.booking.app.ui.authentication.login.bottomsheets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
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
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.field_placeholder_color
import com.android.hindara.booking.app.ui.theme.primary_color

@Composable
fun PasswordTextFieldComposable(placeholder: String = stringResource(id = R.string.textField_password_placeholder)) {
    val focus = LocalFocusManager.current
    val textFieldPasswordState = remember { mutableStateOf("") }
    val showPasswordState = remember { mutableStateOf(false) }

    val passwordTextFieldModifier = Modifier.fillMaxWidth()

    TextField(
        modifier = passwordTextFieldModifier,
        value = textFieldPasswordState.value,
        onValueChange = { textFieldPasswordState.value = it },
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.textField_corners_size))),
        colors = getTextFieldColors(),
        singleLine = true,
        textStyle = typography.body1,
        keyboardOptions = getPasswordKeyboardOptions(),
        keyboardActions = onKeyboardAction(focus),
        placeholder = { PasswordPlaceholderContent(placeholder,typography) },
        visualTransformation = getPasswordTransformation(showPasswordState.value),
        trailingIcon = {
            val (icon, iconColor) = getVisibilitySelectionPair(showPasswordState.value)
            PasswordVisibilityTrailingIcon(
                showPassword = showPasswordState.value,
                onShowPasswordChange = { showPasswordState.value = it },
                icon = icon,
                iconColor = iconColor
            )
        }
    )
}

@Composable
fun getTextFieldColors() = TextFieldDefaults.textFieldColors(
    backgroundColor = MaterialTheme.colors.surface,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent
)

@Composable
private fun getPasswordKeyboardOptions() = KeyboardOptions(
    imeAction = ImeAction.Done,
    keyboardType = KeyboardType.Password
)

@Composable
private fun getPasswordTransformation(passwordValue: Boolean) =
    if (passwordValue) VisualTransformation.None else PasswordVisualTransformation()

@Composable
private fun PasswordVisibilityTrailingIcon(
    showPassword: Boolean,
    onShowPasswordChange: (Boolean) -> Unit,
    icon: ImageVector,
    iconColor: Color
) {
    IconButton(onClick = { onShowPasswordChange(!showPassword) }) {
        Icon(
            imageVector = icon,
            tint = iconColor,
            contentDescription = stringResource(R.string.image_password_visibility),
        )
    }
}

@Composable
private fun getVisibilitySelectionPair(showPassword: Boolean) =
    if (showPassword) {
        Pair(Icons.Filled.Visibility, primary_color)
    } else {
        Pair(Icons.Filled.VisibilityOff, MaterialTheme.colors.onSurface)
    }

@Composable
private fun PasswordPlaceholderContent(placeholder: String, typography: Typography) {
    Text(
        text = placeholder,
        style = typography.body1,
        color = field_placeholder_color
    )
}

@Composable
fun onKeyboardAction(focus: FocusManager) = KeyboardActions(
    onNext = { focus.moveFocus(FocusDirection.Down) },
    onDone = { focus.clearFocus() }
)

