package com.android.hindara.booking.app.ui.home.pager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.home.FeaturedCategory
import com.android.hindara.booking.app.ui.home.HomeViewModel
import com.android.hindara.booking.app.ui.theme.*
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun FeaturedOnHomeScreenListing(viewModel: HomeViewModel, navController: NavController) {

    Column(Modifier.fillMaxSize()) {
        val tabIndexState = remember { mutableStateOf(0) }
        val categories = viewModel.getFeaturedCategories()
        val pagerState = rememberPagerState()
        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                tabIndexState.value = page
            }
        }

        TabRowComposable(tabIndexState, pagerState, categories)
        HorizontalPagerComposable(
            homeViewModel = viewModel,
            navController = navController,
            list = categories,
            pagerState = pagerState,
        )
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TabRowComposable(
    tabPosition: MutableState<Int>,
    pagerState: PagerState,
    tabTitles: List<FeaturedCategory>
) {
    val coroutineScope = rememberCoroutineScope()
    ScrollableTabRow(
        selectedTabIndex = tabPosition.value,
        backgroundColor = ScreenBackgroundColor,
        edgePadding = dimensionResource(id = R.dimen.defaultSpacing),
        divider = {
            TabRowDefaults.Divider(color = Color.Transparent)
        },
        indicator = { tabPositions ->
            TabIndicatorComposable(pagerState, tabPositions)
        }
    ) {
        tabTitles.forEachIndexed { index, category ->
            Tab(
                selected = tabPosition.value == index,
                selectedContentColor = SelectedContentContentColor,
                unselectedContentColor = UnSelectedTabContentColor,
                onClick = {
                    tabPosition.value = index
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    Text(
                        text = category.categoryName,
                        style = MaterialTheme.typography.body1
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
        color = TabIndicatorColor
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HorizontalPagerComposable(
    homeViewModel: HomeViewModel,
    navController: NavController,
    list: List<FeaturedCategory>,
    pagerState: PagerState,
) {

    HorizontalPager(
        count = list.size,
        state = pagerState,
    ) { index ->
        FeaturedHotelsPageScreen(homeViewModel, navController, list[index])
    }
}
