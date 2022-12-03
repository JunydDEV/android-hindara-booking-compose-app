package com.android.hindara.booking.app.ui.booking.dateselection

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.ui.booking.BookingSharedViewModel
import com.android.hindara.booking.app.ui.home.HomeViewModel
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

const val calendarBottomSheetRoute = "calendar_bottom_sheet_route"

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.calendarBottomSheetNavGraph(
    navController: NavController,
    bookingSharedViewModel: BookingSharedViewModel,
    homeViewModel: HomeViewModel
) {
    bottomSheet(calendarBottomSheetRoute) {
        DateSelectionBottomSheet(
            navController = navController,
            viewModel = bookingSharedViewModel,
            homeViewModel = homeViewModel
        )
    }
}