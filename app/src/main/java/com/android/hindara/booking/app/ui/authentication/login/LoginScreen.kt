package com.android.hindara.booking.app.ui.authentication.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.getHalfScreenWidth
import com.android.hindara.booking.app.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreen(
    navController: NavController,
    bottomSheetState: ModalBottomSheetState,
    scope: CoroutineScope,
    viewModel: LoginViewModel = hiltViewModel()
) {
    MainScreenContent(
        bottomSheetScaffoldState = bottomSheetState,
        coroutineScope = scope
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainScreenContent(
    bottomSheetScaffoldState: ModalBottomSheetState,
    coroutineScope: CoroutineScope
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackgroundColor)
            .verticalScroll(rememberScrollState())
    ) {
        SpacerComposable()
        EmailTextFieldLabelComposable()
        EmailTextFieldComposable()

        SpacerComposable()
        PasswordTextFieldLabelComposable()
        PasswordTextFieldComposable()

        ForgotPasswordTextComposable(bottomSheetScaffoldState, coroutineScope)
        SpacerComposable()
        LoginButtonComposable()

        HorizontalLineComposable()
        SocialAuthLoginButtonsComposable()
    }
}

@Composable
fun SocialAuthLoginButtonsComposable() {
    val rowContentModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.defaultSpacing),
            start = dimensionResource(id = R.dimen.defaultSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
            bottom = dimensionResource(id = R.dimen.defaultSpacing)
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
            top = dimensionResource(id = R.dimen.defaultSpacing),
            start = dimensionResource(id = R.dimen.largeSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
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

    val emailTextFieldModifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.defaultSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )

    TextField(
        modifier = emailTextFieldModifier,
        value = textFieldEmailState.value,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.fieldCornersSize))),
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
            top = dimensionResource(id = R.dimen.defaultSpacing),
            start = dimensionResource(id = R.dimen.largeSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )
    Text(
        modifier = passwordLabelModifier,
        text = stringResource(R.string.textField_password_label),
        style = typography.body1,
        color = DarkTextColor
    )
}

@Composable
fun PasswordTextFieldComposable() {
    val focus = LocalFocusManager.current
    val textFieldPasswordState = remember { mutableStateOf("") }
    val showPasswordState = remember { mutableStateOf(false) }

    val passwordTextFieldModifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.defaultSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )

    TextField(
        modifier = passwordTextFieldModifier,
        value = textFieldPasswordState.value,
        onValueChange = { textFieldPasswordState.value = it },
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.fieldCornersSize))),
        colors = getTextFieldColors(),
        singleLine = true,
        textStyle = typography.body1,
        keyboardOptions = getPasswordKeyboardOptions(),
        keyboardActions = onKeyboardAction(focus),
        placeholder = { PasswordPlaceholderContent(typography) },
        visualTransformation = getPasswordTransformation(showPasswordState),
        trailingIcon = {
            val (icon, iconColor) = getVisibilitySelectionPair(showPasswordState)
            PasswordVisibilityTrailingIcon(showPasswordState, icon, iconColor)
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ForgotPasswordTextComposable(
    bottomSheetScaffoldState: ModalBottomSheetState,
    coroutineScope: CoroutineScope
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd,
    ) {
        val forgotPasswordTextModifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.defaultSpacing),
                start = dimensionResource(id = R.dimen.largeSpacing),
                end = dimensionResource(id = R.dimen.defaultSpacing),
            )

        Text(
            modifier = forgotPasswordTextModifier.clickable {
                showBottomSheet(bottomSheetScaffoldState, coroutineScope)
            },
            text = stringResource(R.string.text_forgot_password_label),
            style = typography.body1,
            color = DarkTextColor,
            textAlign = TextAlign.End
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
fun showBottomSheet(
    bottomSheetScaffoldState: ModalBottomSheetState,
    coroutineScope: CoroutineScope
) {
    coroutineScope.launch {
        bottomSheetScaffoldState.animateTo(ModalBottomSheetValue.Expanded)
    }
}

@Composable
fun HorizontalLineComposable() {
    val horizontalLineModifier = Modifier
        .width(dimensionResource(id = R.dimen.horizontalLineWidth))
        .height(dimensionResource(id = R.dimen.horizontalLineHeight))
        .background(LineColor)

    val alternateLoginWithTextModifier = Modifier
        .wrapContentWidth()
        .background(ScreenBackgroundColor)
        .padding(
            start = dimensionResource(id = R.dimen.largeSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )

    val boxModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.defaultSpacing),
            start = dimensionResource(id = R.dimen.largeSpacing),
            end = dimensionResource(id = R.dimen.largeSpacing)
        )

    Box(
        modifier = boxModifier,
        contentAlignment = Alignment.Center
    ) {
        Spacer(modifier = horizontalLineModifier)
        Text(
            modifier = alternateLoginWithTextModifier,
            text = stringResource(R.string.text_alternate_login_with),
            style = typography.body1,
            color = DarkTextColor,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
private fun LoginButtonComposable() {
    val loginButtonModifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.defaultSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )
    Button(
        modifier = loginButtonModifier,
        shape = RoundedCornerShape(CornerSize(100.dp)),
        onClick = { /*TODO*/ },
    ) {
        Text(stringResource(R.string.button_login_text))
    }
}


@Composable
private fun FacebookAuthButtonComposable() {
    val facebookButtonModifier = Modifier
        .width(getHalfScreenWidth())
    Button(
        modifier = facebookButtonModifier,
        shape = RoundedCornerShape(CornerSize(100.dp)),
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
            contentDescription = stringResource(R.string.facebook_image_description),
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(
            modifier = Modifier
                .width(10.dp)
                .height(10.dp)
        )
        Text(
            text = stringResource(R.string.button_facebook_text),
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
        shape = RoundedCornerShape(CornerSize(100.dp)),
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
            contentDescription = stringResource(R.string.google_image_description),
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(
            modifier = Modifier
                .width(10.dp)
                .height(10.dp)
        )
        Text(
            text = stringResource(R.string.button_login_text),
            color = DarkTextColor
        )
    }
}

@Composable
private fun SpacerComposable() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.smallSpacing))
    )
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

@Composable
private fun PasswordPlaceholderContent(typography: Typography) {
    Text(
        text = stringResource(R.string.textField_password_placeholder),
        style = typography.body1,
        color = FieldPlaceholderColor
    )
}

@Composable
private fun getTextFieldColors() = TextFieldDefaults.textFieldColors(
    backgroundColor = FieldBackgroundColor,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent
)

@Composable
private fun onKeyboardAction(focus: FocusManager) = KeyboardActions(
    onNext = { focus.moveFocus(FocusDirection.Down) },
    onDone = { focus.clearFocus() }
)

@Composable
private fun getPasswordKeyboardOptions() = KeyboardOptions(
    imeAction = ImeAction.Done,
    keyboardType = KeyboardType.Password
)

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

