package com.android.hindara.booking.app.ui.home

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import com.core.model.hotel_details.Address
import com.core.model.hotel_details.FeaturedCategory
import com.core.model.hotel_details.Hotel
import com.core.model.hotel_details.Reviews
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {

    private lateinit var selectedHotel: Hotel

    fun onHotelSelect(hotel: Hotel) {
        selectedHotel = hotel
    }

    fun getFeaturedCategories(): List<FeaturedCategory> {
        val list = mutableListOf<FeaturedCategory>()

        val recommendedCategory = getCategory(R.string.tab_recommended_label)
        list.add(recommendedCategory)

        val popularCategory = getCategory(R.string.tab_popular_label)
        list.add(popularCategory)

        val trendingCategory = getCategory(R.string.tab_trending_label)
        list.add(trendingCategory)

        return list
    }

    private fun getCategory(name: Int): FeaturedCategory {
        val recommendedHotels = mutableListOf<Hotel>()
        recommendedHotels.add(
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
        recommendedHotels.add(
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

        recommendedHotels.add(
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

        return FeaturedCategory(name, recommendedHotels)
    }
}