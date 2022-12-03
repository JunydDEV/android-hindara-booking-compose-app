package com.android.hindara.booking.app.ui.common.bottomsheets.jobflowresult

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

const val resultBottomSheetRoute = "result_bottom_sheet_route/{type}"

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.resultBottomSheetNavGraph(navController: NavController) {
    bottomSheet(resultBottomSheetRoute) {
        ResultBottomSheet(navController = navController, type = it.arguments?.getString("type")!!)
    }
}