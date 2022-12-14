package com.android.hindara.booking.app.ui.appmenu.mybookings

import android.content.Context
import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import com.core.data.DataRepositoryImpl
import com.core.model.booking.MyBooking
import com.core.model.booking.PaymentMethod
import com.core.model.hotel_details.Address
import com.core.model.hotel_details.Hotel
import com.core.model.hotel_details.Reviews
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

class MyBookingsViewModel @Inject constructor() : ViewModel() {

    private val dataRepository = DataRepositoryImpl()
    lateinit var chosenBooking: MyBooking

    fun getMyBookings(context: Context): StateFlow<List<MyBooking>> {
        var list = mutableListOf<MyBooking>()
        val stateFlow = MutableStateFlow(list)
        CoroutineScope(Dispatchers.IO).launch {
            list = dataRepository.provideMyBookedHotels(context)
                .map { hotel ->
                    MyBooking(
                        hotel = hotel,
                        checkInDate = LocalDate.now(),
                        checkOutDate = LocalDate.now().plusDays(5),
                        paymentMethod = PaymentMethod(R.drawable.ic_visa, R.string.label_visa, true)
                    )
                }
                .toMutableList()
            stateFlow.value = list
        }
        return stateFlow
    }


    fun getCheckInDate(): LocalDate {
        return LocalDate.now().plusDays(5)
    }
}
