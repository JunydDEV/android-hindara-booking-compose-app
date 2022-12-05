package com.android.hindara.booking.app.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.common.composables.AppBarMenuItem
import com.android.hindara.booking.app.ui.common.composables.AppTopBar
import com.android.hindara.booking.app.ui.common.composables.SearchTextFieldComposable
import com.android.hindara.booking.app.ui.home.Hotel
import com.android.hindara.booking.app.ui.theme.LightTextColor
import com.android.hindara.booking.app.ui.theme.ScreenBackgroundColor
import com.android.hindara.booking.app.ui.theme.WhiteColor
import com.android.hindara.booking.app.ui.theme.YellowColor

const val COLUMNS_COUNT = 2

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController,
) {
    val searchResultsState = viewModel.searchResultsLiveData.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = ScreenBackgroundColor,
        topBar = {
            AppTopBar(
                navController = navController,
                title = stringResource(R.string.title_search_hotels),
                menuItemToShow = AppBarMenuItem.FILTER,
                menuItemClick = {

                }
            )
        }
    ) {
        val modifier = Modifier
            .padding(it)
            .padding(top = dimensionResource(id = R.dimen.defaultSpacing))
        Column(modifier) {
            SearchComposable(viewModel)
            SearchResultsGridComposable(hotels = searchResultsState.value)
        }
    }
}

@Composable
private fun SearchComposable(viewModel: SearchViewModel) {
    val searchFieldState = remember {
        mutableStateOf("")
    }
    SearchTextFieldComposable(
        value = searchFieldState.value,
        onValueChange = {
            searchFieldState.value = it
        }
    )
    viewModel.searchHotels(searchFieldState.value)
}

@Composable
fun SearchResultsGridComposable(hotels: List<Hotel>?) {
    if(hotels == null)
        return

    LazyVerticalGrid(
        columns = GridCells.Fixed(COLUMNS_COUNT),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.smallSpacing))
    ) {
        items(hotels.size) {
            HotelCard(hotels[it])
        }
    }
}

@Composable
fun HotelCard(hotel: Hotel) {
    val modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight()
        .padding(dimensionResource(id = R.dimen.smallSpacing))

    Card(
        modifier = modifier,
        backgroundColor = WhiteColor,
        elevation = 0.dp,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.cardCornersSize))
    ) {
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.smallSpacing))) {
            val hotelImageModifier = Modifier
                .height(dimensionResource(id = R.dimen.searchHotelImageHeight))
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.cardCornersSize)))
            Image(
                modifier = hotelImageModifier,
                painter = painterResource(id = hotel.image),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.hotel_description)
            )

            Text(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.smallSpacing)),
                text = hotel.name,
                style = MaterialTheme.typography.body1
            )

            Text(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.smallSpacing)),
                text = hotel.address.locationTitle,
                style = MaterialTheme.typography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = LightTextColor
            )

            Row(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.smallSpacing)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val startImageModifier = Modifier.size(
                    width = dimensionResource(id = R.dimen.ratingStartSize),
                    height = dimensionResource(id = R.dimen.ratingStartSize)
                )
                Image(
                    modifier = startImageModifier,
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = stringResource(R.string.ratring_start_description)
                )
                Text(
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.smallSpacing)),
                    text = hotel.rating.toString(),
                    style = MaterialTheme.typography.body2,
                    color = YellowColor
                )
            }
        }
    }
}