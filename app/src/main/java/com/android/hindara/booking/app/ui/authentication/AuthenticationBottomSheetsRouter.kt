package com.android.hindara.booking.app.ui.authentication

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import com.android.hindara.booking.app.data.BottomSheetState
import com.android.hindara.booking.app.data.LoginBottomSheetState
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.emailverification.EmailVerificationBottomSheet
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.forgotpassword.ForgotPasswordBottomSheet
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.resetpassword.ResetPasswordBottomSheet
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.resetpasswordresult.ResetPasswordResultBottomSheet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AuthBottomSheetsRouter(
    bottomSheetsVisibilityState: MutableState<BottomSheetState>,
    modalBottomSheetState: ModalBottomSheetState,
    function: @Composable (MutableState<BottomSheetState>, ModalBottomSheetState, CoroutineScope) -> Unit,
    coroutineScope: CoroutineScope
) {
    when (bottomSheetsVisibilityState.value) {

        LoginBottomSheetState.ForgotPassword -> {
            ForgotPasswordBottomSheet(
                sheetState = modalBottomSheetState,
                loginBottomSheetState = bottomSheetsVisibilityState
            ) {
                function(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
            }
        }
        LoginBottomSheetState.ResetPassword -> {
            ResetPasswordBottomSheet(
                sheetState = modalBottomSheetState,
                loginBottomSheetState = bottomSheetsVisibilityState
            ) {
                function(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
            }
        }
        LoginBottomSheetState.ResetPasswordSuccess -> {
            ResetPasswordResultBottomSheet(
                sheetState = modalBottomSheetState,
                loginBottomSheetState = bottomSheetsVisibilityState,
                result = LoginBottomSheetState.ResetPasswordSuccess
            ) {
                function(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
            }
        }
        LoginBottomSheetState.ResetPasswordFailure -> {
            ResetPasswordResultBottomSheet(
                sheetState = modalBottomSheetState,
                loginBottomSheetState = bottomSheetsVisibilityState,
                result = LoginBottomSheetState.ResetPasswordFailure
            ) {
                function(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
            }
        }
        LoginBottomSheetState.VerifyEmail -> {
            EmailVerificationBottomSheet(
                sheetState = modalBottomSheetState,
                loginBottomSheetState = bottomSheetsVisibilityState
            ) {
                function(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
            }
        }
        else -> {
            LaunchedEffect(key1 = modalBottomSheetState) {
                coroutineScope.launch {
                    modalBottomSheetState.hide()
                }
            }
            function(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
        }
    }
}
