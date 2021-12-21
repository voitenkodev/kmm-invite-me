package com.voitenko.dev.kmminviteme.android.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import mvi.MviCore

class ExampleViewModel : ViewModel() {

    val mviProcessor = MviCore.Builder(ExampleRoot())
        .build(viewModelScope)

    data class ExampleRoot(
        val text: String = "Hello World"
    )
}