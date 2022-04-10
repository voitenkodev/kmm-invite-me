package com.voitenko.dev.kmminviteme.android.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun lightPalette() = AppColor(
    background = crystal,
    control = darkPurple,
    primary = darkPurple,
    primaryText = darkPurple,
    secondary = Color.White,
    secondaryText = Color.Black,
    errorColor = darkRed,
    successColor = cyan2,
    warningColor = darkYellow,
    buttonText = Color.White,
    button = darkPurple,
    hint = Color.Black.copy(alpha = 0.3F)
)

@Composable
fun darkPalette() = lightPalette()

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

val lightPurple = Color(0xFF5622E5)
val darkPurple = Color(0xFF4119AF)

val darkYellow = Color(0xFFD5BD3F)
val lightYellow = Color(0xFFFADE4B)

val lightRed = Color(0xFFEA3469)
val darkRed = Color(0xFFC52B58)

val cyan1 = Color(0xFF28D8A3)
val cyan2 = Color(0xFF00BEB2)

val crystal = Color(0xFFD0DBE5)