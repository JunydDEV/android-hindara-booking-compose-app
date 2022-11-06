package com.android.hindara.booking.app.ui.getstarted

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.getOnBoardingImageSizeInDp
import com.android.hindara.booking.app.ui.theme.DarkTextColor


@Composable
fun GettingStartedScreen() {
    val onboardingImagesList = listOfOnboardingImages()
    val imageSelectionState = remember {
        mutableStateOf(0)
    }
    val selectedImage = onboardingImagesList[imageSelectionState.value]

    Column(modifier = Modifier.fillMaxSize()) {
        val topSpacerModifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.largeSpacing))
        Spacer(modifier = topSpacerModifier)
        /************************ Added Top Spacing ***************************/

        val onBoardingImageModifier = Modifier.size(
            width = getOnBoardingImageSizeInDp().first,
            height = getOnBoardingImageSizeInDp().second
        )
        Image(
            modifier = onBoardingImageModifier,
            painter = painterResource(id = selectedImage.imageDrawable),
            contentDescription = stringResource(R.string.image_description_getting_start_first)
        )

        /************************* Added Onboarding Image **************************/

        val onBoardingTitleModifier = Modifier.padding(
            top = dimensionResource(id = R.dimen.largeSpacing),
            start = dimensionResource(id = R.dimen.defaultSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )
        Text(
            modifier = onBoardingTitleModifier,
            text = selectedImage.title,
            style = MaterialTheme.typography.h1,
            color = DarkTextColor
        )

        /************************* Added Onboarding Title **************************/

        val onBoardingDescriptionModifier = Modifier.padding(
            start = dimensionResource(id = R.dimen.defaultSpacing),
            end = dimensionResource(id = R.dimen.defaultSpacing),
        )
        Text(
            modifier = onBoardingDescriptionModifier,
            text = selectedImage.description,
            style = MaterialTheme.typography.body2,
            color = DarkTextColor
        )

        /************************* Added Onboarding Description **************************/

        val onBoardingNavigationModifier = Modifier
            .fillMaxSize()
            .padding(
                start = dimensionResource(id = R.dimen.defaultSpacing),
                end = dimensionResource(id = R.dimen.defaultSpacing),
            )
        OnBoardingNavigationView(
            onBoardingNavigationModifier,
            onboardingImagesList,
            imageSelectionState
        )

        /************************* Added Onboarding Navigation **************************/
    }
}

@Composable
private fun OnBoardingNavigationView(
    modifier: Modifier,
    onboardingImagesList: List<OnboardingImage>,
    imageSelectionState: MutableState<Int>
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        ImageIndicatorsView(imageSelectionState, onboardingImagesList)

        /************************* Added Dots View **************************/

        val buttonNextModifier = Modifier
            .width(dimensionResource(id = R.dimen.buttonWidth))
            .padding(
                start = dimensionResource(id = R.dimen.defaultSpacing),
            )

        val clickListener = if (imageSelectionState.value < onboardingImagesList.size - 1) {
            onNextClickListener(positionState = imageSelectionState)
        } else {
            onGetStartedListener()
        }

        Button(
            modifier = buttonNextModifier,
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.buttonCornersSize)),
            onClick = clickListener
        ) {
            OnboardingButtonContent(imageSelectionState, onboardingImagesList)
        }

        /************************* Added Next/Get-Started Button **************************/

    }
}

@Composable
private fun OnboardingButtonContent(
    imageSelectionState: MutableState<Int>,
    onboardingImagesList: List<OnboardingImage>
) {
    val buttonText = if (imageSelectionState.value < onboardingImagesList.size - 1) {
        stringResource(R.string.button_text_next)
    } else {
        stringResource(R.string.button_text_get_started)
    }
    Text(text = buttonText)
}

@Composable
private fun ImageIndicatorsView(
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
private fun DotView(onboardingImage: OnboardingImage) {
    val icon = if (onboardingImage.isSelected) {
        R.drawable.ic_dot_selected
    } else {
        R.drawable.ic_dot_unselected
    }
    Image(
        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.smallSpacing)),
        painter = painterResource(id = icon),
        contentDescription = stringResource(R.string.dot_icon_image_description)
    )
}

@Composable
private fun onNextClickListener(positionState: MutableState<Int>): () -> Unit = {
    positionState.value += 1
}

@Composable
fun onGetStartedListener(): () -> Unit = {

}

@Composable
private fun listOfOnboardingImages(): List<OnboardingImage> {
    return mutableListOf<OnboardingImage>().apply {
        add(
            OnboardingImage(
                imageDrawable = R.drawable.ic_onboarding_first_image,
                isSelected = true,
                position = 0,
                title = stringResource(id = R.string.text_onboarding_first_title),
                description = stringResource(id = R.string.text_onboarding_first_description)
            )
        )
        add(
            OnboardingImage(
                imageDrawable = R.drawable.ic_onboarding_second_image,
                isSelected = false,
                position = 1,
                title = stringResource(R.string.text_onboarding_second_title),
                description = stringResource(R.string.text_onboarding_second_description)
            )
        )
        add(
            OnboardingImage(
                imageDrawable = R.drawable.ic_onboarding_third_image,
                isSelected = false,
                position = 2,
                title = stringResource(R.string.text_onboarding_third_title),
                description = stringResource(R.string.text_onboarding_third_description)
            )
        )
    }
}

internal data class OnboardingImage(
    val imageDrawable: Int,
    var isSelected: Boolean = false,
    val position: Int,
    val title: String,
    val description: String
)