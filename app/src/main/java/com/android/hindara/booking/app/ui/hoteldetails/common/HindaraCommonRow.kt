package com.android.hindara.booking.app.ui.hoteldetails.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.DarkTextColor
import com.android.hindara.booking.app.ui.theme.LightTextColor

@Composable
fun HindaraCommonRow(label: String, value: String) {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(dimensionResource(id = R.dimen.defaultSpacing))

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.wrapContentWidth(),
            text = label,
            style = MaterialTheme.typography.body1,
            color = LightTextColor
        )
        Text(
            modifier = Modifier.wrapContentWidth(),
            text = value,
            style = MaterialTheme.typography.h2,
            color = DarkTextColor
        )
    }
}