package com.voitenko.dev.kmminviteme.android.example

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ExampleScreen(vm: ExampleViewModel = viewModel()) {
    val state = vm.mviProcessor.root.collectAsState()
}