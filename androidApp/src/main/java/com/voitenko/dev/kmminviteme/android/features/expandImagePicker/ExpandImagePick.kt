package com.voitenko.dev.kmminviteme.android.features.expandImagePicker

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.voitenko.dev.kmminviteme.android.common.ErrorBlock
import com.voitenko.dev.kmminviteme.android.common.ExpanderBlock
import com.voitenko.dev.kmminviteme.android.common.ImagePickerBlock

@Composable
fun ExpandImagePickBlock(
    modifier: Modifier = Modifier,
    state: ExpandImagePickFeature.State,
    onClick: (() -> Unit)? = null,
) = Column(modifier = modifier) {
    ExpanderBlock(
        modifier = Modifier,
        heightExpand = state.expander.expandHeight?.dp,
        expand = state.expander.isOpened,
        number = state.expander.number,
        notes = state.expander.notes,
        content = {
            ImagePickerBlock(
                image = state.image.image,
                alpha = it,
                placeholder = state.image.placeHolder,
                onClick = onClick,
            )
        })
    ErrorBlock(text = state.error.text, isShowed = state.error.isShowed)
}