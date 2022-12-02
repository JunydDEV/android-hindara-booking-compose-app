package com.android.hindara.booking.app

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.android.hindara.booking.app.ui.appmenu.appMenuGraph
import com.android.hindara.booking.app.ui.appmenu.mybookings.myBookingsGraph
import com.android.hindara.booking.app.ui.authentication.authenticationGraph
import com.android.hindara.booking.app.ui.appmenu.mybookmarks.bookmarksGraph
import com.android.hindara.booking.app.ui.description.moreDescriptionGraph
import com.android.hindara.booking.app.ui.home.HomeViewModel
import com.android.hindara.booking.app.ui.home.homeNavGraph
import com.android.hindara.booking.app.ui.hoteldetails.hotelDetailsGraph
import com.android.hindara.booking.app.ui.onboarding.onboardingGraph
import com.android.hindara.booking.app.ui.onboarding.onboardingRoute
import com.android.hindara.booking.app.ui.reviews.reviewsGraph

@Composable
fun HindaraAppNavHost(navController: NavHostController) {
    val homeViewModel: HomeViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = onboardingRoute) {
        onboardingGraph(navController)
        authenticationGraph(navController)
        homeNavGraph(homeViewModel, navController)
        appMenuGraph(navController)
        hotelDetailsGraph(homeViewModel, navController)
        moreDescriptionGraph(navController, homeViewModel)
        reviewsGraph(navController, homeViewModel)
        bookmarksGraph(navController)
        myBookingsGraph(navController)
    }
}