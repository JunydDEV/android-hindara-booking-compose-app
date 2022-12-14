package com.android.hindara.booking.app.ui.home.pager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.android.hindara.booking.app.ui.home.HomeViewModel
import com.android.hindara.booking.app.ui.theme.*
import com.android.hindara.booking.app.utils.isRtlLayout
import com.core.model.hotel_details.FeaturedCategory
import com.core.model.hotel_details.Hotel
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeaturedOnHomeScreenListing(
    viewModel: HomeViewModel,
    navController: NavController,
    onHotelSelect: (Hotel) -> Unit
) {
    val categories = viewModel.getFeaturedCategories(LocalContext.current).collectAsState()
    if(categories.value.isEmpty())
        return

    Column(Modifier.fillMaxSize()) {
        val tabIndexState = remember { mutableStateOf(0) }
        val pagerState = rememberPagerState()
        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                tabIndexState.value = page
            }
        }

        TabRowComposable(
            tabPosition = tabIndexState.value,
            onTabPositionChange = { tabIndexState.value = it },
            pagerState = pagerState,
            tabTitles = categories.value
        )
        HorizontalPagerComposable(
            navController = navController,
            list = categories.value,
            pagerState = pagerState,
            onHotelSelect = onHotelSelect
        )
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TabRowComposable(
    tabPosition: Int,
    onTabPositionChange: (Int) -> Unit,
    pagerState: PagerState,
    tabTitles: List<FeaturedCategory>
) {
    val coroutineScope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = tabPosition,
        backgroundColor = MaterialTheme.colors.background,
        divider = {
            TabRowDefaults.Divider(color = Color.Transparent)
        },
        indicator = { tabPositions ->
            val rtlTabsPosition =
                if (isRtlLayout()) {
                    tabPositions.reversed()
                } else {
                    tabPositions
                }
            TabIndicatorComposable(pagerState, rtlTabsPosition)
        }
    ) {
        tabTitles.forEachIndexed { index, category ->
            Tab(
                selected = tabPosition == index,
                onClick = {
                    onTabPositionChange(index)
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    Text(
                        text = stringResource(id = category.categoryName),
                    )
                },
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TabIndicatorComposable(pagerState: PagerState, tabPositions: List<TabPosition>) {
    TabRowDefaults.Indicator(
        modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
        color = tab_indicator_color
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HorizontalPagerComposable(
    navController: NavController,
    list: List<FeaturedCategory>,
    pagerState: PagerState,
    onHotelSelect: (Hotel) -> Unit,
) {

    HorizontalPager(
        count = list.size,
        state = pagerState,
    ) { index ->
        FeaturedHotelsPageScreen(
            navController = navController,
            category = list[index],
            onHotelSelect = onHotelSelect
        )
    }
}
