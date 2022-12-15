package com.android.hindara.booking.app.utils

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.android.hindara.booking.app.App
import com.google.accompanist.navigation.animation.composable

private const val POSITIVE_OFFSET = 2000
private const val NEGATIVE_OFFSET = -2000

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
                slideInHorizontally { width -> getEnterTransitionOffset(width) } + fadeIn() with
                        slideOutHorizontally { height -> getExitTransitionOffset(height) } + fadeOut()
            } else {
                // If the target number is smaller, it slides in and fades in
                // while the initial number slides out and fades out.
                slideInHorizontally { width -> getExitTransitionOffset(width) } + fadeIn() with
                        slideOutHorizontally { height -> getEnterTransitionOffset(height) } + fadeOut()
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
            getEnterTransitionAnimation()
        },
        exitTransition = {
            exitTransitionAnimation()
        },
        popEnterTransition = {
            getPopEnterTransitionAnimation()
        },
        popExitTransition = {
            getPopExitTransitionAnimation()
        }
    ) {
        content(it)
    }
}

private fun exitTransitionAnimation() = if (App.IS_RTL_LAYOUT) {
    slideOutHorizontally(targetOffsetX = { POSITIVE_OFFSET })
} else {
    slideOutHorizontally(targetOffsetX = { NEGATIVE_OFFSET })
}

private fun getEnterTransitionAnimation() = if (App.IS_RTL_LAYOUT) {
    slideInHorizontally(initialOffsetX = { NEGATIVE_OFFSET })
} else {
    slideInHorizontally(initialOffsetX = { POSITIVE_OFFSET })
}

private fun getPopEnterTransitionAnimation() = if (App.IS_RTL_LAYOUT) {
    slideInHorizontally(initialOffsetX = { POSITIVE_OFFSET })
} else {
    slideInHorizontally(initialOffsetX = { NEGATIVE_OFFSET })
}

private fun getPopExitTransitionAnimation() = if (App.IS_RTL_LAYOUT) {
    slideOutHorizontally(targetOffsetX = { NEGATIVE_OFFSET })
} else {
    slideOutHorizontally(targetOffsetX = { POSITIVE_OFFSET })
}

private fun getExitTransitionOffset(offset: Int) = if (App.IS_RTL_LAYOUT) { offset } else { -offset }

private fun getEnterTransitionOffset(offset: Int) = if (App.IS_RTL_LAYOUT) { -offset } else { offset }
