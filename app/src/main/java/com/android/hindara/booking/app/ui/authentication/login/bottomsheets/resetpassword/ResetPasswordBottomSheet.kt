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
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.bottomsheets.states.BottomSheetState
import com.android.hindara.booking.app.ui.BottomSheetContentWithTitle
import com.android.hindara.booking.app.ui.common.bottomsheets.composables.HindaraBottomSheet
import com.android.hindara.booking.app.ui.common.bottomsheets.states.TransactionResultState
import com.android.hindara.booking.app.ui.theme.*


/**
 * Bottom sheet to show the new passwords setting UI.
 *
 * @param viewModel provides data to UI from outside.
 * @param sheetState state of the bottom sheet.
 * @param loginBottomSheetState holds the state of current bottom sheet.
 * @param mainScreenContent indicates the main screen content.
 * */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ResetPasswordBottomSheet(
    viewModel: ResetPasswordViewModel = hiltViewModel(),
    sheetState: ModalBottomSheetState,
    loginBottomSheetState: MutableState<BottomSheetState>,
    mainScreenContent: @Composable () -> Unit
) {
    HindaraBottomSheet(
        sheetState = sheetState,
        sheetContent = { ResetPasswordBottomSheetContent(loginBottomSheetState) },
        content = { mainScreenContent() }
    )
}

@Composable
fun ResetPasswordBottomSheetContent(loginBottomSheetState: MutableState<BottomSheetState>) {
    BottomSheetContentWithTitle(stringResource(R.string.reset_password_title)) {
        ResetPasswordDescriptionComposable()
        NewPasswordTextFieldLabelComposable()
        NewPasswordTextFieldComposable()
        ConfirmPasswordTextFieldLabelComposable()
        ConfirmPasswordTextFieldComposable()
        ResetButtonComposable(loginBottomSheetState)
    }
}

@Composable
private fun ResetPasswordDescriptionComposable() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.reset_password_description),
        style = MaterialTheme.typography.body2,
        color = DarkTextColor
    )
}

@Composable
private fun NewPasswordTextFieldLabelComposable() {
    val passwordLabelModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.defaultSpacing),
            start = dimensionResource(id = R.dimen.smallSpacing)
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
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.fieldCornersSize)),
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
            top = dimensionResource(id = R.dimen.defaultSpacing),
            start = dimensionResource(id = R.dimen.smallSpacing)
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
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.fieldCornersSize)),
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
private fun ResetButtonComposable(loginBottomSheetState: MutableState<BottomSheetState>) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.defaultSpacing))
    Button(
        modifier = buttonModifier,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.buttonCornersSize))),
        onClick = {
            loginBottomSheetState.value = TransactionResultState.ResetPasswordFailure
        },
    ) {
        Text(stringResource(R.string.button_reset_password_text))
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
            contentDescription = stringResource(R.string.description_password_visibility),
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

