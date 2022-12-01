package com.android.hindara.booking.app.ui.authentication

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import com.android.hindara.booking.app.ui.common.bottomsheets.states.BottomSheetState
import com.android.hindara.booking.app.ui.common.bottomsheets.states.LoginBottomSheetState
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.emailverification.EmailVerificationBottomSheet
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.forgotpassword.ForgotPasswordBottomSheet
import com.android.hindara.booking.app.ui.authentication.login.bottomsheets.resetpassword.ResetPasswordBottomSheet
import com.android.hindara.booking.app.ui.common.bottomsheets.jobflowresult.JobFlowResultBottomSheet
import com.android.hindara.booking.app.ui.common.bottomsheets.states.JobFlowResultState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AuthBottomSheetsRouter(
    bottomSheetsVisibilityState: MutableState<BottomSheetState>,
    modalBottomSheetState: ModalBottomSheetState,
    mainScreenContent: @Composable (MutableState<BottomSheetState>, ModalBottomSheetState, CoroutineScope) -> Unit,
    coroutineScope: CoroutineScope
) {
    when (bottomSheetsVisibilityState.value) {
        LoginBottomSheetState.ForgotPassword -> {
            ForgotPasswordBottomSheet(
                sheetState = modalBottomSheetState,
                loginBottomSheetState = bottomSheetsVisibilityState
            ) {
                mainScreenContent(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
            }
        }
        LoginBottomSheetState.ResetPassword -> {
            ResetPasswordBottomSheet(
                sheetState = modalBottomSheetState,
                loginBottomSheetState = bottomSheetsVisibilityState
            ) {
                mainScreenContent(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
            }
        }
        JobFlowResultState.ResetPasswordSuccess -> {
            JobFlowResultBottomSheet(
                modelBottomSheetState = modalBottomSheetState,
                bottomSheetState = bottomSheetsVisibilityState,
                resultState = JobFlowResultState.ResetPasswordSuccess
            ) {
                mainScreenContent(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
            }
        }
        JobFlowResultState.ResetPasswordFailure -> {
            JobFlowResultBottomSheet(
                modelBottomSheetState = modalBottomSheetState,
                bottomSheetState = bottomSheetsVisibilityState,
                resultState = JobFlowResultState.ResetPasswordFailure
            ) {
                mainScreenContent(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
            }
        }
        LoginBottomSheetState.VerifyEmail -> {
            EmailVerificationBottomSheet(
                sheetState = modalBottomSheetState,
                loginBottomSheetState = bottomSheetsVisibilityState
            ) {
                mainScreenContent(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
            }
        }
        else -> {
            LaunchedEffect(key1 = modalBottomSheetState) {
                coroutineScope.launch {
                    modalBottomSheetState.hide()
                }
            }
            mainScreenContent(bottomSheetsVisibilityState, modalBottomSheetState, coroutineScope)
        }
    }
}
