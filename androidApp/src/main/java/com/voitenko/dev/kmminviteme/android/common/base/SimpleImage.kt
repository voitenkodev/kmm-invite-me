package com.voitenko.dev.kmminviteme.android.common.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

@Composable
fun SimpleImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    painter: Painter,
    placeholder: @Composable BoxScope.() -> Unit
) = Box(contentAlignment = Alignment.Center) {
    Image(
        contentScale = contentScale,
        painter = painter,
        contentDescription = "Image",
        modifier = modifier
    )
    placeholder.invoke(this)
}