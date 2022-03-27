package com.voitenko.dev.kmminviteme.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.voitenko.dev.kmminviteme.android.designsystem.AppTheme
import com.voitenko.dev.kmminviteme.android.designsystem.Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight

            Theme(
                content = {
                    systemUiController.setSystemBarsColor(
                        color = AppTheme.colors.background,
                        darkIcons = useDarkIcons,
                    )
                    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                        Surface(
                            color = AppTheme.colors.background,
                            contentColor = contentColorFor(backgroundColor = AppTheme.colors.background),
                            content = { Navigator() }
                        )
                    }
                }
            )
        }
    }
}
