package com.android.hindara.booking.app.ui.search.filter

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

const val filterBottomSheetRoute = "filter_bottom_sheet_route"

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.filterBottomSheetNavGraph(navController: NavController) {
    bottomSheet(filterBottomSheetRoute) {
        FilterBottomSheetScreen(navController = navController)
    }
}