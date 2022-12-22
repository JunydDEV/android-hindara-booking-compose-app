package com.core.model.hotel_details

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class Reviews(
    @SerializedName("name") @Expose val reviewerName: String,
    @SerializedName("image") @Expose val reviewImage: String,
    @SerializedName("rating") @Expose val rating: Float,
    @SerializedName("review") @Expose val comment: String
)
