package com.android.hindara.booking.app.ui.authentication.login.bottomsheets.emailverification

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.authentication.authenticationRoute
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.resetpassword.resetPasswordBottomSheetRoute
import com.android.hindara.booking.app.ui.common.bottomsheets.composables.BottomSheetContentWithTitle


@Composable
fun EmailVerificationBottomSheet(
    viewModel: EmailVerificationViewModel = hiltViewModel(),
    navController: NavController
) {
    EmailVerificationBottomSheetContent(navController)
}

@Composable
fun EmailVerificationBottomSheetContent(navController: NavController) {
    BottomSheetContentWithTitle(stringResource(R.string.label_enter_digits)) {
        VerifyEmailDescriptionComposable()
        DigitsRowComposable()
        ContinueButtonComposable(navController)
    }
}

@Composable
private fun VerifyEmailDescriptionComposable() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.label_enter_digits_description),
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun DigitsRowComposable() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        DigitsLazyRowComposable()
    }
}

@Composable
private fun DigitsLazyRowComposable() {
    val digitsRowModifier = Modifier.padding(dimensionResource(id = R.dimen.default_spacing))
    LazyRow(
        modifier = digitsRowModifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_spacing))
    ) {
        val itemsCount = 4
        items(itemsCount) {
            DigitFieldComposable(
                isFirsField = it == itemsCount - itemsCount,
                isLastField = it == itemsCount - 1
            )
        }
    }
}

@Composable
fun DigitFieldComposable(isLastField: Boolean, isFirsField: Boolean) {
    val focusManager = LocalFocusManager.current
    val fieldState = remember {
        mutableStateOf("")
    }

    val fieldModifier = Modifier
        .width(dimensionResource(id = R.dimen.digit_field_width))
        .height(dimensionResource(id = R.dimen.digit_field_height))

    OutlinedTextField(
        modifier = fieldModifier,
        value = fieldState.value,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.digit_field_corners_size)),
        singleLine = true,
        textStyle = TextStyle(textAlign = TextAlign.Center).plus(MaterialTheme.typography.h1),
        keyboardActions = onKeyboardAction(focusManager),
        keyboardOptions = getKeyboardOptions(isLastField),
        colors = getTextFieldColors(),
        onValueChange = {
            handleDigitsFieldFocus(
                digit = it,
                onDigitChange = { fieldState.value = it },
                isFirsField = isFirsField,
                isLastField = isLastField,
                focusManager = focusManager
            )
        },
    )
}

private fun handleDigitsFieldFocus(
    digit: String,
    onDigitChange: (String) -> Unit,
    isFirsField: Boolean,
    isLastField: Boolean,
    focusManager: FocusManager
) {
    if (digit.isEmpty()) {
        removeDigitAndMoveFocusBack(
            digit = digit,
            onDigitChange = onDigitChange,
            isFirsField =  isFirsField,
            focusManager = focusManager)
    } else if (digit.length == 1) {
        insertDigitAndMoveFocusNext(digit, onDigitChange, isLastField, focusManager)
    }
}

private fun insertDigitAndMoveFocusNext(
    digit: String,
    onDigitChange: (String) -> Unit,
    isLastField: Boolean,
    focusManager: FocusManager
) {
    onDigitChange(digit)

    if (isLastField) {
        focusManager.clearFocus()
    } else {
        focusManager.moveFocus(FocusDirection.Next)
    }
}

private fun removeDigitAndMoveFocusBack(
    digit: String,
    onDigitChange: (String) -> Unit,
    isFirsField: Boolean,
    focusManager: FocusManager
) {
    onDigitChange(digit)

    if (isFirsField) {
        focusManager.clearFocus()
    } else {
        focusManager.moveFocus(FocusDirection.Previous)
    }
}

@Composable
private fun onKeyboardAction(
    focusManager: FocusManager
) = KeyboardActions(
    onNext = { focusManager.moveFocus(FocusDirection.Next) },
    onDone = {
        focusManager.clearFocus()
    },
    onPrevious = {
        focusManager.moveFocus(FocusDirection.Previous)
    }
)

@Composable
private fun getKeyboardOptions(isLastField: Boolean) = KeyboardOptions(
    imeAction = if (isLastField) ImeAction.Done else ImeAction.Next,
    keyboardType = KeyboardType.Decimal
)

@Composable
private fun getTextFieldColors() = TextFieldDefaults.textFieldColors(
    backgroundColor = MaterialTheme.colors.surface,
)

@Composable
private fun ContinueButtonComposable(navController: NavController) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.small_spacing))
    Button(
        modifier = buttonModifier,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.primary_button_corners_size))),
        onClick = {
            navController.navigate(resetPasswordBottomSheetRoute) {
                popUpTo(authenticationRoute)
            }
        },
    ) {
        Text(stringResource(R.string.button_continue_label))
    }
}
