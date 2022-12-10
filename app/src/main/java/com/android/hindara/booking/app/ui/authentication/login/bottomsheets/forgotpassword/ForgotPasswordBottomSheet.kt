package com.android.hindara.booking.app.ui.authentication.login.bottomsheets.forgotpassword

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.authentication.authenticationRoute
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.emailverification.emailVerificationBottomSheetRoute
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.getTextFieldColors
import com.android.hindara.booking.app.ui.common.bottomsheets.composables.BottomSheetContentWithTitle
import com.android.hindara.booking.app.ui.theme.field_placeholder_color

@Composable
fun ForgotPasswordBottomSheet(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    navController: NavController
) {
    ForgotPasswordBottomSheetContent(navController)
}

@Composable
fun ForgotPasswordBottomSheetContent(navController: NavController) {
    BottomSheetContentWithTitle(stringResource(R.string.label_forgot_password)) {
        ForgotPasswordDescriptionComposable()
        ForgotPasswordEmailTextFieldLabelComposable()
        ForgotPasswordEmailTextFieldComposable()
        ContinueButtonComposable(navController)
    }
}

@Composable
private fun ForgotPasswordDescriptionComposable() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.label_forgot_password_description),
        style = MaterialTheme.typography.body1
    )
}

@Composable
private fun ForgotPasswordEmailTextFieldLabelComposable() {
    val emailLabelModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.default_spacing),
            start = dimensionResource(id = R.dimen.small_spacing)
        )
    Text(
        modifier = emailLabelModifier,
        text = stringResource(R.string.textField_email_label),
        style = MaterialTheme.typography.body1
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
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.textField_corners_size)),
        singleLine = true,
        textStyle = MaterialTheme.typography.body1,
        onValueChange = { textFieldEmailState.value = it },
        keyboardActions = onKeyboardAction(focus),
        keyboardOptions = getEmailKeyboardOptions(),
        colors = getTextFieldColors(),
        placeholder = {
            EmailPlaceholderContent(MaterialTheme.typography)
        },
    )
}

@Composable
private fun onKeyboardAction(focus: FocusManager) =
    KeyboardActions(
        onNext = { focus.moveFocus(FocusDirection.Down) },
        onDone = {
            focus.clearFocus()
        }
    )

@Composable
private fun EmailPlaceholderContent(typography: Typography) {
    Text(
        text = stringResource(R.string.textField_email_placeholder),
        style = typography.body1,
        color = field_placeholder_color
    )
}

@Composable
private fun getEmailKeyboardOptions() = KeyboardOptions(
    imeAction = ImeAction.Done,
    keyboardType = KeyboardType.Email
)

@Composable
private fun ContinueButtonComposable(navController: NavController) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.default_spacing))
    Button(
        modifier = buttonModifier,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.primary_button_corners_size))),
        onClick = {
            navController.navigate(emailVerificationBottomSheetRoute) {
                popUpTo(authenticationRoute)
            }
        },
    ) {
        Text(stringResource(R.string.button_continue_label))
    }
}
