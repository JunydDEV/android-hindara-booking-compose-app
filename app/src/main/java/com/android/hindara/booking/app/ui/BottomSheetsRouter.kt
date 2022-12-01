package com.android.hindara.booking.app.ui

import androidx.activity.compose.BackHandler
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.android.hindara.booking.app.data.bottomsheets.BottomSheetState
import com.android.hindara.booking.app.data.bottomsheets.JobFlow
import com.android.hindara.booking.app.ui.authentication.AuthBottomSheetsRouter
import com.android.hindara.booking.app.ui.booking.BookingBottomSheetsRouter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetsRouterComposable(
    navController: NavController,
    start: BottomSheetState,
    jobFlow: JobFlow,
    function: @Composable (MutableState<BottomSheetState>, ModalBottomSheetState, CoroutineScope) -> Unit
) {
    val bottomSheetsVisibilityState = remember {
        mutableStateOf(start)
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

    when (jobFlow) {
        JobFlow.Authentication -> {
            AuthBottomSheetsRouter(
                bottomSheetsVisibilityState,
                bottomSheetState,
                function,
                coroutineScope
            )
        }
        JobFlow.Booking -> {
            BookingBottomSheetsRouter(
                bottomSheetsVisibilityState,
                bottomSheetState,
                function,
                coroutineScope
            )
        }
    }

}