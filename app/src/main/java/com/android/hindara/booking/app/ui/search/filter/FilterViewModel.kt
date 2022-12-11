package com.android.hindara.booking.app.ui.search.filter

import androidx.lifecycle.ViewModel
import com.core.model.search.ChipInfo
import javax.inject.Inject

class FilterViewModel @Inject constructor(): ViewModel() {

    fun getLocationsChipsList(): List<ChipInfo> {
        return listOf(
            ChipInfo("Jakarta", false),
            ChipInfo("Kuala Lumpur", false),
            ChipInfo("Los Angeles", false),
        )
    }

    fun getPriceRange(): ClosedFloatingPointRange<Float> {
        return 0f..1000f
    }

    fun getFacilitiesChipsList(): List<ChipInfo> {
        return listOf(
            ChipInfo("Balcony", false),
            ChipInfo("Breakfast", false),
            ChipInfo("Swimming Pool", false),
        )
    }

}
