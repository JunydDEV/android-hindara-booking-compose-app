package com.android.hindara.booking.app.ui.appmenu.bookmarks

import android.content.Context
import androidx.lifecycle.ViewModel
import com.core.data.DataRepositoryImpl
import com.core.model.hotel_details.Hotel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookmarksViewModel @Inject constructor() : ViewModel() {

    private val dataRepository = DataRepositoryImpl()

    fun getBookmarkedHotelsList(context: Context): StateFlow<List<Hotel>> {
        val list = mutableListOf<Hotel>()
        val stateFlow = MutableStateFlow(list)
        CoroutineScope(Dispatchers.IO).launch {
            list.addAll(dataRepository.provideMyBookmarkedHotels(context))
            stateFlow.value = list
        }
        return stateFlow
    }
}