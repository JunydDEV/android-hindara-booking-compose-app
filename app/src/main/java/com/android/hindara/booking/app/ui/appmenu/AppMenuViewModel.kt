package com.android.hindara.booking.app.ui.appmenu

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import com.core.model.profile_info.BookingHistory
import com.core.model.profile_info.MenuItem
import com.core.model.profile_info.ProfileInfo
import javax.inject.Inject

class AppMenuViewModel @Inject constructor() : ViewModel() {

    fun getProfileInfo(): ProfileInfo {
        return ProfileInfo(
            name = R.string.label_profile_name,
            country = R.string.label_profile_country,
            picture = R.drawable.ic_profile_picture,
            address = R.string.label_profile_address,
            phoneNumber = "NA",
            bookingHistory = BookingHistory(
                totalBookings = 2,
                totalTransactions = 75,
                totalReviews = 47
            )
        )
    }

    fun getMenuItems(): List<MenuItem> {
        return mutableListOf(
            MenuItem(R.string.label_bookmarks, R.drawable.ic_bookmark),
            MenuItem(R.string.label_my_bookings, R.drawable.ic_tickets),
            MenuItem(R.string.label_settings, R.drawable.ic_settings),
            MenuItem(R.string.label_help, R.drawable.ic_help),
            MenuItem(R.string.label_logout, R.drawable.ic_logout),
        )
    }
}

