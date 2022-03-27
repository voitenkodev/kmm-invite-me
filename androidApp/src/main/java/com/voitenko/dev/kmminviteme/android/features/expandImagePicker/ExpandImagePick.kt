package com.voitenko.dev.kmminviteme.android.features.expandImagePicker

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.voitenko.dev.kmminviteme.android.common.ErrorBlock
import com.voitenko.dev.kmminviteme.android.common.ExpanderBlock
import com.voitenko.dev.kmminviteme.android.common.ImagePickerBlock
import com.voitenko.dev.kmminviteme.android.designsystem.Theme

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

@Preview
@Composable
fun ExpandImagePickBlock_Preview() {
    val preview = ExpandImagePickFeature.State(
        error = ExpandImagePickFeature.State.Error(
            text = "ops, u forgot to put image",
            isShowed = false
        ),
        image = ExpandImagePickFeature.State.Image(
            image = Uri.EMPTY,
            placeHolder = Icons.Default.Image,
        ),
        expander = ExpandImagePickFeature.State.Expander(
            isOpened = false,
            number = "5",
            notes = "Add Image or Photo of event",
            expandHeight = (56 * 2.5).toInt(),
        )
    )
    Theme {
        Column {
            ExpandImagePickBlock(
                modifier = Modifier.padding(top = 4.dp),
                state = preview,
                onClick = { }
            )
            ExpandImagePickBlock(
                modifier = Modifier.padding(top = 4.dp),
                state = preview.copy(expander = preview.expander.copy(isOpened = true)),
                onClick = { }
            )
        }
    }
}