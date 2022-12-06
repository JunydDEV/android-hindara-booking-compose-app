package com.android.hindara.booking.app.ui.authentication.login.bottomsheets.resetpassword

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

const val resetPasswordBottomSheetRoute = "reset_password_bottom_sheet_route"

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.resetPasswordNavGraph(navController: NavController) {
    bottomSheet(resetPasswordBottomSheetRoute) {
        ResetPasswordBottomSheet(navController = navController)
    }
}