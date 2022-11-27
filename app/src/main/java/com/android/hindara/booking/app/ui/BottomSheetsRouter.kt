package com.android.hindara.booking.app.ui

import androidx.activity.compose.BackHandler
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.emailverification.EmailVerificationBottomSheet
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.forgotpassword.ForgotPasswordBottomSheet
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.resetpassword.ResetPasswordBottomSheet
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.resetpasswordresult.ResetPasswordResultBottomSheet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetsRouterComposable(
    navController: NavController,
    function: @Composable (MutableState<LoginBottomSheetState>, ModalBottomSheetState, CoroutineScope) -> Unit
) {
    val bottomSheetsVisibilityState = remember {
        mutableStateOf<LoginBottomSheetState>(LoginBottomSheetState.ForgotPassword)
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
        LoginBottomSheetState.ForgotPassword -> {
            ForgotPasswordBottomSheet(
                sheetState = bottomSheetState,
                loginBottomSheetState = bottomSheetsVisibilityState
            ) {
                function(bottomSheetsVisibilityState, bottomSheetState, coroutineScope)
            }
        }
        LoginBottomSheetState.ResetPassword -> {
            ResetPasswordBottomSheet(
                sheetState = bottomSheetState,
                loginBottomSheetState = bottomSheetsVisibilityState
            ) {
                function(bottomSheetsVisibilityState, bottomSheetState, coroutineScope)
            }
        }
        LoginBottomSheetState.ResetPasswordSuccess -> {
            ResetPasswordResultBottomSheet(
                sheetState = bottomSheetState,
                loginBottomSheetState = bottomSheetsVisibilityState,
                result = LoginBottomSheetState.ResetPasswordSuccess
            ) {
                function(bottomSheetsVisibilityState, bottomSheetState, coroutineScope)
            }
        }
        LoginBottomSheetState.ResetPasswordFailure -> {
            ResetPasswordResultBottomSheet(
                sheetState = bottomSheetState,
                loginBottomSheetState = bottomSheetsVisibilityState,
                result = LoginBottomSheetState.ResetPasswordFailure
            ) {
                function(bottomSheetsVisibilityState, bottomSheetState, coroutineScope)
            }
        }
        LoginBottomSheetState.VerifyEmail -> {
            EmailVerificationBottomSheet(
                sheetState = bottomSheetState,
                loginBottomSheetState = bottomSheetsVisibilityState
            ) {
                function(bottomSheetsVisibilityState, bottomSheetState, coroutineScope)
            }
        }
        else -> {
            LaunchedEffect(key1 = bottomSheetState) {
                coroutineScope.launch {
                    bottomSheetState.hide()
                }
            }
            function(bottomSheetsVisibilityState, bottomSheetState, coroutineScope)
        }
    }
}

sealed class LoginBottomSheetState {
    object ForgotPassword : LoginBottomSheetState()
    object VerifyEmail: LoginBottomSheetState()
    object ResetPassword: LoginBottomSheetState()
    object ResetPasswordSuccess: LoginBottomSheetState()
    object ResetPasswordFailure: LoginBottomSheetState()
    object ResetPasswordCompleted: LoginBottomSheetState()
}