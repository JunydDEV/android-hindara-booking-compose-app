package com.android.hindara.booking.app.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.composables.SearchTextFieldComposable
import com.android.hindara.booking.app.ui.home.pager.FeaturedOnHomeScreenListing
import com.android.hindara.booking.app.ui.search.searchRoute
import com.android.hindara.booking.app.ui.theme.DarkTextColor

@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel,
    modifier: Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.default_spacing))
    ) {
        TitleComposable()
        SearchComposable(navController)
        FeaturedOnHomeScreenListing(viewModel, navController)
    }
}

@Composable
private fun SearchComposable(navController: NavController) {
    val searchFieldState = remember {
        mutableStateOf("")
    }
    SearchTextFieldComposable(
        searchFieldState.value,
        readyOnly = true,
        isClickable = true,
        onValueChange = { searchFieldState.value = it },
        onClick = { navController.navigate(searchRoute) },
    )
}

@Composable
private fun TitleComposable() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(id = R.dimen.default_spacing),
                end = dimensionResource(id = R.dimen.default_spacing),
                top = dimensionResource(id = R.dimen.default_spacing)
            ),
        text = stringResource(R.string.label_welcome_message),
        style = MaterialTheme.typography.h1.copy(fontSize = 28.sp),
        color = DarkTextColor
    )
}