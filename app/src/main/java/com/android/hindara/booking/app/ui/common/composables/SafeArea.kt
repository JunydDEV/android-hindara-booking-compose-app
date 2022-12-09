package com.android.hindara.booking.app.ui.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.ScreenBackgroundColorLight

@Composable
fun SafeArea(content: @Composable ColumnScope.(PaddingValues) -> Unit) {
    val paddingValues = PaddingValues(
        top = dimensionResource(id = R.dimen.extra_large_spacing),
        bottom = dimensionResource(id = R.dimen.large_spacing),
    )

    val parentColumnModifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
        .padding(paddingValues)

    Column(parentColumnModifier) {
        content(paddingValues)
    }
}