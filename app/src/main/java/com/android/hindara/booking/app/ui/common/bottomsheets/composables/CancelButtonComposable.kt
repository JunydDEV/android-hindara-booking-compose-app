package com.android.hindara.booking.app.ui.common.bottomsheets.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.CancelButtonColor
import com.android.hindara.booking.app.ui.theme.LightTextColor
import com.android.hindara.booking.app.ui.theme.ScreenBackgroundColor

@Composable
fun CancelButtonComposable(onClick: ()->Unit) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.defaultSpacing),
            bottom = dimensionResource(id = R.dimen.defaultSpacing)
        )

    Button(
        modifier = buttonModifier,
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.buttonBordersWidth),
            brush = SolidColor(CancelButtonColor)
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = ScreenBackgroundColor,
            contentColor = CancelButtonColor,
            disabledContentColor = LightTextColor
        ),
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.buttonCornersSize))),
        onClick = {
            onClick()
        },
    ) {
        Text(stringResource(R.string.button_cancel_booking))
    }
}