package com.android.hindara.booking.app.ui.common.bottomsheets

import androidx.activity.compose.BackHandler
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.ui.common.bottomsheets.states.BottomSheetState
import com.android.hindara.booking.app.ui.common.bottomsheets.states.JobFlow
import com.android.hindara.booking.app.ui.authentication.AuthBottomSheetsRouter
import com.android.hindara.booking.app.ui.booking.BookingBottomSheetsRouter
import com.android.hindara.booking.app.ui.booking.BookingSharedViewModel
import com.android.hindara.booking.app.ui.home.Hotel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetsRouterComposable(
    navController: NavController,
    hotel: Hotel? = null,
    startBottomSheet: BottomSheetState,
    jobFlow: JobFlow,
    mainScreenContent: @Composable (MutableState<BottomSheetState>, ModalBottomSheetState, CoroutineScope) -> Unit
) {
    val bottomSheetsVisibilityState = remember {
        mutableStateOf(startBottomSheet)
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
                mainScreenContent,
                coroutineScope
            )
        }
        JobFlow.Booking -> {
            val bookingSharedViewModel: BookingSharedViewModel = hiltViewModel()
            bookingSharedViewModel.chosenHotel = hotel!!

            BookingBottomSheetsRouter(
                bookingSharedViewModel,
                bottomSheetsVisibilityState,
                bottomSheetState,
                mainScreenContent,
                coroutineScope
            )
        }
    }

}