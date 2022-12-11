package com.android.hindara.booking.app.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.authentication.authenticationRoute
import com.android.hindara.booking.app.ui.theme.selected_dot_tint_color
import com.android.hindara.booking.app.ui.theme.unselected_dot_tint_color
import com.android.hindara.booking.app.utils.OnboardingContentSlideAnimation
import com.android.hindara.booking.app.utils.getOnBoardingImageSizeInDp
import com.core.model.alert_bottomsheet.OnboardingImage


@Composable
fun OnboardingScreen(
    navHostController: NavHostController,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val onboardingImagesList = viewModel.getOnboardingImagesList()
    val imageSelectionState = remember {
        mutableStateOf(0)
    }

    val parentColumnModifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
        .verticalScroll(rememberScrollState())
    Column(modifier = parentColumnModifier) {
        SpacerComposable()
        OnboardingContentSlideAnimation(imageSelectionState.value) {
            val selectedImage = onboardingImagesList[it]
            OnboardingSlide(selectedImage)
        }
        OnboardingBottomNavigationComposable(
            navHostController,
            onboardingImagesList,
            imageSelectionState
        )
    }
}

@Composable
private fun OnboardingSlide(selectedImage: OnboardingImage) {
    Column {
        OnboardingImageComposable(selectedImage)
        OnboardingTitleComposable(selectedImage)
        OnboardingDescriptionComposable(selectedImage)
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
private fun OnboardingImageComposable(selectedImage: OnboardingImage) {
    val onBoardingImageModifier = Modifier
        .clip(
            RoundedCornerShape(
                topEnd = dimensionResource(id = R.dimen.hotel_content_corners_size),
                bottomEnd = dimensionResource(id = R.dimen.hotel_content_corners_size)
            )
        )
        .size(
            width = getOnBoardingImageSizeInDp().first,
            height = getOnBoardingImageSizeInDp().second
        )

    Image(
        modifier = onBoardingImageModifier,
        contentScale = ContentScale.Crop,
        painter = painterResource(id = selectedImage.imageDrawable),
        contentDescription = stringResource(R.string.image_getting_started)
    )
}

@Composable
private fun OnboardingTitleComposable(selectedImage: OnboardingImage) {
    val onBoardingTitleModifier = Modifier.padding(
        top = dimensionResource(id = R.dimen.large_spacing),
        start = dimensionResource(id = R.dimen.default_spacing),
        end = dimensionResource(id = R.dimen.default_spacing),
    )
    Text(
        modifier = onBoardingTitleModifier,
        text = stringResource(id = selectedImage.title),
        style = MaterialTheme.typography.h1
    )
}

@Composable
private fun OnboardingDescriptionComposable(selectedImage: OnboardingImage) {
    val onBoardingDescriptionModifier = Modifier.padding(
        start = dimensionResource(id = R.dimen.default_spacing),
        end = dimensionResource(id = R.dimen.default_spacing),
    )
    Text(
        modifier = onBoardingDescriptionModifier,
        text = stringResource(id = selectedImage.description),
        style = MaterialTheme.typography.body2
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
            start = dimensionResource(id = R.dimen.default_spacing),
            end = dimensionResource(id = R.dimen.default_spacing),
            top = dimensionResource(id = R.dimen.default_spacing)
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
        items(onboardingImagesList.size) {
            DotView(onboardingImagesList[it])
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
        .width(dimensionResource(id = R.dimen.primary_button_width))
        .padding(start = dimensionResource(id = R.dimen.default_spacing))

    val clickListener = if (imageSelectionState.value < onboardingImagesList.size - 1) {
        onNextClickListener(positionState = imageSelectionState)
    } else {
        onGetStartedListener(navHostController)
    }

    Button(
        modifier = buttonNextModifier,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.primary_button_corners_size)),
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
    stringResource(R.string.button_next_label)
} else {
    stringResource(R.string.button_get_started_label)
}

@Composable
private fun DotView(onboardingImage: OnboardingImage) {
    val icon = getDotIcon(onboardingImage)
    val tintColor = getTintColor(onboardingImage)
    Image(
        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.small_spacing)),
        painter = painterResource(id = icon),
        colorFilter = ColorFilter.tint(tintColor),
        contentDescription = stringResource(R.string.image_slider_dot)
    )
}

fun getTintColor(onboardingImage: OnboardingImage) =
    if (onboardingImage.isSelected) {
        selected_dot_tint_color
    } else {
        unselected_dot_tint_color
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