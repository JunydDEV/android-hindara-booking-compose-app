package com.android.hindara.booking.app.ui.common.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.BordersColor
import com.android.hindara.booking.app.utils.noRippleClickable

@Composable
fun ApplicationCard(
    showBorders: Boolean = true,
    cornersSize: Dp = dimensionResource(id = R.dimen.app_card_corners_size),
    onCardClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val cardModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.default_spacing))

    if (onCardClick != null) {
        cardModifier.noRippleClickable {
            onCardClick.invoke()
        }
    }

    Card(
        modifier = cardModifier,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        border = if (showBorders) borderStroke() else null,
        shape = RoundedCornerShape(cornersSize),
        content = { content() }
    )
}

@Composable
private fun borderStroke() = BorderStroke(
    dimensionResource(id = R.dimen.card_default_borders_width), brush = SolidColor(
        BordersColor
    )
)