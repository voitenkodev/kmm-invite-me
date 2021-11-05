package com.voitenko.dev.kmminviteme.android.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.voitenko.dev.kmminviteme.android.theme.AppTheme
import com.voitenko.dev.kmminviteme.android.base.box.FlipBox
import com.voitenko.dev.kmminviteme.android.base.box.FlipIconType
import com.voitenko.dev.kmminviteme.android.base.box.ShimmerBox

@Composable
fun SplashScreen(navController: NavController) {
    val alphaLogo = remember { Animatable(initialValue = 0f) }
    val visibilityText = remember { mutableStateOf(false) }
    val offsetContent = remember { mutableStateOf(0.dp) }

    LaunchedEffect(key1 = true) {
//        delay(500)
//        alphaLogo.animateTo(targetValue = 1f, animationSpec = tween(durationMillis = 2000))
//        visibilityText.value = visibilityText.value.not()
//        delay(500)
//        offsetContent.value = 1000.dp
        navController.navigate("new_event_screen")
    }

    ShimmerBox(
        gradient = listOf(
            AppTheme.colors.background,
            AppTheme.colors.background,
            AppTheme.colors.background
        )
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(brush = it)
                .fillMaxSize()
        ) {
            Column(
                Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 100.dp, bottom = 100.dp)
                    .offset(y = offsetContent.value),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FlipBox(type = listOf(FlipIconType.Y), repeatCount = 1) {
                    Image(
                        painter = painterResource(id = android.R.drawable.alert_light_frame),
                        contentDescription = "logo",
                        contentScale = ContentScale.Crop,
                        alpha = alphaLogo.value,
                        modifier = Modifier
                            .width(171.dp)
                            .height(103.dp)
                    )
                }
                AnimatedVisibility(visible = visibilityText.value) {
                    Text(
                        text = "INVITE ME",
                        style = AppTheme.typography.input.copy(color = Color.White),
                        fontWeight = FontWeight.Bold,
                        fontSize = 35.sp,
                    )
                }

            }
        }
    }
}
