package com.android.hindara.booking.app.ui.authentication.login.bottomsheets.resetpassword

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.data.AlertType
import com.android.hindara.booking.app.ui.authentication.authenticationRoute
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.PasswordTextFieldComposable
import com.android.hindara.booking.app.ui.common.bottomsheets.alertbottomsheet.alertBottomSheetRoute
import com.android.hindara.booking.app.ui.common.bottomsheets.composables.BottomSheetContentWithTitle
import com.android.hindara.booking.app.ui.theme.TextColorLight


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
        PasswordTextFieldComposable()
        ConfirmPasswordTextFieldLabelComposable()
        PasswordTextFieldComposable(
            placeholder = stringResource(id = R.string.textField_confirm_password_placeholder)
        )
        ResetButtonComposable(navController)
    }
}

@Composable
private fun ResetPasswordDescriptionComposable() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.label_reset_password_description),
        style = MaterialTheme.typography.body1
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
        style = MaterialTheme.typography.body1
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
        style = MaterialTheme.typography.body1
    )
}

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

