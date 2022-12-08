package com.android.hindara.booking.app.utils

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.ui.onboarding.onboardingRoute
import com.google.accompanist.navigation.animation.composable

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

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.animatedComposable(
    route: String,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { 1000 })
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { -1000 })
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { -1000 })
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { 1000 })
        }
    ) {
        content(it)
    }
}