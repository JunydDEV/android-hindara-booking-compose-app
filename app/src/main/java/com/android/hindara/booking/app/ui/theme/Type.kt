package com.android.hindara.booking.app.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.hindara.booking.app.R

val fonts = FontFamily(
    Font(R.font.app_font_regular),
    Font(R.font.app_font_bold, weight = FontWeight.Bold),
    Font(R.font.app_font_semibold, weight = FontWeight.SemiBold),
    Font(R.font.app_font_medium, weight = FontWeight.Medium),
    Font(R.font.app_font_regular, weight = FontWeight.Thin),
    Font(R.font.app_font_regular, weight = FontWeight.Normal, style = FontStyle.Italic),
)

// Set of Material typography styles to start with
val Typography = Typography(
    body2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    ),
    button = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    h1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
    ),
    h2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
    ),
)
