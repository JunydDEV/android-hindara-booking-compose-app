package com.android.hindara.booking.app.ui.authentication.login.emailverification

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.BottomSheetBackgroundColor
import com.android.hindara.booking.app.ui.theme.DarkTextColor
import com.android.hindara.booking.app.ui.theme.FieldBackgroundColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmailVerificationBottomSheet(
    viewModel: EmailVerificationViewModel = hiltViewModel(),
    sheetState: ModalBottomSheetState,
    MainScreenContent: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetBackgroundColor = BottomSheetBackgroundColor,
        sheetShape = RoundedCornerShape(
            topStart = dimensionResource(id = R.dimen.bottomSheetCornerSize),
            topEnd = dimensionResource(id = R.dimen.bottomSheetCornerSize)
        ),
        sheetContent = { EmailVerificationBottomSheetContent() },
    ) { MainScreenContent() }
}

@Composable
fun EmailVerificationBottomSheetContent() {
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.defaultSpacing))
            .fillMaxWidth()
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

        ContinueButtonComposable()
    }
}

@Composable
fun DigitsRowComposable() {
    val digitsRowModifier = Modifier
        .fillMaxWidth()
        .padding(
            dimensionResource(id = R.dimen.defaultSpacing)
        )
    LazyRow(modifier = digitsRowModifier, horizontalArrangement = Arrangement.SpaceBetween) {
        items(4) {
            DigitFieldComposable(it == 3)
        }
    }
}

@Composable
fun DigitFieldComposable(isLastField: Boolean) {
    val focus = LocalFocusManager.current
    val fieldState = remember {
        mutableStateOf("")
    }

    TextField(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.digitFieldWidth))
            .height(dimensionResource(id = R.dimen.digitFieldHeight)),
        value = fieldState.value,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.digitFieldCornerSize)),
        singleLine = true,
        textStyle = MaterialTheme.typography.h1,
        onValueChange = { fieldState.value = it },
        keyboardActions = onKeyboardAction(focus),
        keyboardOptions = getKeyboardOptions(isLastField),
        colors = getTextFieldColors(),
    )
}

@Composable
private fun onKeyboardAction(
    focus: FocusManager
) = KeyboardActions(
    onNext = { focus.moveFocus(FocusDirection.Next) },
    onDone = {
        focus.clearFocus()
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
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent
)

@Composable
private fun ContinueButtonComposable() {
    val continueButtonModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.smallSpacing))
    Button(
        modifier = continueButtonModifier,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.buttonCornersSize))),
        onClick = { },
    ) {
        Text(stringResource(R.string.button_continue_text))
    }
}
