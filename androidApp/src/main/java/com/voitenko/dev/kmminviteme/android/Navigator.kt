package com.voitenko.dev.kmminviteme.android

import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.voitenko.dev.kmminviteme.android.newEvent.NewEvent

@Composable
fun Navigator() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = "new_event") {
        composable(route = "new_event") {
            NewEvent(navController = navController)
        }
    }
}