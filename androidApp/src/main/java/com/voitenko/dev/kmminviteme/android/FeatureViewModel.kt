package com.voitenko.dev.kmminviteme.android

import androidx.lifecycle.ViewModel
import mvi.MviCore

typealias Splitter<Event, State> = (event: Event, state: State) -> Unit

abstract class FeatureViewModel<Event, Model> : ViewModel() {

    interface Event

    abstract fun send(event: FeatureViewModel.Event)

    abstract val processor: MviCore<Model>

    val state: Model get() = processor.root.value
}