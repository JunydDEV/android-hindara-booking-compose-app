package com.core.data

import android.content.Context
import android.util.LayoutDirection
import com.core.model.hotel_details.Hotel
import com.google.gson.Gson

class DataRepositoryImpl: DataRepository {

    private val gson by lazy { Gson() }

    override suspend fun provideBestHotels(context: Context): List<Hotel> {
        val fileName = getBestHotelsFilename(context)
        val result = readJsonFileFromAssets(context, fileName)

        return gson.fromJson(result, Array<Hotel>::class.java).toList()
    }

    override suspend fun provideTrendingHotels(context: Context): List<Hotel> {
        val fileName = getTrendingFilename(context)
        val result = readJsonFileFromAssets(context, fileName)

        return gson.fromJson(result, Array<Hotel>::class.java).toList()
    }

    override suspend fun providePopularHotels(context: Context): List<Hotel> {
        val fileName = getBestPopularFilename(context)
        val result = readJsonFileFromAssets(context, fileName)

        return gson.fromJson(result, Array<Hotel>::class.java).toList()
    }

    override suspend fun provideMyBookmarkedHotels(context: Context): List<Hotel> {
        val fileName = getMyBookmarksFilename(context)
        val result = readJsonFileFromAssets(context, fileName)

        return gson.fromJson(result, Array<Hotel>::class.java).toList()
    }

    override suspend fun provideMyBookedHotels(context: Context): List<Hotel> {
        val fileName = getMyBookingsFilename(context)
        val result = readJsonFileFromAssets(context, fileName)

        return gson.fromJson(result, Array<Hotel>::class.java).toList()
    }

    private fun readJsonFileFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName)
            .bufferedReader()
            .use { it.readText() }
    }


    private fun getBestHotelsFilename(context: Context) = if (isRtl(context)) {
        "best_hotels_listing_ar.json"
    } else {
        "best_hotels_listing.json"
    }

    private fun getTrendingFilename(context: Context) = if (isRtl(context)) {
        "trending_hotels_listing_ar.json"
    } else {
        "trending_hotels_listing.json"
    }

    private fun getBestPopularFilename(context: Context) = if (isRtl(context)) {
        "popular_hotels_listing_ar.json"
    } else {
        "popular_hotels_listing.json"
    }

    private fun getMyBookmarksFilename(context: Context) = if (isRtl(context)) {
        "my_bookmarks_hotels_listing_ar.json"
    } else {
        "my_bookmarks_hotels_listing.json"
    }

    private fun getMyBookingsFilename(context: Context) = if (isRtl(context)) {
        "my_bookmarks_hotels_listing_ar.json"
    } else {
        "my_bookmarks_hotels_listing.json"
    }

    private fun isRtl(context: Context) =
        context.resources.configuration.layoutDirection == LayoutDirection.RTL

}