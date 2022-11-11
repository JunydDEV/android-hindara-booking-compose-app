package com.android.hindara.booking.app.ui.authentication.login

import androidx.activity.compose.BackHandler
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.android.hindara.booking.app.ui.authentication.login.emailverification.EmailVerificationBottomSheet
import com.android.hindara.booking.app.ui.authentication.login.forgotpassword.ForgotPasswordBottomSheet
import com.android.hindara.booking.app.ui.authentication.login.resetpassword.ResentBottomSheet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetsRouterComposable(
    navController: NavController,
    function: @Composable (MutableState<LoginBottomSheet>, ModalBottomSheetState, CoroutineScope) -> Unit
) {
    val bottomSheetsVisibilityState = remember {
        mutableStateOf<LoginBottomSheet>(LoginBottomSheet.ForgotPassword)
    }
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.Expanded }
    )
    val coroutineScope = rememberCoroutineScope()

    BackHandler(bottomSheetState.isVisible) {
        coroutineScope.launch { bottomSheetState.hide() }
    }

    LaunchedEffect(key1 = coroutineScope) {
        coroutineScope.launch { bottomSheetState.hide() }
    }

    when (bottomSheetsVisibilityState.value) {
        LoginBottomSheet.ForgotPassword -> {
            ForgotPasswordBottomSheet(
                sheetState = bottomSheetState,
                loginBottomSheetState = bottomSheetsVisibilityState
            ) {
                function(bottomSheetsVisibilityState, bottomSheetState, coroutineScope)
            }
        }
        LoginBottomSheet.ResetPassword -> {
            ResentBottomSheet(
                sheetState = bottomSheetState,
                loginBottomSheetState = bottomSheetsVisibilityState
            ) {
                function(bottomSheetsVisibilityState, bottomSheetState, coroutineScope)
            }
        }
        else -> {
            EmailVerificationBottomSheet(
                sheetState = bottomSheetState,
                loginBottomSheetState = bottomSheetsVisibilityState
            ) {
                function(bottomSheetsVisibilityState, bottomSheetState, coroutineScope)
            }
        }
    }
}


