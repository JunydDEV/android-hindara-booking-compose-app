package com.android.hindara.booking.app.ui.home

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {

    private lateinit var chosenHotel: Hotel

    fun onChooseHotel(hotel: Hotel) {
        chosenHotel = hotel
    }

    fun getChosenHotel(): Hotel {
        return chosenHotel
    }

    fun getFeaturedCategories(): List<FeaturedCategory> {
        val list = mutableListOf<FeaturedCategory>()

        val recommendedCategory = getCategory("Recommended")
        list.add(recommendedCategory)

        val popularCategory = getCategory("Popular")
        list.add(popularCategory)

        val trendingCategory = getCategory("Trending")
        list.add(trendingCategory)

        return list
    }

    private fun getCategory(name: String): FeaturedCategory {
        val recommendedHotels = mutableListOf<Hotel>()
        recommendedHotels.add(
            Hotel(
                name = "Tropicasa De Hotel",
                image = R.drawable.ic_tropicasa_hotel,
                address = "Amsterdam, Netherlands",
                rating = 4.6f,
                description = "Tropicasa De Hotel is high rated hotels with 1000+ reviews and we are always maintaning the quality for better rating and high attitude service for you",
                pricePerNight = 125.0,
                reviewsList = listOf(
                    Reviews("Brad John", R.drawable.ic_profile_picture, 4.5f, "The bed was nice and comfortable, the service was on point. Good job!"),
                    Reviews("Kate Rose", R.drawable.ic_profile_picture, 4.5f, "9/10 for me personally, no complain at all because it’s perfect. Thanks!"),
                )

            )
        )
        recommendedHotels.add(
            Hotel(
                name = "Luxe Hotel",
                image = R.drawable.ic_luxe_hotel,
                address = "Jakarta, Indonesia",
                rating = 4.6f,
                description = "Luxe Hotel is high rated hotels with 1000+ reviews and we are always maintaning the quality for better rating and high attitude service for you",
                pricePerNight = 125.0,
                reviewsList = listOf(
                    Reviews("Brad John", R.drawable.ic_profile_picture, 4.5f, "The bed was nice and comfortable, the service was on point. Good job!"),
                    Reviews("Kate Rose", R.drawable.ic_profile_picture, 4.5f, "9/10 for me personally, no complain at all because it’s perfect. Thanks!"),
                )
            )
        )

        recommendedHotels.add(
            Hotel(
                name = "Tropicasa De Hotel",
                image = R.drawable.ic_cleans_hotel,
                address = "Amsterdam, Netherlands",
                rating = 4.6f,
                description = "Tropicasa De Hotel is high rated hotels with 1000+ reviews and we are always maintaning the quality for better rating and high attitude service for you",
                pricePerNight = 125.0,
                reviewsList = listOf(
                    Reviews("Brad John", R.drawable.ic_profile_picture, 4.5f, "The bed was nice and comfortable, the service was on point. Good job!"),
                    Reviews("Kate Rose", R.drawable.ic_profile_picture, 4.5f, "9/10 for me personally, no complain at all because it’s perfect. Thanks!"),
                )
            )
        )

        return FeaturedCategory(name, recommendedHotels)
    }
}

data class FeaturedCategory(
    val categoryName: String,
    val hotelsList: List<Hotel>
)

data class Hotel(
    val name: String,
    val image: Int,
    val address: String,
    val rating: Float,
    val description: String,
    val pricePerNight: Double,
    val reviewsList: List<Reviews>?
)

data class Reviews(
    val reviewerName: String,
    val reviewImage: Int,
    val rating: Float,
    val comment: String
)