package com.android.hindara.booking.app.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.BordersColor
import com.android.hindara.booking.app.ui.theme.CardBackgroundColor

@Composable
fun HindaraCard(
    showBorders: Boolean = true,
    cornersSize: Dp = dimensionResource(id = R.dimen.cardCornersSize),
    content: @Composable () -> Unit
) {
    val cardModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.defaultSpacing))

    Card(
        modifier = cardModifier,
        backgroundColor = CardBackgroundColor,
        elevation = 0.dp,
        border = if (showBorders) borderStroke() else null,
        shape = RoundedCornerShape(cornersSize),
        content = { content() }
    )
}

@Composable
private fun borderStroke() = BorderStroke(
    dimensionResource(id = R.dimen.borderWidth), brush = SolidColor(
        BordersColor
    )
)