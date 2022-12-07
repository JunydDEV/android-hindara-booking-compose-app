package com.android.hindara.booking.app.ui.appmenu.bookmarks

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.home.Address
import com.android.hindara.booking.app.ui.home.Hotel
import com.android.hindara.booking.app.ui.home.Reviews
import javax.inject.Inject

class BookmarksViewModel @Inject constructor() : ViewModel() {

    fun getBookmarkedHotelsList(): List<Hotel> {
        val list = mutableListOf<Hotel>()
        list.add(
            Hotel(
                name = "Tropicasa De Hotel",
                image = R.drawable.ic_tropicasa_hotel,
                address = Address(
                    latitude = 52.1326,
                    longitude = 5.2913,
                    locationTitle = "Amsterdam, Netherlands"
                ),
                rating = 4.6f,
                tax = 10.0,
                description = "Tropicasa De Hotel is high rated hotels with 1000+ reviews and we are always maintaning the quality for better rating and high attitude service for you",
                pricePerNight = 125.0,
                reviewsList = listOf(
                    Reviews(
                        "Brad John",
                        R.drawable.ic_profile_picture,
                        4.5f,
                        "The bed was nice and comfortable, the service was on point. Good job!"
                    ),
                    Reviews(
                        "Kate Rose",
                        R.drawable.ic_profile_picture,
                        4.5f,
                        "9/10 for me personally, no complain at all because it’s perfect. Thanks!"
                    ),
                    Reviews(
                        "Kate Rose",
                        R.drawable.ic_profile_picture,
                        4.5f,
                        "9/10 for me personally, no complain at all because it’s perfect. Thanks!"
                    ),
                    Reviews(
                        "Kate Rose",
                        R.drawable.ic_profile_picture,
                        4.5f,
                        "9/10 for me personally, no complain at all because it’s perfect. Thanks!"
                    ),
                    Reviews(
                        "Kate Rose",
                        R.drawable.ic_profile_picture,
                        4.5f,
                        "9/10 for me personally, no complain at all because it’s perfect. Thanks!"
                    ),
                )

            )
        )
        list.add(
            Hotel(
                name = "Luxe Hotel",
                image = R.drawable.ic_luxe_hotel,
                address = Address(
                    latitude = 52.1326,
                    longitude = 5.2913,
                    locationTitle = "Amsterdam, Netherlands"
                ),
                rating = 4.6f,
                tax = 10.0,
                description = "Luxe Hotel is high rated hotels with 1000+ reviews and we are always maintaning the quality for better rating and high attitude service for you",
                pricePerNight = 125.0,
                reviewsList = listOf(
                    Reviews(
                        "Brad John",
                        R.drawable.ic_profile_picture,
                        4.5f,
                        "The bed was nice and comfortable, the service was on point. Good job!"
                    ),
                    Reviews(
                        "Kate Rose",
                        R.drawable.ic_profile_picture,
                        4.5f,
                        "9/10 for me personally, no complain at all because it’s perfect. Thanks!"
                    ),
                )
            )
        )

        list.add(
            Hotel(
                name = "Tropicasa De Hotel",
                image = R.drawable.ic_cleans_hotel,
                address = Address(
                    latitude = 52.1326,
                    longitude = 5.2913,
                    locationTitle = "Amsterdam, Netherlands"
                ),
                rating = 4.6f,
                description = "Tropicasa De Hotel is high rated hotels with 1000+ reviews and we are always maintaning the quality for better rating and high attitude service for you",
                pricePerNight = 125.0,
                tax = 10.0,
                reviewsList = listOf(
                    Reviews(
                        "Brad John",
                        R.drawable.ic_profile_picture,
                        4.5f,
                        "The bed was nice and comfortable, the service was on point. Good job!"
                    ),
                    Reviews(
                        "Kate Rose",
                        R.drawable.ic_profile_picture,
                        4.5f,
                        "9/10 for me personally, no complain at all because it’s perfect. Thanks!"
                    ),
                )
            )
        )
        return list
    }
}