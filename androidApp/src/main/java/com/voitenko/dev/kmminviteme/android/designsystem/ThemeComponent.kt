package com.voitenko.dev.kmminviteme.android.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf

object AppTheme {
    val colors: AppColor
        @Composable
        get() = LocalAppColors.current

    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current
}

val LocalAppColors = staticCompositionLocalOf<AppColor> { error("No colors provided") }
val LocalAppTypography = staticCompositionLocalOf<AppTypography> { error("No font provided") }