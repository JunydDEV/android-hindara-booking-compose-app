package com.android.hindara.booking.app

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.SwipeableDefaults
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.plusAssign
import com.android.hindara.booking.app.ui.appmenu.appMenuGraph
import com.android.hindara.booking.app.ui.appmenu.mybookings.MyBookingsViewModel
import com.android.hindara.booking.app.ui.appmenu.mybookings.bottomsheets.details.bookingDetailsBottomSheetGraph
import com.android.hindara.booking.app.ui.appmenu.mybookings.myBookingsGraph
import com.android.hindara.booking.app.ui.appmenu.mybookmarks.bookmarksGraph
import com.android.hindara.booking.app.ui.authentication.authenticationGraph
import com.android.hindara.booking.app.ui.common.BottomSheetLayoutConfig
import com.android.hindara.booking.app.ui.description.moreDescriptionGraph
import com.android.hindara.booking.app.ui.home.HomeViewModel
import com.android.hindara.booking.app.ui.home.homeNavGraph
import com.android.hindara.booking.app.ui.hoteldetails.hotelDetailsGraph
import com.android.hindara.booking.app.ui.onboarding.onboardingGraph
import com.android.hindara.booking.app.ui.onboarding.onboardingRoute
import com.android.hindara.booking.app.ui.reviews.reviewsGraph
import com.android.hindara.booking.app.ui.theme.BottomSheetBackgroundColor
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import kotlinx.coroutines.InternalCoroutinesApi


@ExperimentalMaterialNavigationApi
@Composable
fun HindaraAppNavHost(
    navController: NavHostController,
    bottomSheetNavigator: BottomSheetNavigator) {

    val homeViewModel: HomeViewModel = hiltViewModel()
    val myBookingsViewModel: MyBookingsViewModel = hiltViewModel()

    ModalBottomSheetLayout(
        sheetShape = BottomSheetLayoutConfig.sheetShape(),
        sheetBackgroundColor = BottomSheetBackgroundColor,
        bottomSheetNavigator = bottomSheetNavigator
    ) {

        NavHost(navController = navController, startDestination = onboardingRoute) {
            // Main Screens
            onboardingGraph(navController)
            authenticationGraph(navController)
            homeNavGraph(homeViewModel, navController)
            appMenuGraph(navController)
            hotelDetailsGraph(homeViewModel, navController)
            moreDescriptionGraph(navController, homeViewModel)
            reviewsGraph(navController, homeViewModel)
            bookmarksGraph(navController)
            myBookingsGraph(navController, myBookingsViewModel)

            // Bottom Sheet Screens
            bookingDetailsBottomSheetGraph(navController, myBookingsViewModel)
        }
    }
}