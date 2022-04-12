package com.voitenko.dev.kmminviteme.android.ui.newevent

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.voitenko.dev.kmminviteme.android.designsystem.AppTheme
import com.voitenko.dev.kmminviteme.android.designsystem.ArcShape
import com.voitenko.dev.kmminviteme.android.designsystem.components.*

@Composable
fun NewEventScreen(navController: NavController) {

    val almostWhite = Color(236, 234, 235)

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f)
                .shadow(elevation = 4.dp, shape = ArcShape())
                .background(color = almostWhite, shape = ArcShape())
                .padding(24.dp)
        ) {
            Switch()
        }
    }
}

@Composable
fun Sad() {
    Row {
        Column(modifier = Modifier.weight(1f)) {
            H1Text(text = "H1Text", textAlign = TextAlign.Center)
            H2Text(text = "H2Text")
            H3Text(text = "H3Text")
            BODY1Text(text = "BODY1Text")
            BODY2Text(text = "BODY2Text")
            BODY3Text(text = "BODY3Text")
            BUTTON1Text(text = "BUTTON1Text")
            BUTTON2Text(text = "BUTTON2Text")
        }
    }
}