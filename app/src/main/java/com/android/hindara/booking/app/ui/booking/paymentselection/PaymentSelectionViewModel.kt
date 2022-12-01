package com.android.hindara.booking.app.ui.booking.paymentselection

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import javax.inject.Inject

class PaymentSelectionViewModel @Inject constructor() : ViewModel() {

    fun getPaymentMethodsList(): List<PaymentMethod> {
        val masterCard = PaymentMethod(R.drawable.ic_mastercard, R.string.mastercard, false)
        val visa = PaymentMethod(R.drawable.ic_visa, R.string.visa, false)
        val paypal = PaymentMethod(R.drawable.ic_paypal, R.string.paypal, false)
        return listOf(masterCard, visa, paypal)
    }
}

data class PaymentMethod(val icon: Int, val name: Int, var isSelected: Boolean)
