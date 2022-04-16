package com.voitenko.dev.kmminviteme.android.ui.newevent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.voitenko.dev.kmminviteme.android.designsystem.*
import com.voitenko.dev.kmminviteme.android.designsystem.components.RideBox

@Composable
fun NewEventScreen(navController: NavController) {

    val almostWhite = Color(236, 234, 235)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(cyan1)
            .padding(top = 40.dp)
            .padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = cyan2, shape = RoundedCornerShape(16.dp))
                .aspectRatio(1f)
                .neu(
                    radius = 16.dp,
                    pressed = true,
                    shadow1 = cyan2shadow2,
                    shadow2 = cyan1shadow1,
                ), contentAlignment = Alignment.Center
        ) {
            val width = maxWidth.value / 1.5

            RideBox(
                modifier = Modifier
                    .size(width.dp)
                    .neu(
                        radius = 16.dp,
                        pressed = false,
                        shadow1 = cyan2shadow2,
                        shadow2 = cyan2shadow2,
                    )
                    .background(Color.White, RoundedCornerShape(16.dp)),
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://zimamagazine.com/wp-content/uploads/2019/05/zvezdnaya-noch-nad-ronoi-600x400.jpg")
                        .crossfade(true).build(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                )
            }
        }
    }
}