package com.android.hindara.booking.app.ui.authentication.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.authentication.authenticationRoute
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.PasswordTextFieldComposable
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.forgotpassword.forgotPasswordBottomSheetRoute
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.getTextFieldColors
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.onKeyboardAction
import com.android.hindara.booking.app.ui.home.homeRoute
import com.android.hindara.booking.app.ui.theme.*
import com.android.hindara.booking.app.utils.getHalfScreenWidth

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    MainScreenContent(navController = navController)
}

@Composable
private fun MainScreenContent(navController: NavController) {
    val mainModifier = Modifier
        .fillMaxSize()
        .background(ScreenBackgroundColor)
        .padding(dimensionResource(id = R.dimen.default_spacing))
        .verticalScroll(rememberScrollState())

    Column(
        modifier = mainModifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.large_spacing))
    ) {
        EmailComposable()
        PasswordComposable()
        ForgotPasswordTextComposable(navController)
        BottomButtonAreaComposable(navController)
    }
}

@Composable
private fun EmailComposable() {
    Column {
        EmailTextFieldLabelComposable()
        EmailTextFieldComposable()
    }
}

@Composable
private fun PasswordComposable() {
    Column {
        PasswordTextFieldLabelComposable()
        PasswordTextFieldComposable()
    }
}

@Composable
private fun BottomButtonAreaComposable(navController: NavController) {
    Column {
        LoginButtonComposable(navController)
        HorizontalLineComposable()
        SocialAuthLoginButtonsComposable()
    }
}

@Composable
fun SocialAuthLoginButtonsComposable() {
    val rowContentModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.default_spacing),
            bottom = dimensionResource(id = R.dimen.default_spacing)
        )
    Row(
        modifier = rowContentModifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GoogleAuthButtonComposable()
        FacebookAuthButtonComposable()
    }
}

@Composable
private fun EmailTextFieldLabelComposable() {
    val emailLabelModifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.small_spacing),
            end = dimensionResource(id = R.dimen.small_spacing),
        )
    Text(
        modifier = emailLabelModifier,
        text = stringResource(R.string.textField_email_label),
        style = typography.body1,
        color = DarkTextColor
    )
}

@Composable
fun EmailTextFieldComposable() {
    val focus = LocalFocusManager.current
    val textFieldEmailState = remember {
        mutableStateOf("")
    }

    val emailTextFieldModifier = Modifier.fillMaxWidth()

    TextField(
        modifier = emailTextFieldModifier,
        value = textFieldEmailState.value,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.textField_corners_size))),
        singleLine = true,
        textStyle = typography.body1,
        onValueChange = { textFieldEmailState.value = it },
        keyboardActions = onKeyboardAction(focus),
        keyboardOptions = getEmailKeyboardOptions(),
        colors = getTextFieldColors(),
        placeholder = {
            EmailPlaceholderContent(typography)
        }
    )
}

@Composable
private fun PasswordTextFieldLabelComposable() {
    val passwordLabelModifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.small_spacing),
            end = dimensionResource(id = R.dimen.small_spacing),
        )
    Text(
        modifier = passwordLabelModifier,
        text = stringResource(R.string.textField_password_label),
        style = typography.body1,
        color = DarkTextColor
    )
}

@Composable
fun ForgotPasswordTextComposable(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd,
    ) {
        Text(
            modifier = Modifier.clickable {
                navController.navigate(forgotPasswordBottomSheetRoute)
            },
            text = stringResource(R.string.label_forgot_password_q),
            style = typography.body1,
            color = DarkTextColor,
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun HorizontalLineComposable() {
    val horizontalLineModifier = Modifier
        .width(dimensionResource(id = R.dimen.login_with_line_width))
        .height(dimensionResource(id = R.dimen.login_with_line_height))
        .background(LineColor)

    val alternateLoginWithTextModifier = Modifier
        .wrapContentWidth()
        .background(ScreenBackgroundColor)
        .padding(
            start = dimensionResource(id = R.dimen.default_spacing),
            end = dimensionResource(id = R.dimen.default_spacing),
        )

    val boxModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.default_spacing),
            start = dimensionResource(id = R.dimen.large_spacing),
            end = dimensionResource(id = R.dimen.large_spacing)
        )

    Box(
        modifier = boxModifier,
        contentAlignment = Alignment.Center
    ) {
        Spacer(modifier = horizontalLineModifier)
        Text(
            modifier = alternateLoginWithTextModifier,
            text = stringResource(R.string.label_login_with),
            style = typography.body1,
            color = DarkTextColor,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
private fun LoginButtonComposable(navController: NavController) {
    val loginButtonModifier = Modifier.fillMaxWidth()
    Button(
        modifier = loginButtonModifier,
        shape = RoundedCornerShape(
            CornerSize(dimensionResource(id = R.dimen.primary_button_corners_size))
        ),
        onClick = {
            navigationToHomeScreen(navController)
        },
    ) {
        Text(stringResource(R.string.button_login_label))
    }
}

private fun navigationToHomeScreen(navController: NavController) {
    navController.navigate(homeRoute) {
        popUpTo(authenticationRoute) {
            inclusive = true
        }
    }
}

@Composable
private fun FacebookAuthButtonComposable() {
    val facebookButtonModifier = Modifier
        .width(getHalfScreenWidth())
    Button(
        modifier = facebookButtonModifier,
        shape = RoundedCornerShape(
            CornerSize(dimensionResource(id = R.dimen.primary_button_corners_size))
        ),
        colors = ButtonDefaults.buttonColors(backgroundColor = FacebookColor),
        onClick = { /*TODO*/ },
    ) {
        FacebookAuthButtonContentComposable()
    }
}

@Composable
private fun FacebookAuthButtonContentComposable() {
    Row(modifier = Modifier.wrapContentWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_facebook),
            contentDescription = stringResource(R.string.image_facebook),
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(
            modifier = Modifier
                .width(10.dp)
                .height(10.dp)
        )
        Text(
            text = stringResource(R.string.button_facebook_label),
            color = WhiteColor
        )
    }
}

@Composable
private fun GoogleAuthButtonComposable() {
    val googleButtonModifier = Modifier
        .width(getHalfScreenWidth())
    Button(
        modifier = googleButtonModifier,
        shape = RoundedCornerShape(
            CornerSize(dimensionResource(id = R.dimen.primary_button_corners_size))
        ),
        colors = ButtonDefaults.buttonColors(backgroundColor = WhiteColor),
        onClick = { /*TODO*/ },
    ) {
        GoogleAuthButtonContentComposable()
    }
}

@Composable
private fun GoogleAuthButtonContentComposable() {
    Row(
        modifier = Modifier.wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = stringResource(R.string.image_google),
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(
            modifier = Modifier
                .width(10.dp)
                .height(10.dp)
        )
        Text(
            text = stringResource(R.string.button_google_label),
            color = DarkTextColor
        )
    }
}

@Composable
private fun EmailPlaceholderContent(typography: Typography) {
    Text(
        text = stringResource(R.string.textField_email_placeholder),
        style = typography.body1,
        color = FieldPlaceholderColor
    )
}

@Composable
private fun getEmailKeyboardOptions() = KeyboardOptions(
    imeAction = ImeAction.Next,
    keyboardType = KeyboardType.Email
)

