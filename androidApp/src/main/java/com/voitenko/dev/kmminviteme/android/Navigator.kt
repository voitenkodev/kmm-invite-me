package com.voitenko.dev.kmminviteme.android

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.voitenko.dev.kmminviteme.android.ui.newevent.NewEventScreen

@Composable
fun Navigator(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "new_event") {
        composable(route = "new_event") {
            NewEventScreen(navController = navController)
        }
    }
}