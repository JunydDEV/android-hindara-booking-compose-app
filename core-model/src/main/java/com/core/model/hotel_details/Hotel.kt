package com.core.model.hotel_details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Hotel(
    @SerializedName("name") @Expose val name: String,
    @SerializedName("image") @Expose val image: String,
    @SerializedName("address") @Expose val address: Address,
    @SerializedName("rating") @Expose val rating: Float,
    @SerializedName("description") @Expose val description: String,
    @SerializedName("tax") @Expose val tax: Double,
    @SerializedName("pricePerNight") @Expose val pricePerNight: Double,
    @SerializedName("reviewsList") @Expose val reviewsList: List<Reviews>
)