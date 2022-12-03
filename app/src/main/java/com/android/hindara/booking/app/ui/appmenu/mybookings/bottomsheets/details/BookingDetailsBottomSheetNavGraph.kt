package com.android.hindara.booking.app.ui.appmenu.mybookings.bottomsheets.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.ui.appmenu.mybookings.MyBookingsViewModel
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

const val bookingDetailsBottomSheetRoute = "booking_details_bottom_sheet_route"

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.bookingDetailsBottomSheetGraph(
    navController: NavController,
    myBookingsViewModel: MyBookingsViewModel
){
    bottomSheet(route = bookingDetailsBottomSheetRoute) {
        BookingDetailsBottomSheet(
            navController = navController,
            myBookingsViewModel = myBookingsViewModel
        )
    }
}