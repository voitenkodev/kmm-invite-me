package com.voitenko.dev.kmminviteme.android.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

data class AppColor(
    val background: Color,
    val control: Color,

    val primary: Color,
    val primaryText: Color,

    val secondaryText: Color,
    val secondary: Color,

    val buttonText: Color,
    val button: Color,

    val successColor: Color,
    val warningColor: Color,
    val errorColor: Color,

    val hint: Color,
)

data class AppTypography(
    val toolbar: TextStyle,
    val input: TextStyle,
    val title: TextStyle,
    val notes: TextStyle,
    val description: TextStyle,
    val button: TextStyle,
)

data class AppShape(
    val small: Shape,
    val medium: Shape,
    val large: Shape,
    val flat: Shape,
)

object AppTheme {
    val colors: AppColor
        @Composable
        get() = LocalJetHabitColors.current

    val typography: AppTypography
        @Composable
        get() = LocalJetHabitTypography.current

    val shapes: AppShape
        @Composable
        get() = LocalJetHabitShape.current
}

val LocalJetHabitColors = staticCompositionLocalOf<AppColor> {
    error("No colors provided")
}

val LocalJetHabitTypography = staticCompositionLocalOf<AppTypography> {
    error("No font provided")
}

val LocalJetHabitShape = staticCompositionLocalOf<AppShape> {
    error("No shapes provided")
}
