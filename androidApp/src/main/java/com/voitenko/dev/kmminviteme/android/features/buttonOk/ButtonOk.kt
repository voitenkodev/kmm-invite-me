package com.voitenko.dev.kmminviteme.android.features.buttonOk

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.voitenko.dev.kmminviteme.android.common.base.Title
import com.voitenko.dev.kmminviteme.android.common.theme.AppTheme

@Composable
fun ButtonOk(modifier: Modifier = Modifier, state: ButtonOkFeature.State, onClick: () -> Unit) {
    Button(
        modifier = modifier
            .clip(AppTheme.shapes.medium)
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = state.color
        )
    ) {
        Title(
            text = state.text,
            color = AppTheme.colors.primary,
            modifier = Modifier.fillMaxWidth()
        )
    }
}