package com.voitenko.dev.kmminviteme.android.features.expander

import com.voitenko.dev.kmminviteme.mvi.feature.Actor
import com.voitenko.dev.kmminviteme.mvi.feature.Feature
import com.voitenko.dev.kmminviteme.mvi.feature.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flowOf

class ExpanderFeature(
    scope: CoroutineScope,
    initial: State = State()
) : Feature<ExpanderFeature.Wish, ExpanderFeature.State, Nothing>(
    scope = scope,
    initial = initial,
    actor = ActorImpl(),
    reducer = ReducerImpl(),
) {

    sealed class Wish : Feature.Wish {
        object Expand : Wish()
        object Collapse : Wish()
    }

    data class State(
        val isOpened: Boolean = false,
        val notes: String = "",
        val number: String = "",
        val expandHeight: Int? = null,
    ) : Feature.State

    class ActorImpl : Actor<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            else -> flowOf(wish)
        }
    }

    class ReducerImpl : Reducer<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            Wish.Collapse -> state.copy(isOpened = false)
            Wish.Expand -> state.copy(isOpened = true)
        }
    }
}
