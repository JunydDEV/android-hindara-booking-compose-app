package com.android.hindara.booking.app.ui.onboarding

import androidx.lifecycle.ViewModel
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.data.OnboardingImage
import javax.inject.Inject

class OnboardingViewModel @Inject constructor() : ViewModel() {

    fun getOnboardingImagesList(): List<OnboardingImage> {
        return mutableListOf<OnboardingImage>().apply {
            add(
                OnboardingImage(
                    imageDrawable = R.drawable.ic_onboarding_first_image,
                    isSelected = true,
                    position = 0,
                    title = R.string.text_onboarding_first_title,
                    description = R.string.text_onboarding_first_description
                )
            )
            add(
                OnboardingImage(
                    imageDrawable = R.drawable.ic_onboarding_second_image,
                    isSelected = false,
                    position = 1,
                    title = R.string.text_onboarding_second_title,
                    description = R.string.text_onboarding_second_description
                )
            )
            add(
                OnboardingImage(
                    imageDrawable = R.drawable.ic_onboarding_third_image,
                    isSelected = false,
                    position = 2,
                    title = R.string.text_onboarding_third_title,
                    description = R.string.text_onboarding_third_description
                )
            )
        }
    }

}