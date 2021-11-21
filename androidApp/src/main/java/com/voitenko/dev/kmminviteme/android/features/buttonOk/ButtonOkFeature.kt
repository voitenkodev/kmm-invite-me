package com.voitenko.dev.kmminviteme.android.features.buttonOk

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.flowOf
import mvi.feature.Actor
import mvi.feature.Feature
import mvi.feature.Reducer

class ButtonOkFeature(
    initial: State = State()
) : Feature<ButtonOkFeature.Wish, ButtonOkFeature.State, Nothing>(
    initial = initial,
    actor = ActorImpl(),
    reducer = ReducerImpl(),
) {

    sealed class Wish : Feature.Wish {
        data class SetTitle(val text: String) : Wish()
        data class SetBackground(val color: Color) : Wish()
    }

    data class State(
        val text: String = "",
        val color: Color = Color.Black
    ) : Feature.State

    class ActorImpl : Actor<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            else -> flowOf(wish)
        }
    }

    class ReducerImpl : Reducer<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            is Wish.SetTitle -> state.copy(text = wish.text)
            is Wish.SetBackground -> state.copy(color = wish.color)
        }
    }
}
