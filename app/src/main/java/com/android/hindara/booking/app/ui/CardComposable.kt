package com.android.hindara.booking.app.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.WhiteColor

@Composable
fun HindaraCard(content: @Composable () -> Unit) {
    val cardModifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.defaultSpacing))

    Card(
        modifier = cardModifier,
        backgroundColor = WhiteColor,
        elevation = 0.dp,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.detailsSheetCornersSize))
    ) {
        content()
    }
}