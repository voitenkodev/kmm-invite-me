package com.voitenko.dev.kmminviteme.android.common

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.voitenko.dev.kmminviteme.android.common.base.FabIcon
import com.voitenko.dev.kmminviteme.android.common.base.SimpleImage
import com.voitenko.dev.kmminviteme.android.common.theme.AppTheme
import com.voitenko.dev.kmminviteme.android.common.theme.MainTheme

@Composable
fun ImagePickerBlock(
    image: Uri = Uri.EMPTY,
    alpha: Float = 1f,
    placeholder: ImageVector = Icons.Default.Image,
    onClick: (() -> Unit)? = null,
) = SimpleImage(
    modifier = Modifier
        .padding(8.dp)
        .clip(AppTheme.shapes.medium)
        .background(AppTheme.colors.background)
        .alpha(alpha)
        .clickable { onClick?.invoke() },
    painter = rememberCoilPainter(request = image)
) {
    AnimatedVisibility(visible = image == Uri.EMPTY) {
        FabIcon(
            imageVector = placeholder, tint = AppTheme.colors.hint,
            modifier = Modifier
                .size(56.dp)
                .alpha(alpha),
        )
    }
}