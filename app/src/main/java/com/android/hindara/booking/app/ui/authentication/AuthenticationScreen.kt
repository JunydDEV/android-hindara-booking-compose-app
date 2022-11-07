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
import com.android.hindara.booking.app.ui.authentication.login.LoginScreen
import com.android.hindara.booking.app.ui.authentication.signup.SignupScreen
import com.android.hindara.booking.app.ui.theme.PrimaryColor
import com.android.hindara.booking.app.ui.theme.ScreenBackgroundColor
import com.android.hindara.booking.app.ui.theme.WhiteColor
import com.google.accompanist.pager.*

@Composable
fun AuthenticationScreen(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel = hiltViewModel()
) {
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
        .height(dimensionResource(id = R.dimen.defaultSpacing))
    Spacer(modifier = topSpacerModifier)
}

@Composable
private fun AppLogoComposable() {
    Image(
        painter = painterResource(id = R.drawable.ic_hindara),
        contentDescription = stringResource(R.string.hindara_logo_description)
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SwipePagerView(navController: NavController) {
    Column {
        val tabIndex by remember { mutableStateOf(0) }
        val tabTitles = listOf(
            stringResource(R.string.tab_title_login),
            stringResource(R.string.tab_title_signup)
        )
        val pagerState = rememberPagerState()

        TabRowComposable(tabIndex, pagerState, tabTitles)
        HorizontalPagerComposable(navController,tabTitles, pagerState)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TabRowComposable(
    tabIndex: Int,
    pagerState: PagerState,
    tabTitles: List<String>
) {
    var tabPosition = tabIndex
    TabRow(
        selectedTabIndex = tabPosition,
        backgroundColor = WhiteColor,
        indicator = { tabPositions ->
            TabIndicatorComposable(pagerState, tabPositions)
        }
    ) {
        tabTitles.forEachIndexed { index, title ->
            Tab(
                selected = tabPosition == index,
                onClick = { tabPosition = index },
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
        color = PrimaryColor
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HorizontalPagerComposable(
    navController: NavController,
    tabTitles: List<String>,
    pagerState: PagerState
) {
    HorizontalPager(
        count = tabTitles.size,
        state = pagerState,
    ) { index ->
        if (index == 0) {
            LoginScreen(navController)
        } else {
            SignupScreen(navController)
        }
    }
}
