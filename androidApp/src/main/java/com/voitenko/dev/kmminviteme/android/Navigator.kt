package com.voitenko.dev.kmminviteme.android

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.voitenko.dev.kmminviteme.android.screens.HomeScreen
import com.voitenko.dev.kmminviteme.android.screens.SplashScreen
import com.voitenko.dev.kmminviteme.android.screens.newevent.NewEventScreen

@Composable
fun Navigator() {
    val navController = rememberAnimatedNavController()

    val springSpec = spring<IntOffset>(dampingRatio = Spring.DampingRatioMediumBouncy)

    AnimatedNavHost(navController = navController, startDestination = "splash_screen") {
        composable(route = "splash_screen") {
            SplashScreen(navController = navController)
        }
        composable(route = "main_screen", enterTransition = { _, _ ->
            slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = springSpec)
        }, exitTransition = { _, _ ->
            slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = springSpec)
        }, popEnterTransition = { _, _ ->
            slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = springSpec)
        }, popExitTransition = { _, _ ->
            slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = springSpec)
        }) {
            HomeScreen(navController = navController)
        }
        composable(route = "new_event_screen", enterTransition = { initial, _ ->
            slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = springSpec)
        }, exitTransition = { _, _ ->
            slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = springSpec)
        }, popEnterTransition = { _, _ ->
            slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = springSpec)
        }, popExitTransition = { _, _ ->
            slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = springSpec)
        }) {
            NewEventScreen(navController = navController)
        }
    }
}