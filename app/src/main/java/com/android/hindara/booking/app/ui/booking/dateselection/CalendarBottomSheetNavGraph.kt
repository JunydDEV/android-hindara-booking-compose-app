package com.android.hindara.booking.app.ui.booking.dateselection

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.ui.booking.BookingSharedViewModel
import com.android.hindara.booking.app.ui.home.HomeViewModel
import com.android.hindara.booking.app.ui.home.Hotel
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

const val calendarBottomSheetRoute = "calendar_bottom_sheet_route"

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.calendarBottomSheetNavGraph(
    navController: NavController,
    bookingSharedViewModel: BookingSharedViewModel,
    selectedHotel: MutableState<Hotel?>
) {
    bottomSheet(calendarBottomSheetRoute) {
        DateSelectionBottomSheet(
            navController = navController,
            viewModel = bookingSharedViewModel,
            hotel = selectedHotel.value!!
        )
    }
}