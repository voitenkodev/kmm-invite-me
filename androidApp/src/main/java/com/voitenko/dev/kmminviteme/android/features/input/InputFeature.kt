package com.voitenko.dev.kmminviteme.android.features.input

import com.voitenko.dev.kmminviteme.mvi.feature.Actor
import com.voitenko.dev.kmminviteme.mvi.feature.Feature
import com.voitenko.dev.kmminviteme.mvi.feature.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flowOf

class InputFeature(
    scope: CoroutineScope,
    initial: State = State()
) : Feature<InputFeature.Wish, InputFeature.State, Nothing>(
    scope = scope,
    initial = initial,
    actor = ActorImpl(),
    reducer = ReducerImpl(),
) {

    sealed class Wish : Feature.Wish {
        data class SetText(val text: String) : Wish()
        object Submit : Wish()
    }

    data class State(
        val text: String = "",
        val placeholder: String = "",
        val isFocused: Boolean = false,
        val readOnly: Boolean = false
    ) : Feature.State

    class ActorImpl : Actor<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            else -> flowOf(wish)
        }
    }

    class ReducerImpl : Reducer<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            is Wish.SetText -> state.copy(text = wish.text)
            Wish.Submit -> state.copy(isFocused = true)
        }
    }
}
