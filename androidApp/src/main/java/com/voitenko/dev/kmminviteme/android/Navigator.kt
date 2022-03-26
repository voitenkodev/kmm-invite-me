package com.voitenko.dev.kmminviteme.android

import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.voitenko.dev.kmminviteme.android.newEvent.NewEvent
import com.voitenko.dev.kmminviteme.android.sample.CertificatesStack
import com.voitenko.dev.kmminviteme.android.superbox.ParallaxBox

@Composable
fun Navigator() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = "test_screen") {
        composable(route = "new_event") {
            NewEvent(navController = navController)
        }
        composable(route = "test_screen") {
            TestScreen()
        }
        composable(route = "certificates_stack") {
            CertificatesStack()
        }
    }
}