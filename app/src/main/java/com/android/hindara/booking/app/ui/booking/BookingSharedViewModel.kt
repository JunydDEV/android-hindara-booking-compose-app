package com.android.hindara.booking.app.ui.booking

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import com.core.model.booking.PaymentMethod
import com.core.model.hotel_details.Hotel
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class BookingSharedViewModel @Inject constructor(): ViewModel() {

    lateinit var paymentMethod: PaymentMethod
    lateinit var checkInDate: LocalDate
    lateinit var checkOutDate: LocalDate
    lateinit var chosenHotel: Hotel

    fun getPaymentMethodsList(): List<PaymentMethod> {
        val masterCard = PaymentMethod(R.drawable.ic_mastercard, R.string.label_mastercard, false)
        val visa = PaymentMethod(R.drawable.ic_visa, R.string.label_visa, false)
        val paypal = PaymentMethod(R.drawable.ic_paypal, R.string.label_paypal, false)
        return listOf(masterCard, visa, paypal)
    }

    fun getReservedNightsCount(): Long {
        return ChronoUnit.DAYS.between(checkInDate, checkOutDate)
    }

    fun getTotalOfReservedNights(): Double {
        val nightsCount = getReservedNightsCount()
        val perNightPrice = chosenHotel.pricePerNight
        return nightsCount * perNightPrice
    }

    fun getTaxTotal(): Double {
        val tax = chosenHotel.tax
        val totalOfReservedNights = getTotalOfReservedNights()
        return (tax / 100) * totalOfReservedNights
    }

    fun getTotalBill(): Double {
        return getTotalOfReservedNights() + getTaxTotal()
    }

}
