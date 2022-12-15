package com.android.hindara.booking.app.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import com.core.data.DataRepositoryImpl
import com.core.model.hotel_details.FeaturedCategory
import com.core.model.hotel_details.Hotel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {

    private val dataRepository = DataRepositoryImpl()
    lateinit var selectedHotel: Hotel

    fun onHotelSelect(hotel: Hotel) {
        selectedHotel = hotel
    }

    fun getFeaturedCategories(context: Context): MutableStateFlow<MutableList<FeaturedCategory>> {
        val list = mutableListOf<FeaturedCategory>()
        val stateFlow = MutableStateFlow(list)
        CoroutineScope(Dispatchers.IO).launch {
            list.add(
                FeaturedCategory(
                    categoryName = R.string.tab_recommended_label,
                    hotelsList = dataRepository.provideBestHotels(context)
                )
            )
            list.add(
                FeaturedCategory(
                    categoryName = R.string.tab_trending_label,
                    hotelsList = dataRepository.provideTrendingHotels(context)
                )
            )

            list.add(
                FeaturedCategory(
                    categoryName = R.string.tab_popular_label,
                    hotelsList = dataRepository.providePopularHotels(context)
                )
            )
            stateFlow.value = list
        }
        return stateFlow
    }
}