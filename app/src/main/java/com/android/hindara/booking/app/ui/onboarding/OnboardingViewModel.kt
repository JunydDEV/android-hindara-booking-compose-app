package com.android.hindara.booking.app.ui.onboarding

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import com.core.model.alert_bottomsheet.OnboardingImage
import javax.inject.Inject

class OnboardingViewModel @Inject constructor() : ViewModel() {

    fun getOnboardingImagesList(): List<OnboardingImage> {
        return mutableListOf<OnboardingImage>().apply {
            add(
                OnboardingImage(
                    imageDrawable = R.drawable.ic_onboarding_first_image,
                    isSelected = true,
                    position = 0,
                    title = R.string.label_travel_with_no_worry,
                    description = R.string.label_travel_with_no_worry_description
                )
            )
            add(
                OnboardingImage(
                    imageDrawable = R.drawable.ic_onboarding_second_image,
                    isSelected = false,
                    position = 1,
                    title = R.string.label_find_hundreds_hotels,
                    description = R.string.label_find_hundreds_hotels_description
                )
            )
            add(
                OnboardingImage(
                    imageDrawable = R.drawable.ic_onboarding_third_image,
                    isSelected = false,
                    position = 2,
                    title = R.string.label_discover_the_world,
                    description = R.string.label_discover_the_world_description
                )
            )
        }
    }

}