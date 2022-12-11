package com.core.model.alert_bottomsheet

data class OnboardingImage(
    val imageDrawable: Int,
    var isSelected: Boolean = false,
    val position: Int,
    val title: Int,
    val description: Int
)