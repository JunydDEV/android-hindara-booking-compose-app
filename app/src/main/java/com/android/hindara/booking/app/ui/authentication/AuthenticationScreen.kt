package com.android.hindara.booking.app.ui.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.authentication.login.*
import com.android.hindara.booking.app.ui.authentication.signup.SignupScreen
import com.android.hindara.booking.app.ui.theme.*
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

/**
 * Container screen hosting login and signup.
 *
 * @param navController helps in navigation to other screen.
 * @param viewModel establishes communication between UI & data component.
 * */
@Composable
fun AuthenticationScreen(
    viewModel: AuthenticationViewModel = hiltViewModel(),
    navController: NavController
) {
    MainScreenContentComposable(navController)
}

@Composable
private fun MainScreenContentComposable(navController: NavController) {
    val mainColumnModifier = Modifier
        .background(ScreenBackgroundColor)
        .fillMaxSize()
    Column(
        modifier = mainColumnModifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpacerComposable()
        AppLogoComposable()
        SpacerComposable()
        SwipePagerView(navController)
    }
}

@Composable
private fun SpacerComposable() {
    val topSpacerModifier = Modifier
        .fillMaxWidth()
        .height(dimensionResource(id = R.dimen.extra_large_spacing))
    Spacer(modifier = topSpacerModifier)
}

@Composable
private fun AppLogoComposable() {
    Image(
        painter = painterResource(id = R.drawable.ic_hindara),
        contentDescription = stringResource(R.string.image_app_logo)
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SwipePagerView(navController: NavController) {
    Column {
        val tabIndexState = remember { mutableStateOf(0) }
        val tabTitles = listOf(
            stringResource(R.string.tab_login_label),
            stringResource(R.string.tab_signup_label)
        )
        val pagerState = rememberPagerState()

        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                tabIndexState.value = page
            }
        }

        TabRowComposable(tabIndexState, pagerState, tabTitles)
        HorizontalPagerComposable(
            navController = navController,
            tabTitles = tabTitles,
            pagerState = pagerState
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TabRowComposable(
    tabPosition: MutableState<Int>,
    pagerState: PagerState,
    tabTitles: List<String>
) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = tabPosition.value,
        backgroundColor = TabBackgroundColor,
        indicator = { tabPositions ->
            TabIndicatorComposable(pagerState, tabPositions)
        }
    ) {
        tabTitles.forEachIndexed { index, title ->
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
                text = { Text(text = title) },
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
    navController: NavController,
    tabTitles: List<String>,
    pagerState: PagerState) {
    val loginPage = 0
    val signupPage = 1

    HorizontalPager(
        count = tabTitles.size,
        state = pagerState,
    ) { index ->
        if (index == loginPage) {
            LoginScreen(navController = navController)
        } else if (index == signupPage) {
            SignupScreen(navController = navController)
        }
    }
}
