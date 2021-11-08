package com.voitenko.dev.kmminviteme.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voitenko.dev.kmminviteme.mvi.builder.FeatureBuilder
import com.voitenko.dev.kmminviteme.mvi.builder.FeatureTag
import com.voitenko.dev.kmminviteme.mvi.feature.Feature
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class FeatureViewModel<Model> : ViewModel() {

    abstract val processor: FeatureBuilder<Model>

    val state: Model get() = processor.state.value

    fun want(tag: FeatureTag, wish: Feature.Wish) =
        processor.want(tag = tag, wish = wish)

    fun <News : Feature.News> news(tag: FeatureTag, lambda: (News) -> Unit) =
        processor.news<News>(tag = tag).onEach { lambda.invoke(it) }.launchIn(viewModelScope)
}