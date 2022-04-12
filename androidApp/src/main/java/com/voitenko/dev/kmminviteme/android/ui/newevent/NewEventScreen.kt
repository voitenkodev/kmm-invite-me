package com.voitenko.dev.kmminviteme.android.ui.newevent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.voitenko.dev.kmminviteme.android.designsystem.ArcShape
import com.voitenko.dev.kmminviteme.android.designsystem.components.*
import com.voitenko.dev.kmminviteme.android.designsystem.components.box.FlatBox

@Composable
fun NewEventScreen(navController: NavController) {

    val almostWhite = Color(236, 234, 235)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f)
                .shadow(elevation = 4.dp, shape = ArcShape())
                .background(color = almostWhite, shape = ArcShape())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Switch()

            Check()
        }

        FlatBox(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .height(56.dp),
            radius = 16.dp,
            color = almostWhite
        ) {

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