package com.android.hindara.booking.app.utils

import androidx.compose.animation.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OnboardingContentSlideAnimation(
    selectionPosition: Int,
    content: @Composable (Int) -> Unit) {
    AnimatedContent(
        targetState = selectionPosition,
        transitionSpec = {
            if (targetState > initialState) {
                // If the target number is larger, it slides in and fades in
                // while the initial (smaller) number slides out and fades out.
                slideInHorizontally { width -> width } + fadeIn() with
                        slideOutHorizontally { height -> -height } + fadeOut()
            } else {
                // If the target number is smaller, it slides in and fades in
                // while the initial number slides out and fades out.
                slideInHorizontally { width -> -width } + fadeIn() with
                        slideOutHorizontally() { height -> height } + fadeOut()
            }.using(
                // Disable clipping since the faded slide-in/out should
                // be displayed out of bounds.
                SizeTransform(clip = false)
            )
        }
    ) { targetPosition->
        content(targetPosition)
    }
}