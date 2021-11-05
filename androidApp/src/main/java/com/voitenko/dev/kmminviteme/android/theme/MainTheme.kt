package com.voitenko.dev.kmminviteme.android.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voitenko.dev.kmminviteme.android.R

private val QuickSand = FontFamily(
    Font(R.font.quicksand_light, FontWeight.W300),
    Font(R.font.quicksand_regular, FontWeight.W400),
    Font(R.font.quicksand_medium, FontWeight.W500),
    Font(R.font.quicksand_bold, FontWeight.W600)
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

val lightPalette = AppColor(
    background = crystal,
    control = darkPurple,

    primary = Color.White,
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

val darkPalette = lightPalette

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when (darkTheme) {
        true -> darkPalette
        false -> lightPalette
    }
    val typography = AppTypography(
        toolbar = TextStyle(
            fontSize = 25.sp,
            fontFamily = QuickSand,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
        title = TextStyle(
            fontFamily = QuickSand,
            fontSize = 26.sp,
            fontWeight = FontWeight.W600,
            color = Color.Black
        ),
        description = TextStyle(
            fontSize = 16.sp,
            fontFamily = QuickSand,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        ),

        notes = TextStyle(
            fontSize = 16.sp,
            fontFamily = QuickSand,
            fontWeight = FontWeight.W600,
            color = Color.Black
        ),

        input = TextStyle(
            fontFamily = QuickSand,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        ),

        button = TextStyle(
            fontSize = 16.sp,
            fontFamily = QuickSand,
            fontWeight = FontWeight.Bold,
            color = Color.White
        ),


        )

    val shapes = AppShape(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(8.dp),
        large = RoundedCornerShape(16.dp),
        flat = RoundedCornerShape(0.dp),
    )

    CompositionLocalProvider(
        LocalJetHabitColors provides colors,
        LocalJetHabitTypography provides typography,
        LocalJetHabitShape provides shapes,
        content = content
    )
}