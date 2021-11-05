package com.voitenko.dev.kmminviteme.android.features.bottomSheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.voitenko.dev.kmminviteme.android.features.backpress.BackHandler

@Composable
fun BottomSheet(
    state: BottomSheetFeature.State,
    onClose: () -> Unit,
    content: @Composable () -> Unit,
    bottomSheetContent: @Composable ColumnScope.() -> Unit,
) {
    val controller: ModalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    LaunchedEffect(key1 = state.isOpen) {
        if (state.isOpen && controller.isVisible.not()) controller.show()
        else if (state.isOpen.not() && controller.isVisible) controller.hide()
    }

    BackHandler(state.isOpen) { onClose.invoke() }

    // https://stackoverflow.com/questions/69052660/listen-modalbottomsheetlayout-state-change-in-jetpack-compose
    if (controller.currentValue != ModalBottomSheetValue.Hidden) {
        DisposableEffect(key1 = Unit) { onDispose { onClose.invoke() } }
    }

    ModalBottomSheetLayout(
        sheetState = controller,
        modifier = Modifier.alpha(if (state.isOpen) 1f else 0.9f),
        sheetShape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        sheetContent = bottomSheetContent,
        scrimColor = Color.Black.copy(alpha = 0.3f),
        content = content
    )
}