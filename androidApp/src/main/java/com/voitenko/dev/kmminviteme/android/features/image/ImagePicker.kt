package com.voitenko.dev.kmminviteme.android.features.image

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.voitenko.dev.kmminviteme.android.theme.AppTheme
import com.voitenko.dev.kmminviteme.android.base.FabIcon
import com.voitenko.dev.kmminviteme.android.base.SimpleImage


@Composable
fun ImagePicker(
    state: ImagePickerFeature.State,
    alpha: Float = 1f,
    onClick: (() -> Unit)? = null,
) = SimpleImage(
    modifier = Modifier
        .padding(6.dp)
        .clip(AppTheme.shapes.medium)
        .background(AppTheme.colors.background)
        .alpha(alpha)
        .clickable { onClick?.invoke() },
    painter = rememberCoilPainter(request = state.image)
) {
    AnimatedVisibility(visible = state.image == Uri.EMPTY) {
        FabIcon(
            imageVector = Icons.Default.Image, tint = AppTheme.colors.hint,
            modifier = Modifier
                .size(56.dp)
                .alpha(alpha),
        )
    }
}