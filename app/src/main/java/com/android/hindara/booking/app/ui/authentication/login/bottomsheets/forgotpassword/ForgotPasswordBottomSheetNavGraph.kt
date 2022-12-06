package com.android.hindara.booking.app.ui.authentication.login.bottomsheets.forgotpassword

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

const val forgotPasswordBottomSheetRoute = "forgot_password_bottom_sheet_route"

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.forgotPasswordBottomSheetNavGraph(navController: NavController) {
    bottomSheet(forgotPasswordBottomSheetRoute) {
        ForgotPasswordBottomSheet(navController = navController)
    }
}