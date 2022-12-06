package com.android.hindara.booking.app.ui.search.filter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.theme.*
import kotlin.math.roundToInt

@Composable
fun FilterBottomSheetScreen(
    viewModel: FilterViewModel = hiltViewModel(),
    navController: NavController,
) {
    val locationSelectionState = remember {
        mutableStateOf<ChipInfo?>(null)
    }

    val facilitiesSelectionState = remember {
        mutableStateOf<ChipInfo?>(null)
    }

    val priceRangeState = remember {
        mutableStateOf(viewModel.getPriceRange())
    }

    val defaultSpacing = dimensionResource(id = R.dimen.defaultSpacing)
    val largeSpacing = dimensionResource(id = R.dimen.extraLargeSpacing)

    val modifier = Modifier
        .background(BottomSheetBackgroundColor)
        .padding(
            start = defaultSpacing, end = defaultSpacing, top = largeSpacing, bottom = largeSpacing
        )

    Column(modifier = modifier) {
        TitleComposable(stringResource(R.string.label_location))
        FilterChipComposable(
            list = viewModel.getLocationsChipsList(),
            value = locationSelectionState.value,
            onValueSelection = { locationSelectionState.value = it },
        )

        DefaultSpacer()
        TitleComposable(stringResource(id = R.string.label_price_range))
        PriceRangeSliderComposable(
            viewModel = viewModel,
            value = priceRangeState.value,
            onValueChange = { priceRangeState.value = it },
        )

        DefaultSpacer()
        TitleComposable(stringResource(R.string.label_facilities))
        FilterChipComposable(
            list = viewModel.getFacilitiesChipsList(),
            value = facilitiesSelectionState.value,
            onValueSelection = { facilitiesSelectionState.value = it }
        )
        ApplyFilterButtonComposable(navController)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PriceRangeSliderComposable(
    viewModel: FilterViewModel,
    value: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        RangeSlider(
            value = value,
            onValueChange = { onValueChange(it) },
            valueRange = viewModel.getPriceRange(),
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "$${value.start.roundToInt()}",
                style = MaterialTheme.typography.h2,
                color = LightTextColor)

            Text(
                text = "$${value.endInclusive.roundToInt()}",
                style = MaterialTheme.typography.h2,
                color = LightTextColor
            )
        }
    }
}

@Composable
private fun DefaultSpacer() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.defaultSpacing))
    )
}

@Composable
private fun TitleComposable(title: String) {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(
            bottom = dimensionResource(id = R.dimen.smallSpacing)
        )
    Text(
        modifier = modifier,
        text = title,
        style = MaterialTheme.typography.h1,
        color = DarkTextColor
    )
}


@Composable
fun FilterChipComposable(
    list: List<ChipInfo>,
    value: ChipInfo?,
    onValueSelection: (ChipInfo) -> Unit
) {

    val modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = dimensionResource(id = R.dimen.smallSpacing))

    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(list.size) { index ->
            ChipComposable(value, list, index, onValueSelection)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun ChipComposable(
    selectedChipInfo: ChipInfo?,
    locations: List<ChipInfo>,
    index: Int,
    onLocationSelection: (ChipInfo) -> Unit
) {
    FilterChip(
        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.defaultSpacing)),
        selected = isChipSelected(selectedChipInfo, locations[index]),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.cardCornersSize)),
        border = getBorderStroke(selectedChipInfo, locations, index),
        colors = chipColors(),
        onClick = {
            locations[index].isSelected = !locations[index].isSelected
            onLocationSelection(locations[index])
        }
    ) {
        ChipLabelComposable(locations, index, selectedChipInfo)
    }
}

@Composable
private fun ChipLabelComposable(
    locations: List<ChipInfo>,
    index: Int,
    selectedChipInfo: ChipInfo?
) {
    Text(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.smallSpacing)),
        text = locations[index].label,
        style = MaterialTheme.typography.h2,
        color = getTextColor(selectedChipInfo, locations[index])
    )
}

@Composable
private fun getBorderStroke(
    selectedChipInfo: ChipInfo?,
    locations: List<ChipInfo>,
    index: Int
) = BorderStroke(
    dimensionResource(id = R.dimen.borderWidth),
    brush = getBordersColor(selectedChipInfo, locations[index])
)

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun chipColors() = ChipDefaults.filterChipColors(
    backgroundColor = WhiteColor,
    contentColor = DarkTextColor,
    selectedBackgroundColor = SelectedChipBackgroundColor,
    selectedContentColor = PrimaryColor

)

private fun isChipSelected(
    selectedChipInfo: ChipInfo?,
    currentChip: ChipInfo,
) = selectedChipInfo != null
        && selectedChipInfo.label == currentChip.label && selectedChipInfo.isSelected

fun getBordersColor(
    selectedChipInfo: ChipInfo?,
    currentChipInfo: ChipInfo
): Brush {
    return if (isChipSelected(selectedChipInfo, currentChipInfo)) {
        SolidColor(PrimaryColor)
    } else {
        SolidColor(LightTextColor)
    }
}

fun getTextColor(
    selectedChipInfo: ChipInfo?,
    currentChipInfo: ChipInfo
): Color {
    return if (isChipSelected(
            selectedChipInfo = selectedChipInfo,
            currentChip = currentChipInfo
        )
    ) {
        PrimaryColor
    } else {
        LightTextColor
    }
}

@Composable
private fun ApplyFilterButtonComposable(navController: NavController) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(
            top = dimensionResource(id = R.dimen.defaultSpacing),
            bottom = dimensionResource(id = R.dimen.defaultSpacing)
        )
    Button(
        modifier = buttonModifier,
        shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.buttonCornersSize))),
        onClick = {
            navController.popBackStack()
        },
    ) {
        Text(stringResource(R.string.button_apply_filter_label))
    }
}
