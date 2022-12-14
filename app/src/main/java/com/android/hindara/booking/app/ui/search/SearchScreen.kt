package com.android.hindara.booking.app.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
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
import com.android.hindara.booking.app.ui.search.filter.filterBottomSheetRoute
import com.android.hindara.booking.app.ui.theme.yellow_color
import com.android.hindara.booking.app.utils.mapImageToDrawable
import com.core.model.hotel_details.Hotel

const val COLUMNS_COUNT = 2

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController,
    onHotelSelect: (Hotel) -> Unit
) {
    viewModel.setUpRepository(LocalContext.current)
    val searchResultsState = viewModel.searchResultsLiveData.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            AppTopBar(
                navController = navController,
                title = stringResource(R.string.label_title_search),
                menuItem = AppBarMenuItem.FILTER,
                menuItemClick = {
                    navController.navigate(filterBottomSheetRoute)
                }
            )
        }
    ) {
        val modifier = Modifier
            .padding(it)
            .padding(
                top = dimensionResource(id = R.dimen.default_spacing),
                start = dimensionResource(id = R.dimen.default_spacing),
                end = dimensionResource(id = R.dimen.default_spacing)
            )
        Column(modifier) {
            SearchComposable(viewModel)
            SearchResultsGridComposable(
                hotels = searchResultsState.value,
                onHotelSelect = onHotelSelect
            )
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
fun SearchResultsGridComposable(hotels: List<Hotel>?, onHotelSelect: (Hotel) -> Unit) {
    if (hotels == null)
        return

    LazyVerticalGrid(
        modifier = Modifier.padding(
            top = dimensionResource(id = R.dimen.default_spacing),
            bottom = dimensionResource(id = R.dimen.default_spacing)
        ),
        columns = GridCells.Fixed(COLUMNS_COUNT),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.default_spacing)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.default_spacing))
    ) {
        items(hotels.size) {
            HotelCard(hotels[it], onHotelSelect)
        }
    }
}

@Composable
fun HotelCard(hotel: Hotel, onHotelSelect: (Hotel) -> Unit) {
    val modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight()
        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.app_card_corners_size)))
        .clickable {
            onHotelSelect(hotel)
        }

    Card(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
    ) {
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.small_spacing))) {
            HotelImageComposable(hotel, onHotelSelect)
            HotelNameAndAddressComposable(hotel)
            HotelRatingComposable(hotel)
        }
    }
}

@Composable
private fun HotelImageComposable(hotel: Hotel, onHotelSelect: (Hotel) -> Unit) {
    val hotelImageModifier = Modifier
        .height(dimensionResource(id = R.dimen.hotel_small_image_height))
        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.app_card_corners_size)))
    Image(
        modifier = hotelImageModifier,
        painter = painterResource(id = mapImageToDrawable(imageName = hotel.image)),
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(R.string.image_search_hotel)
    )
}

@Composable
private fun HotelNameAndAddressComposable(hotel: Hotel) {
    Text(
        modifier = Modifier
            .wrapContentWidth()
            .padding(top = dimensionResource(id = R.dimen.default_spacing)),

        text = hotel.name,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.h2
    )

    Text(
        modifier = Modifier.wrapContentWidth(),
        text = hotel.address.locationTitle,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.body1
    )
}

@Composable
private fun HotelRatingComposable(hotel: Hotel) {
    Row(
        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.small_spacing)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val startImageModifier = Modifier.size(
            width = dimensionResource(id = R.dimen.rating_star_size),
            height = dimensionResource(id = R.dimen.rating_star_size)
        )
        Image(
            modifier = startImageModifier,
            painter = painterResource(id = R.drawable.ic_star),
            contentDescription = stringResource(R.string.image_star)
        )
        Text(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.small_spacing)),
            text = hotel.rating.toString(),
            style = MaterialTheme.typography.body1,
            color = yellow_color
        )
    }
}