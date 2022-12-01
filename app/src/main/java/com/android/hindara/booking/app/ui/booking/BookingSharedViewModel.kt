package com.android.hindara.booking.app.ui.booking

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.home.Hotel
import java.time.LocalDate
import javax.inject.Inject

class BookingSharedViewModel @Inject constructor(): ViewModel() {

    lateinit var paymentMethod: PaymentMethod
    lateinit var checkInDate: LocalDate
    lateinit var checkOutDate: LocalDate
    lateinit var chosenHotel: Hotel

    fun getPaymentMethodsList(): List<PaymentMethod> {
        val masterCard = PaymentMethod(R.drawable.ic_mastercard, R.string.mastercard, false)
        val visa = PaymentMethod(R.drawable.ic_visa, R.string.visa, false)
        val paypal = PaymentMethod(R.drawable.ic_paypal, R.string.paypal, false)
        return listOf(masterCard, visa, paypal)
    }

}

data class PaymentMethod(val icon: Int, val name: Int, var isSelected: Boolean)
