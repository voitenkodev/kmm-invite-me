package com.voitenko.dev.kmminviteme.android.features.expandTextInput

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.voitenko.dev.kmminviteme.android.common.ErrorBlock
import com.voitenko.dev.kmminviteme.android.common.ExpanderBlock
import com.voitenko.dev.kmminviteme.android.common.InputBlock
import com.voitenko.dev.kmminviteme.android.common.theme.AppTheme

@Composable
fun ExpandInputBlock(
    modifier: Modifier = Modifier,
    state: ExpandInputFeature.State,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChange: ((String) -> Unit)? = null,
    onClick: (() -> Unit)? = null
) = Column(modifier = modifier) {
    ExpanderBlock(
        modifier = Modifier,
        heightExpand = state.expander.expandHeight?.dp,
        expand = state.expander.isOpened,
        number = state.expander.number,
        notes = state.expander.notes,
        content = {
            InputBlock(
                modifier = Modifier
                    .alpha(it),
//                    .focusRequester(titleInput),
                text = state.input.text,
                onClick = onClick,
                placeholder = state.input.placeholder,
                textStyle = AppTheme.typography.input.copy(color = Color.Black),
                keyboardActions = keyboardActions, /*KeyboardActions(onNext = { vm.want(ExpanderFeature.Wish.Expand) })*/
                onValueChange = onValueChange, /*vm.want(NewEventViewModel.TAG.TITLE_INPUT, InputFeature.Wish.SetText(it))*/
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )
        }
    )

    ErrorBlock(text = state.error.text, isShowed = state.error.isShowed)
}