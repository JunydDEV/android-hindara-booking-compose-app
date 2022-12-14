package com.android.hindara.booking.app.ui.search

import android.content.Context
import androidx.lifecycle.ViewModel
import com.core.data.DataRepositoryImpl
import com.core.model.hotel_details.Hotel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class SearchViewModel @Inject constructor() : ViewModel() {

    private val dataRepository = DataRepositoryImpl()
    private val allHotels = mutableListOf<Hotel>()
    val searchResultsLiveData: MutableStateFlow<List<Hotel>?> = MutableStateFlow(allHotels)

    fun searchHotels(hotelName: String) {
        if (hotelName.isEmpty()) {
            searchResultsLiveData.value = allHotels
        } else {
            searchResultsLiveData.value = allHotels.filter {
                it.name.lowercase(Locale.ROOT).startsWith(hotelName)
            }
        }
    }

    fun setUpRepository(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            allHotels.addAll(dataRepository.provideBestHotels(context))
            allHotels.addAll(dataRepository.provideTrendingHotels(context))
            allHotels.addAll(dataRepository.providePopularHotels(context))
        }
    }

}