package com.core.model.hotel_details

import androidx.annotation.Keep

@Keep
data class FeaturedCategory(
    val categoryName: Int,
    val hotelsList: List<Hotel>
)
