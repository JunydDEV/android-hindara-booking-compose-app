package com.android.hindara.booking.app.ui.authentication.login.bottomsheets.emailverification

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.LoginBottomSheet
import com.android.hindara.booking.app.ui.theme.BottomSheetBackgroundColor
import com.android.hindara.booking.app.ui.theme.DarkTextColor
import com.android.hindara.booking.app.ui.theme.FieldBackgroundColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmailVerificationBottomSheet(
    viewModel: EmailVerificationViewModel = hiltViewModel(),
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
        sheetContent = { EmailVerificationBottomSheetContent(loginBottomSheetState) },
    ) { function() }
}

@Composable
fun EmailVerificationBottomSheetContent(loginBottomSheetState: MutableState<LoginBottomSheet>) {
    val parentColumnModifier = Modifier
        .padding(dimensionResource(id = R.dimen.defaultSpacing))
        .verticalScroll(rememberScrollState())
        .fillMaxWidth()
    Column(
        modifier = parentColumnModifier
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.enter_digits_title),
            style = MaterialTheme.typography.h1,
            color = DarkTextColor
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.enter_digits_description),
            style = MaterialTheme.typography.body2,
            color = DarkTextColor
        )

        DigitsRowComposable()

        ContinueButtonComposable(loginBottomSheetState)
    }
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
    val digitsRowModifier = Modifier.padding(dimensionResource(id = R.dimen.defaultSpacing))
    LazyRow(
        modifier = digitsRowModifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.smallSpacing))
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
    val focus = LocalFocusManager.current
    val fieldState = remember {
        mutableStateOf("")
    }

    val fieldModifier = Modifier
        .width(dimensionResource(id = R.dimen.digitFieldWidth))
        .height(dimensionResource(id = R.dimen.digitFieldHeight))

    OutlinedTextField(
        modifier = fieldModifier,
        value = fieldState.value,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.digitFieldCornerSize)),
        singleLine = true,
        textStyle = TextStyle(textAlign = TextAlign.Center).plus(MaterialTheme.typography.h1),
        keyboardActions = onKeyboardAction(focus),
        keyboardOptions = getKeyboardOptions(isLastField),
        colors = getTextFieldColors(),
        onValueChange = {
            handleDigitsFieldFocus(
                text = it,
                fieldState = fieldState,
                isFirsField = isFirsField,
                isLastField = isLastField,
                focus = focus
            )
        },
    )
}

private fun handleDigitsFieldFocus(
    text: String,
    fieldState: MutableState<String>,
    isFirsField: Boolean,
    isLastField: Boolean,
    focus: FocusManager
) {
    if (text.isEmpty()) {
        removeDigitAndMoveFocusBack(fieldState, text, isFirsField, focus)
    } else if (text.length == 1) {
        insertDigitAndMoveFocusNext(fieldState, text, isLastField, focus)
    }
}

private fun insertDigitAndMoveFocusNext(
    fieldState: MutableState<String>,
    text: String,
    isLastField: Boolean,
    focus: FocusManager
) {
    fieldState.value = text
    if (isLastField) {
        focus.clearFocus()
    } else {
        focus.moveFocus(FocusDirection.Next)
    }
}

private fun removeDigitAndMoveFocusBack(
    fieldState: MutableState<String>,
    text: String,
    isFirsField: Boolean,
    focus: FocusManager
) {
    fieldState.value = text
    if (isFirsField) {
        focus.clearFocus()
    } else {
        focus.moveFocus(FocusDirection.Previous)
    }
}

@Composable
private fun onKeyboardAction(
    focus: FocusManager
) = KeyboardActions(
    onNext = { focus.moveFocus(FocusDirection.Next) },
    onDone = {
        focus.clearFocus()
    },
    onPrevious = {
        focus.moveFocus(FocusDirection.Previous)
    }
)

@Composable
private fun getKeyboardOptions(isLastField: Boolean) = KeyboardOptions(
    imeAction = if (isLastField) ImeAction.Done else ImeAction.Next,
    keyboardType = KeyboardType.Decimal
)

@Composable
private fun getTextFieldColors() = TextFieldDefaults.textFieldColors(
    backgroundColor = FieldBackgroundColor,
)

@Composable
private fun ContinueButtonComposable(loginBottomSheetState: MutableState<LoginBottomSheet>) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.smallSpacing))
    Button(
        modifier = buttonModifier,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.buttonCornersSize))),
        onClick = { loginBottomSheetState.value = LoginBottomSheet.ResetPassword },
    ) {
        Text(stringResource(R.string.button_continue_text))
    }
}
