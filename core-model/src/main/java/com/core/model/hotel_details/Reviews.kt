package com.core.model.hotel_details

import com.google.gson.annotations.SerializedName

data class Reviews(
    @SerializedName("name") val reviewerName: String,
    @SerializedName("image") val reviewImage: String,
    @SerializedName("rating") val rating: Float,
    @SerializedName("review") val comment: String
)
