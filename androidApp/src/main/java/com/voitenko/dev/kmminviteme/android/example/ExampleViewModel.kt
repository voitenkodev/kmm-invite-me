package com.voitenko.dev.kmminviteme.android.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import mvi.core.MviCore

class ExampleViewModel : ViewModel() {

    val mviProcessor = MviCore.featureProcessor(ExampleRoot())
        .launchIn(viewModelScope)

    data class ExampleRoot(
        val text: String = "Hello World"
    )
}