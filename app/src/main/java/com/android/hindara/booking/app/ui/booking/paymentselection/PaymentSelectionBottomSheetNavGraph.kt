package com.android.hindara.booking.app.ui.booking.paymentselection

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.ui.booking.BookingSharedViewModel
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

const val paymentSelectionBottomSheetRoute = "payment_selection_bottom_sheet_route"

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.paymentSelectionBottomSheetNavGraph(
    navController: NavController,
    bookingSharedViewModel: BookingSharedViewModel
) {
    bottomSheet(paymentSelectionBottomSheetRoute) {
        PaymentMethodsBottomSheet(navController = navController, viewModel = bookingSharedViewModel)
    }
}