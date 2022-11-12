package com.android.hindara.booking.app.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.DarkTextColor
import com.android.hindara.booking.app.ui.theme.FieldBackgroundColor
import com.android.hindara.booking.app.ui.theme.FieldPlaceholderColor

@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier,
    navHostController: NavHostController) {
    Column(
        modifier = modifier
    ) {
        SpacerComposable()
        TitleComposable()
        SpacerComposable()
        SearchTextFieldComposable()
        SpacerComposable()
        FeaturedOnHomeScreenListing(viewModel)
    }
}

@Composable
private fun SpacerComposable() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.defaultSpacing))
    )
}

@Composable
private fun TitleComposable() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(id = R.dimen.defaultSpacing),
                end = dimensionResource(id = R.dimen.defaultSpacing)
            ),
        text = stringResource(R.string.title_home_screen),
        style = MaterialTheme.typography.h1.copy(fontSize = 28.sp),
        color = DarkTextColor
    )
}

@Composable
fun SearchTextFieldComposable() {
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
        textStyle = MaterialTheme.typography.body1,
        onValueChange = { textFieldEmailState.value = it },
        keyboardActions = onKeyboardAction(focus),
        keyboardOptions = getKeyboardOptions(ImeAction.Search, KeyboardType.Text),
        colors = getTextFieldColors(),
        placeholder = {
            SearchFieldPlaceholderContent()
        },
        leadingIcon = {
            SearchIconComposable()
        }
    )
}

@Composable
private fun SearchIconComposable() = Image(
    painter = painterResource(id = R.drawable.ic_search),
    contentDescription = stringResource(
        R.string.search_image_description
    )
)


@Composable
private fun SearchFieldPlaceholderContent() = Text(
    text = stringResource(R.string.textField_find_hotels_placeholder),
    style = MaterialTheme.typography.body1,
    color = FieldPlaceholderColor
)


@Composable
private fun getTextFieldColors() = TextFieldDefaults.textFieldColors(
    backgroundColor = FieldBackgroundColor,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent
)

@Composable
private fun onKeyboardAction(focus: FocusManager) = KeyboardActions(
    onNext = { focus.moveFocus(FocusDirection.Down) },
    onDone = { focus.clearFocus() },
    onSearch = { focus.clearFocus() }
)

@Composable
private fun getKeyboardOptions(imeAction: ImeAction, keyboardType: KeyboardType) = KeyboardOptions(
    imeAction = imeAction,
    keyboardType = keyboardType
)