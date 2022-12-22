package com.core.model.hotel_details

import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class Hotel(
    @Expose val name: String,
    @Expose val image: String,
    @Expose val address: Address,
    @Expose val rating: Float,
    @Expose val description: String, val tax: Double,
    @Expose val pricePerNight: Double,
    @Expose val reviewsList: List<Reviews>
)