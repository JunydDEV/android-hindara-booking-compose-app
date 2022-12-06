package com.android.hindara.booking.app.ui.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.dimensionResource
import com.android.hindara.booking.app.R

object BottomSheetLayoutConfig {

    @Composable
    @ReadOnlyComposable
    fun sheetShape() = RoundedCornerShape(
        topStart = dimensionResource(id = R.dimen.primary_bottom_sheet_corners_size),
        topEnd = dimensionResource(id = R.dimen.primary_bottom_sheet_corners_size)
    )

}
