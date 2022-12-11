package com.core.model.profile_info

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ProfileInfo(
    @StringRes val name: Int,
    @DrawableRes val picture: Int,
    @StringRes val country: Int,
    @StringRes val address: Int,
    val phoneNumber: String,
    val bookingHistory: BookingHistory,

    )