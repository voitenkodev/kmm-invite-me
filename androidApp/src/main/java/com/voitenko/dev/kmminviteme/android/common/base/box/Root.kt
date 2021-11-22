package com.voitenko.dev.kmminviteme.android.common.base.box

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Root(
    modifier: Modifier = Modifier,
    header: (@Composable ColumnScope.() -> Unit)? = null,
    footer: (@Composable ColumnScope.() -> Unit)? = null,
    body: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier) {
        header?.invoke(this)
        Column(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 4.dp)
                .fillMaxSize()
                .weight(1f, false),
            content = body
        )
        Center(modifier = Modifier.fillMaxWidth()) {
            footer?.invoke(this)
        }
    }
}