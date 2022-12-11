package com.core.model.booking

import com.core.model.hotel_details.Hotel
import java.time.LocalDate

data class MyBooking(
    val hotel: Hotel,
    val checkInDate: LocalDate,
    val checkOutDate: LocalDate,
    val paymentMethod: PaymentMethod
)