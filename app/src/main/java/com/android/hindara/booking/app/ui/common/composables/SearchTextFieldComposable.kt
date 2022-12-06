package com.android.hindara.booking.app.ui.common.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.FieldBackgroundColor
import com.android.hindara.booking.app.ui.theme.FieldPlaceholderColor

@Composable
fun SearchTextFieldComposable(
    value: String,
    onValueChange: (String) -> Unit,
    readyOnly: Boolean = false,
    isClickable: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    val focus = LocalFocusManager.current

    val searchTextFieldModifier = Modifier
        .fillMaxWidth()
        .padding(
            start = dimensionResource(id = R.dimen.defaultSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )
        .clickable {
            if (isClickable) {
                onClick?.invoke()
            }
        }

    TextField(
        modifier = searchTextFieldModifier,
        value = value,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.fieldCornersSize))),
        singleLine = true,
        enabled = !readyOnly,
        textStyle = MaterialTheme.typography.body1,
        onValueChange = { onValueChange(it) },
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
        R.string.image_search
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
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
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