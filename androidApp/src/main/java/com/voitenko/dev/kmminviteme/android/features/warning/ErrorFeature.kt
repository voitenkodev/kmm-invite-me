package com.voitenko.dev.kmminviteme.android.features.warning

import com.voitenko.dev.kmminviteme.mvi.feature.Actor
import com.voitenko.dev.kmminviteme.mvi.feature.Feature
import com.voitenko.dev.kmminviteme.mvi.feature.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flowOf

class ErrorFeature(
    scope: CoroutineScope,
    initial: State = State()
) : Feature<ErrorFeature.Wish, ErrorFeature.State, Nothing>(
    scope = scope,
    initial = initial,
    actor = ActorImpl(),
    reducer = ReducerImpl(),
) {

    sealed class Wish : Feature.Wish {
        object Show : Wish()
        object Hide : Wish()
    }

    data class State(
        val isShowed: Boolean = false,
        val text: String = ""
    ) : Feature.State

    class ActorImpl : Actor<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            else -> flowOf(wish)
        }
    }

    class ReducerImpl : Reducer<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            is Wish.Show -> state.copy(isShowed = true)
            is Wish.Hide -> state.copy(isShowed = false)
        }
    }
}