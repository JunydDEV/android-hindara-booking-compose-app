package com.core.data

import android.content.Context
import com.core.model.hotel_details.Hotel

interface DataRepository {

    suspend fun provideBestHotels(context: Context): List<Hotel>

    suspend fun provideTrendingHotels(context: Context): List<Hotel>

    suspend fun providePopularHotels(context: Context): List<Hotel>

    suspend fun provideMyBookmarkedHotels(context: Context): List<Hotel>

    suspend fun provideMyBookedHotels(context: Context): List<Hotel>
}