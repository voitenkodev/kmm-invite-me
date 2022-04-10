package com.voitenko.dev.kmminviteme.android.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.voitenko.dev.kmminviteme.android.R

@ExperimentalUnitApi
@Composable
fun appTypography() = AppTypography(
    H1 = TextStyle(
        fontSize = 34.sp,
        fontFamily = QuickSand,
        fontWeight = FontWeight.ExtraBold,
        fontStyle = FontStyle.Normal,
        color = Color.Yellow,
    ), H2 = TextStyle(
        fontSize = 25.sp,
        fontFamily = QuickSand,
        fontWeight = FontWeight.W600,
        color = Color.Black
    ), H3 = TextStyle(
        fontFamily = QuickSand,
        fontSize = 26.sp,
        fontWeight = FontWeight.W400,
        color = Color.Black
    ), BODY1 = TextStyle(
        fontSize = 16.sp,
        fontFamily = QuickSand,
        fontWeight = FontWeight.W600,
        color = Color.Black
    ), BODY2 = TextStyle(
        fontSize = 16.sp,
        fontFamily = QuickSand,
        fontWeight = FontWeight.W400,
        color = Color.Black
    ), BODY3 = TextStyle(
        fontSize = 16.sp,
        fontFamily = QuickSand,
        fontWeight = FontWeight.W300,
        color = Color.Black
    ), BUTTON1 = TextStyle(
        fontSize = 14.sp,
        fontFamily = QuickSand,
        fontWeight = FontWeight.W600,
        color = Color.Black
    ), BUTTON2 = TextStyle(
        fontSize = 18.sp,
        fontFamily = QuickSand,
        fontWeight = FontWeight.W600,
        color = Color.Black
    )
)

data class AppTypography(
    val H1: TextStyle,
    val H2: TextStyle,
    val H3: TextStyle,
    val BODY1: TextStyle,
    val BODY2: TextStyle,
    val BODY3: TextStyle,
    val BUTTON1: TextStyle,
    val BUTTON2: TextStyle,
)

private val QuickSand = FontFamily(
    Font(R.font.quicksand_light, FontWeight.W300),
    Font(R.font.quicksand_regular, FontWeight.W400),
    Font(R.font.quicksand_medium, FontWeight.W600),
    Font(R.font.quicksand_bold, FontWeight.W900)
)
private val collegiate = FontFamily(
    Font(R.font.collegiate_filled, style = FontStyle.Normal, weight = FontWeight.ExtraBold),
    Font(R.font.collegiate_inside, style = FontStyle.Normal, weight = FontWeight.Normal),
    Font(R.font.collegiate, style = FontStyle.Normal, weight = FontWeight.Normal),
    Font(R.font.collegiate_border, style = FontStyle.Italic, weight = FontWeight.ExtraLight),
    Font(R.font.collegiate_outline, style = FontStyle.Italic),

    )