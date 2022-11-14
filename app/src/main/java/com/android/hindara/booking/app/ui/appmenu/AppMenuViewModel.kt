package com.android.hindara.booking.app.ui.appmenu

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import javax.inject.Inject

class AppMenuViewModel @Inject constructor() : ViewModel() {

    fun getProfileInfo(): ProfileInfo {
        return ProfileInfo(
            name = "Ahmad Ali",
            country = "United Arab Emirates",
            picture = R.drawable.ic_profile_picture,
            address = "NA",
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
            MenuItem(R.string.menu_item_bookmarks, R.drawable.ic_bookmark),
            MenuItem(R.string.menu_item_my_bookings, R.drawable.ic_tickets),
            MenuItem(R.string.menu_item_settings, R.drawable.ic_settings),
            MenuItem(R.string.menu_item_help, R.drawable.ic_help),
            MenuItem(R.string.menu_item_logout, R.drawable.ic_logout),
        )
    }
}

data class ProfileInfo(
    val name: String,
    val picture: Int,
    val country: String,
    val address: String,
    val phoneNumber: String,
    val bookingHistory: BookingHistory,

)

data class BookingHistory(
    val totalReviews: Int,
    val totalTransactions: Int,
    val totalBookings: Int
)

data class MenuItem(
    val title: Int,
    val icon: Int
)

