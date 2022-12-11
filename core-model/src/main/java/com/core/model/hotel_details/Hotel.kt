package com.core.model.hotel_details

data class Hotel(
    val name: String,
    val image: Int,
    val address: Address,
    val rating: Float,
    val description: String,
    val tax: Double,
    val pricePerNight: Double,
    val reviewsList: List<Reviews>
)