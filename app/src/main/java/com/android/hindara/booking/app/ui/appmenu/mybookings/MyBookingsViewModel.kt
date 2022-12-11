package com.android.hindara.booking.app.ui.appmenu.mybookings

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import com.core.model.booking.MyBooking
import com.core.model.booking.PaymentMethod
import com.core.model.hotel_details.Address
import com.core.model.hotel_details.Hotel
import com.core.model.hotel_details.Reviews
import java.time.LocalDate
import javax.inject.Inject

class MyBookingsViewModel @Inject constructor() : ViewModel() {

    lateinit var chosenBooking: MyBooking

    fun getMyBookings(): List<MyBooking> {
        val hotel = getHotel()
        val firstBooking = MyBooking(
            hotel = hotel,
            checkInDate = LocalDate.now(),
            checkOutDate = LocalDate.now().plusDays(5),
            paymentMethod = PaymentMethod(R.drawable.ic_visa, R.string.label_visa, true)
        )

        val secondBooking = MyBooking(
            hotel = hotel,
            checkInDate = LocalDate.now(),
            checkOutDate = LocalDate.now().plusDays(5),
            paymentMethod = PaymentMethod(R.drawable.ic_visa, R.string.label_visa, true)
        )

        val thirdBooking = MyBooking(
            hotel = hotel,
            checkInDate = LocalDate.now(),
            checkOutDate = LocalDate.now().plusDays(5),
            paymentMethod = PaymentMethod(R.drawable.ic_visa, R.string.label_visa, true)
        )

        return listOf(firstBooking, secondBooking, thirdBooking)
    }

    private fun getHotel(): Hotel {
        return Hotel(
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
    }

    fun getCheckInDate(): LocalDate {
        return LocalDate.now().plusDays(5)
    }
}
