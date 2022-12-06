package com.android.hindara.booking.app.ui.authentication.login.bottomsheets.emailverification

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet

const val emailVerificationBottomSheetRoute = "email_verification_bottom_sheet_route"

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.emailVerificationBottomSheetNavGraph(navController: NavController) {
    bottomSheet(emailVerificationBottomSheetRoute) {
        EmailVerificationBottomSheet(navController = navController)
    }
}