package com.android.hindara.booking.app

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.android.hindara.booking.app.ui.appmenu.appMenuGraph
import com.android.hindara.booking.app.ui.appmenu.mybookings.MyBookingsViewModel
import com.android.hindara.booking.app.ui.appmenu.mybookings.bottomsheets.details.bookingDetailsBottomSheetGraph
import com.android.hindara.booking.app.ui.appmenu.mybookings.myBookingsGraph
import com.android.hindara.booking.app.ui.appmenu.mybookmarks.bookmarksGraph
import com.android.hindara.booking.app.ui.appmenu.settings.settingsGraph
import com.android.hindara.booking.app.ui.authentication.authenticationGraph
import com.android.hindara.booking.app.ui.authentication.authenticationRoute
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.emailverification.emailVerificationBottomSheetNavGraph
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.forgotpassword.forgotPasswordBottomSheetNavGraph
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.resetpassword.resetPasswordNavGraph
import com.android.hindara.booking.app.ui.booking.BookingSharedViewModel
import com.android.hindara.booking.app.ui.booking.dateselection.calendarBottomSheetNavGraph
import com.android.hindara.booking.app.ui.booking.paymentconfirmation.paymentConfirmationBottomSheetNavGraph
import com.android.hindara.booking.app.ui.booking.paymentselection.paymentSelectionBottomSheetNavGraph
import com.android.hindara.booking.app.ui.common.BottomSheetLayoutConfig
import com.android.hindara.booking.app.ui.common.bottomsheets.jobflowresult.alertBottomSheetNavGraph
import com.android.hindara.booking.app.ui.description.moreDescriptionGraph
import com.android.hindara.booking.app.ui.home.HomeViewModel
import com.android.hindara.booking.app.ui.home.Hotel
import com.android.hindara.booking.app.ui.home.homeNavGraph
import com.android.hindara.booking.app.ui.home.homeRoute
import com.android.hindara.booking.app.ui.hoteldetails.hotelDetailsGraph
import com.android.hindara.booking.app.ui.hoteldetails.hotelDetailsRoute
import com.android.hindara.booking.app.ui.onboarding.onboardingGraph
import com.android.hindara.booking.app.ui.reviews.reviewsGraph
import com.android.hindara.booking.app.ui.search.filter.filterBottomSheetNavGraph
import com.android.hindara.booking.app.ui.search.searchScreenNavGraph
import com.android.hindara.booking.app.ui.theme.BottomSheetBackgroundColor
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun HindaraAppNavHost(
    navController: NavHostController,
    bottomSheetNavigator: BottomSheetNavigator,
) {

    val homeViewModel: HomeViewModel = hiltViewModel()
    val myBookingsViewModel: MyBookingsViewModel = hiltViewModel()
    val bookingSharedViewModel: BookingSharedViewModel = hiltViewModel()

    ModalBottomSheetLayout(
        sheetShape = BottomSheetLayoutConfig.sheetShape(),
        sheetBackgroundColor = BottomSheetBackgroundColor,
        bottomSheetNavigator = bottomSheetNavigator
    ) {

        NavHost(navController = navController, startDestination = authenticationRoute) {
            // Main Screens
            onboardingGraph(navController)
            authenticationGraph(navController)
            homeNavGraph(homeViewModel, navController)
            appMenuGraph(navController)
            hotelDetailsGraph(homeViewModel, navController)
            moreDescriptionGraph(navController, homeViewModel)
            reviewsGraph(navController, homeViewModel)
            bookmarksGraph(navController, onHotelSelect = {
                navigateToHotelDetailsScreen(homeViewModel, it, navController)
            })
            myBookingsGraph(navController, myBookingsViewModel)
            settingsGraph(navController)
            searchScreenNavGraph(navController, onHotelSelect = {
                navigateToHotelDetailsScreen(homeViewModel, it, navController)
            })

            // Bottom Sheet Screens
            forgotPasswordBottomSheetNavGraph(navController)
            emailVerificationBottomSheetNavGraph(navController)
            resetPasswordNavGraph(navController)
            calendarBottomSheetNavGraph(navController, bookingSharedViewModel, homeViewModel)
            paymentSelectionBottomSheetNavGraph(navController, bookingSharedViewModel)
            paymentConfirmationBottomSheetNavGraph(navController, bookingSharedViewModel)
            bookingDetailsBottomSheetGraph(navController, myBookingsViewModel)
            alertBottomSheetNavGraph(navController)
            filterBottomSheetNavGraph(navController)
        }
    }
}

private fun navigateToHotelDetailsScreen(
    homeViewModel: HomeViewModel,
    it: Hotel,
    navController: NavHostController
) {
    homeViewModel.onHotelSelect(it)
    navController.navigate(hotelDetailsRoute)
}