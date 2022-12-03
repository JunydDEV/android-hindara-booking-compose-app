package com.android.hindara.booking.app.ui.booking.paymentconfirmation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.ui.booking.BookingSharedViewModel
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

const val paymentConfirmationBottomSheetRoute = "payment_confirmation_bottom_sheet_route"

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.paymentConfirmationBottomSheetNavGraph(
    navController: NavController,
    bookingSharedViewModel: BookingSharedViewModel
) {
    bottomSheet(paymentConfirmationBottomSheetRoute) {
        PaymentConfirmationBottomSheet(navController = navController, viewModel = bookingSharedViewModel)
    }
}