package com.voitenko.dev.kmminviteme.android.ui.newevent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.voitenko.dev.kmminviteme.android.designsystem.components.*
import com.voitenko.dev.kmminviteme.android.neomorph.neu

@Composable
fun NewEventScreen(navController: NavController) {

    val almostWhite = Color(236, 234, 235)

    Box(
        Modifier
            .fillMaxSize()
            .background(almostWhite)
            .padding(20.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color.White, shape = RoundedCornerShape(24.dp))
                .neu(24.dp, pressed = true)
                .padding(20.dp)
        ) {

            Card(
                modifier = Modifier
                    .size(100.dp)
                    .neu(24.dp, pressed = false),
                shape = RoundedCornerShape(24.dp),
            ) {
                // Add child components here.
            }
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