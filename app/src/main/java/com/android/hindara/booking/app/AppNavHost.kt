package com.android.hindara.booking.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.android.hindara.booking.app.ui.appmenu.appMenuGraph
import com.android.hindara.booking.app.ui.appmenu.mybookings.MyBookingsViewModel
import com.android.hindara.booking.app.ui.appmenu.mybookings.bottomsheets.details.bookingDetailsBottomSheetGraph
import com.android.hindara.booking.app.ui.appmenu.mybookings.myBookingsGraph
import com.android.hindara.booking.app.ui.appmenu.bookmarks.bookmarksGraph
import com.android.hindara.booking.app.ui.appmenu.settings.settingsGraph
import com.android.hindara.booking.app.ui.authentication.authenticationGraph
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.emailverification.emailVerificationBottomSheetNavGraph
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.forgotpassword.forgotPasswordBottomSheetNavGraph
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.resetpassword.resetPasswordNavGraph
import com.android.hindara.booking.app.ui.booking.BookingSharedViewModel
import com.android.hindara.booking.app.ui.booking.dateselection.calendarBottomSheetNavGraph
import com.android.hindara.booking.app.ui.booking.paymentconfirmation.paymentConfirmationBottomSheetNavGraph
import com.android.hindara.booking.app.ui.booking.paymentselection.paymentSelectionBottomSheetNavGraph
import com.android.hindara.booking.app.ui.common.BottomSheetLayoutConfig
import com.android.hindara.booking.app.ui.common.bottomsheets.alertbottomsheet.alertBottomSheetNavGraph
import com.android.hindara.booking.app.ui.description.moreDescriptionGraph
import com.android.hindara.booking.app.ui.home.HomeViewModel
import com.android.hindara.booking.app.ui.home.Hotel
import com.android.hindara.booking.app.ui.home.homeNavGraph
import com.android.hindara.booking.app.ui.hoteldetails.hotelDetailsGraph
import com.android.hindara.booking.app.ui.hoteldetails.hotelDetailsRoute
import com.android.hindara.booking.app.ui.onboarding.onboardingGraph
import com.android.hindara.booking.app.ui.onboarding.onboardingRoute
import com.android.hindara.booking.app.ui.reviews.reviewsGraph
import com.android.hindara.booking.app.ui.search.filter.filterBottomSheetNavGraph
import com.android.hindara.booking.app.ui.search.searchScreenNavGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout


@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    bottomSheetNavigator: BottomSheetNavigator,
) {

    val homeViewModel: HomeViewModel = hiltViewModel()
    val myBookingsViewModel: MyBookingsViewModel = hiltViewModel()
    val bookingSharedViewModel: BookingSharedViewModel = hiltViewModel()
    val selectedHotel = remember {
        mutableStateOf<Hotel?>(null)
    }

    ModalBottomSheetLayout(
        sheetShape = BottomSheetLayoutConfig.sheetShape(),
        sheetBackgroundColor = MaterialTheme.colors.background,
        bottomSheetNavigator = bottomSheetNavigator
    ) {
        AnimatedNavHost(navController = navController, startDestination = onboardingRoute) {
            // Main Screens
            onboardingGraph(navController)
            authenticationGraph(navController)
            homeNavGraph(navController, homeViewModel, onHotelSelect = {
                selectedHotel.value = it
            })
            appMenuGraph(navController)
            hotelDetailsGraph(navController, selectedHotel)
            moreDescriptionGraph(navController, selectedHotel)
            reviewsGraph(navController, selectedHotel)
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
            calendarBottomSheetNavGraph(navController, bookingSharedViewModel, selectedHotel)
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