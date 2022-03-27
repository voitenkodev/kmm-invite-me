package com.voitenko.dev.kmminviteme.android.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun Theme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = when (darkTheme) {
        true -> darkPalette()
        false -> lightPalette()
    }

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides appTypography(),
        content = content
    )
}