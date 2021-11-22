package com.voitenko.dev.kmminviteme.android.common

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

@Composable
fun BottomSheetBlock(
    isOpen: Boolean = false,
    onClose: () -> Unit,
    content: @Composable () -> Unit,
    bottomSheetContent: @Composable ColumnScope.() -> Unit,
) {
    val controller: ModalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    LaunchedEffect(key1 = isOpen) {
        if (isOpen && controller.isVisible.not()) controller.show()
        else if (isOpen.not() && controller.isVisible) controller.hide()
    }

    BackHandler(isOpen) { onClose.invoke() }

    // https://stackoverflow.com/questions/69052660/listen-modalbottomsheetlayout-state-change-in-jetpack-compose

    if (controller.currentValue != ModalBottomSheetValue.Hidden) {
        DisposableEffect(key1 = Unit) { onDispose { onClose.invoke() } }
    }

    ModalBottomSheetLayout(
        sheetState = controller,
        modifier = Modifier.alpha(if (isOpen) 1f else 0.9f),
        sheetShape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        sheetContent = bottomSheetContent,
        scrimColor = Color.Black.copy(alpha = 0.3f),
        content = content
    )
}