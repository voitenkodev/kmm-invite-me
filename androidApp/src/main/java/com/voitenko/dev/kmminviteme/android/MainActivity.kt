package com.voitenko.dev.kmminviteme.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.voitenko.dev.kmminviteme.android.designsystem.Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            rememberSystemUiController().setStatusBarColor(
                color = Color.Transparent, darkIcons = MaterialTheme.colors.isLight
            )
            Theme {
                Navigator(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .statusBarsPadding()
                )
            }
        }
    }
}