package com.android.hindara.booking.app.utils

import android.util.LayoutDirection
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private const val MDPI = 160
private const val HDPI = 240
private const val XHDPI = 320
private const val XXHDPI = 480
private const val XXXHDPI = 640


val onBoardingImageSizeMap = mutableMapOf(
    MDPI to Pair(100.dp, 200.dp),
    HDPI to Pair(150.dp, 250.dp),
    XHDPI to Pair(250.dp, 350.dp),
    XXHDPI to Pair(250.dp, 350.dp),
    XXXHDPI to Pair(300.dp, 400.dp),
)

/**
 *
 * */
val headerImageHeightMap = mutableMapOf(
    MDPI to 200.dp,
    HDPI to 250.dp,
    XHDPI to 300.dp,
    XXHDPI to 300.dp,
    XXXHDPI to 350.dp,
)

@Composable
fun getOnBoardingImageSizeInDp(): Pair<Dp, Dp> {
    val configuration = LocalConfiguration.current
    val pair = onBoardingImageSizeMap[configuration.densityDpi]
    return pair ?: Pair(300.dp, 400.dp)
}

@Composable
fun getHeaderImageHeightInDp(): Dp {
    val configuration = LocalConfiguration.current
    val height = headerImageHeightMap[configuration.densityDpi]
    return height ?: 400.dp
}

@Composable
fun getHalfScreenWidth(): Dp {
    val configuration = LocalConfiguration.current
    return configuration.screenWidthDp.div(2.5).dp
}

@Composable
fun isRtlLayout() = LocalConfiguration.current.layoutDirection == LayoutDirection.RTL

