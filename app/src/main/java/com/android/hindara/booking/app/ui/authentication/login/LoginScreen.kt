package com.android.hindara.booking.app.ui.authentication.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.DarkTextColor
import com.android.hindara.booking.app.ui.theme.WhiteColor

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SpacerComposable()
        EmailTextFieldLabelComposable()
        EmailTextFieldComposable()

        SpacerComposable()
        PasswordTextFieldLabelComposable()
        PasswordTextFieldComposable()

        ForgotPasswordTextComposable()
        SpacerComposable()
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.defaultSpacing),
                    end = dimensionResource(id = R.dimen.defaultSpacing),
                ),
            shape = RoundedCornerShape(CornerSize(100.dp)),
            onClick = { /*TODO*/ },
        ) {
            Text(
                text = "Login",
                style = typography.body1,
                color = WhiteColor,
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
private fun SpacerComposable() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.largeSpacing))
    )
}

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
        text = "Email",
        style = typography.body1,
        color = DarkTextColor
    )
}

@Composable
fun EmailTextFieldComposable() {
    val emailTextFieldModifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.defaultSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )

    val focus = LocalFocusManager.current
    val textFieldEmailState = remember {
        mutableStateOf("")
    }
    TextField(
        modifier = emailTextFieldModifier,
        value = textFieldEmailState.value,
        onValueChange = {
            textFieldEmailState.value = it
        },
        shape = RoundedCornerShape(CornerSize(50.dp)),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = WhiteColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        textStyle = typography.body1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ),
        keyboardActions = KeyboardActions(
            onNext = { focus.moveFocus(FocusDirection.Down) }
        ),
        placeholder = {
            Text(
                text = "Enter your email",
                style = typography.body2,
                color = Color.Gray
            )
        }
    )
}

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
        text = "Password",
        style = typography.body1,
        color = DarkTextColor
    )
}

@Composable
fun PasswordTextFieldComposable() {
    val passwordTextFieldModifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.defaultSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )

    val focus = LocalFocusManager.current
    val textFieldPasswordState = remember {
        mutableStateOf("")
    }
    TextField(
        modifier = passwordTextFieldModifier,
        value = textFieldPasswordState.value,
        onValueChange = {
            textFieldPasswordState.value = it
        },
        shape = RoundedCornerShape(CornerSize(50.dp)),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = WhiteColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        textStyle = typography.body1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onNext = { focus.moveFocus(FocusDirection.Down) }
        ),
        placeholder = {
            Text(
                text = "Enter your password",
                style = typography.body2,
                color = Color.Gray
            )
        },
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun ForgotPasswordTextComposable() {
    val forgotPasswordTextField = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.defaultSpacing),
            start = dimensionResource(id = R.dimen.largeSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )
    Text(
        modifier = forgotPasswordTextField,
        text = "Forgot Password?",
        style = typography.body1,
        color = DarkTextColor,
        textAlign = TextAlign.End
    )
}

