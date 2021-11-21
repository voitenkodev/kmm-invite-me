package com.voitenko.dev.kmminviteme.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import mvi.core.MviCore
import mvi.feature.Feature

typealias Splitter<Event, State> = (event: Event, state: State) -> Unit

abstract class FeatureViewModel<Event, Model> : ViewModel() {

    interface Event

    abstract fun send(event: FeatureViewModel.Event)

    abstract val processor: MviCore<Model>

    val state: Model get() = processor.state.value

    fun want(tag: MviCore.FeatureTag, wish: Feature.Wish) =
        processor.want(tag = tag, wish = wish)

    fun <News : Feature.News> news(tag: MviCore.FeatureTag, lambda: (News) -> Unit) =
        processor.news<News>(tag = tag)
            ?.onEach { lambda.invoke(it) }
            ?.launchIn(viewModelScope)
}