package com.android.hindara.booking.app.utils

import android.annotation.SuppressLint
import android.util.LayoutDirection
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}


@Composable
fun isRtlLayout() = LocalConfiguration.current.layoutDirection == LayoutDirection.RTL

@SuppressLint("DiscouragedApi")
@Composable
fun mapImageToDrawable(imageName: String): Int {
    val context = LocalContext.current
    val resources = context.resources
    return resources.getIdentifier(imageName, "drawable", context.packageName)
}