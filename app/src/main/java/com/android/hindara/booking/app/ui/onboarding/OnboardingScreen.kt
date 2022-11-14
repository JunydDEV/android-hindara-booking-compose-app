package com.android.hindara.booking.app.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.data.OnboardingImage
import com.android.hindara.booking.app.getOnBoardingImageSizeInDp
import com.android.hindara.booking.app.ui.authentication.authenticationRoute
import com.android.hindara.booking.app.ui.theme.DarkTextColor
import com.android.hindara.booking.app.ui.theme.ScreenBackgroundColor
import com.android.hindara.booking.app.ui.theme.SelectedDotTintColor
import com.android.hindara.booking.app.ui.theme.UnSelectedDotTintColor


@Composable
fun OnboardingScreen(
    navHostController: NavHostController,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val onboardingImagesList = viewModel.getOnboardingImagesList()
    val imageSelectionState = remember {
        mutableStateOf(0)
    }
    val selectedImage = onboardingImagesList[imageSelectionState.value]

    val parentColumnModifier = Modifier
        .fillMaxSize()
        .background(ScreenBackgroundColor)
        .verticalScroll(rememberScrollState())
    Column(modifier = parentColumnModifier) {
        SpacerComposable()
        OnboardingImageComposable(selectedImage)
        OnboardingTitleComposable(selectedImage)
        OnboardingDescriptionComposable(selectedImage)
        OnboardingBottomNavigationComposable(
            navHostController,
            onboardingImagesList,
            imageSelectionState
        )
    }
}

@Composable
private fun SpacerComposable() {
    val topSpacerModifier = Modifier
        .fillMaxWidth()
        .height(dimensionResource(id = R.dimen.extraLargeSpacing))
    Spacer(modifier = topSpacerModifier)
}


@Composable
private fun OnboardingImageComposable(selectedImage: OnboardingImage) {
    val onBoardingImageModifier = Modifier.size(
        width = getOnBoardingImageSizeInDp().first,
        height = getOnBoardingImageSizeInDp().second
    )
    Image(
        modifier = onBoardingImageModifier,
        painter = painterResource(id = selectedImage.imageDrawable),
        contentDescription = stringResource(R.string.image_description_getting_start_first)
    )
}

@Composable
private fun OnboardingTitleComposable(selectedImage: OnboardingImage) {
    val onBoardingTitleModifier = Modifier.padding(
        top = dimensionResource(id = R.dimen.largeSpacing),
        start = dimensionResource(id = R.dimen.defaultSpacing),
        end = dimensionResource(id = R.dimen.defaultSpacing),
    )
    Text(
        modifier = onBoardingTitleModifier,
        text = stringResource(id = selectedImage.title),
        style = MaterialTheme.typography.h1,
        color = DarkTextColor
    )
}

@Composable
private fun OnboardingDescriptionComposable(selectedImage: OnboardingImage) {
    val onBoardingDescriptionModifier = Modifier.padding(
        start = dimensionResource(id = R.dimen.defaultSpacing),
        end = dimensionResource(id = R.dimen.defaultSpacing),
    )
    Text(
        modifier = onBoardingDescriptionModifier,
        text = stringResource(id = selectedImage.description),
        style = MaterialTheme.typography.body2,
        color = DarkTextColor
    )
}

@Composable
private fun OnboardingBottomNavigationComposable(
    navHostController: NavHostController,
    onboardingImagesList: List<OnboardingImage>,
    imageSelectionState: MutableState<Int>
) {
    val onboardingNavigationModifier = Modifier
        .fillMaxSize()
        .padding(
            start = dimensionResource(id = R.dimen.defaultSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )
    OnboardingNavigationComposable(
        onboardingNavigationModifier,
        navHostController,
        onboardingImagesList,
        imageSelectionState
    )
}

@Composable
private fun OnboardingNavigationComposable(
    modifier: Modifier,
    navHostController: NavHostController,
    onboardingImagesList: List<OnboardingImage>,
    imageSelectionState: MutableState<Int>
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        ImageIndicatorsComposable(imageSelectionState, onboardingImagesList)
        NextButtonComposable(imageSelectionState, onboardingImagesList, navHostController)
    }
}

@Composable
private fun ImageIndicatorsComposable(
    imageSelectionState: MutableState<Int>,
    onboardingImagesList: List<OnboardingImage>
) {
    onboardingImagesList.mapIndexed { index, item ->
        item.isSelected = (index == imageSelectionState.value)
    }
    LazyRow(content = {
        items(onboardingImagesList) {
            DotView(it)
        }
    })
}

@Composable
private fun NextButtonComposable(
    imageSelectionState: MutableState<Int>,
    onboardingImagesList: List<OnboardingImage>,
    navHostController: NavHostController
) {
    val buttonNextModifier = Modifier
        .width(dimensionResource(id = R.dimen.buttonWidth))
        .padding(start = dimensionResource(id = R.dimen.defaultSpacing))

    val clickListener = if (imageSelectionState.value < onboardingImagesList.size - 1) {
        onNextClickListener(positionState = imageSelectionState)
    } else {
        onGetStartedListener(navHostController)
    }

    Button(
        modifier = buttonNextModifier,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.buttonCornersSize)),
        onClick = clickListener
    ) {
        OnboardingButtonContent(imageSelectionState, onboardingImagesList)
    }
}

@Composable
private fun OnboardingButtonContent(
    imageSelectionState: MutableState<Int>,
    onboardingImagesList: List<OnboardingImage>
) {
    val buttonText = getButtonText(imageSelectionState, onboardingImagesList)
    Text(text = buttonText)
}

@Composable
private fun getButtonText(
    imageSelectionState: MutableState<Int>,
    onboardingImagesList: List<OnboardingImage>
) = if (imageSelectionState.value < onboardingImagesList.size - 1) {
    stringResource(R.string.button_text_next)
} else {
    stringResource(R.string.button_text_get_started)
}

@Composable
private fun DotView(onboardingImage: OnboardingImage) {
    val icon = getDotIcon(onboardingImage)
    val tintColor = getTintColor(onboardingImage)
    Image(
        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.smallSpacing)),
        painter = painterResource(id = icon),
        colorFilter = ColorFilter.tint(tintColor),
        contentDescription = stringResource(R.string.dot_icon_image_description)
    )
}

fun getTintColor(onboardingImage: OnboardingImage) =
    if(onboardingImage.isSelected) {
        SelectedDotTintColor
    } else {
        UnSelectedDotTintColor
    }


@Composable
private fun getDotIcon(onboardingImage: OnboardingImage) =
    if (onboardingImage.isSelected) {
        R.drawable.ic_dot_selected
    } else {
        R.drawable.ic_dot_unselected
    }

@Composable
private fun onNextClickListener(positionState: MutableState<Int>): () -> Unit = {
    positionState.value += 1
}

@Composable
fun onGetStartedListener(navHostController: NavHostController): () -> Unit = {
    navHostController.navigate(authenticationRoute) {
        popUpTo(onboardingRoute) {
            inclusive = true
        }
    }
}