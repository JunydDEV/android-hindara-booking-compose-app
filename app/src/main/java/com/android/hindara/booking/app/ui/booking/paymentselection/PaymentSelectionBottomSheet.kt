package com.android.hindara.booking.app.ui.booking.paymentselection

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PaymentSelectionBottomSheet(
    viewModel: PaymentSelectionViewModel = hiltViewModel(),
    sheetState: ModalBottomSheetState,
    function: @Composable () -> Unit
) {

}