package com.core.model.hotel_details

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("name") val locationTitle: String,
)