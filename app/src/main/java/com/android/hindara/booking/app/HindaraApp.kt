package com.android.hindara.booking.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.android.hindara.booking.app.ui.hoteldetails.common.rememberBottomSheetNavigator
import com.android.hindara.booking.app.ui.theme.HindaraBookingApp
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi

@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
@Composable
fun HindaraApp() {
    HindaraBookingApp {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val bottomSheetNavigator = rememberBottomSheetNavigator()
            val navController = rememberAnimatedNavController(bottomSheetNavigator)

            AppNavHost(
                navController = navController,
                bottomSheetNavigator = bottomSheetNavigator
            )
        }
    }
}