package com.voitenko.dev.kmminviteme.android.features.expandTextInput

import kotlinx.coroutines.flow.flowOf
import mvi.feature.Actor
import mvi.feature.Feature
import mvi.feature.Reducer

class ExpandInputFeature(
    initial: State = State()
) : Feature<ExpandInputFeature.Wish, ExpandInputFeature.State, Nothing>(
    initial = initial,
    actor = ActorImpl(),
    reducer = ReducerImpl(),
) {

    sealed class Wish : Feature.Wish {
        object Expand : Wish()
        object Collapse : Wish()
        data class SetText(val text: String) : Wish()
        object ShowError : Wish()
        object HideError : Wish()
    }

    data class State(
        val expander: Expander = Expander(),
        val input: Input = Input(),
        val error: Error = Error()
    ) : Feature.State {

        data class Error(
            val text: String = "ops, u forgot to put title",
            val isShowed: Boolean = false
        )

        data class Input(
            val placeholder: String = "Title...",
            val text: String = "",
            val isFocused: Boolean = false,
            val readOnly: Boolean = false
        )

        data class Expander(
            val isOpened: Boolean = false,
            val number: String = "1",
            val notes: String = "You need to put title of event",
            val expandHeight: Int? = null,
        )
    }

    class ActorImpl : Actor<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            else -> flowOf(wish)
        }
    }

    class ReducerImpl : Reducer<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            is Wish.SetText -> state.copy(input = state.input.copy(text = wish.text))
            Wish.Collapse -> state.copy(expander = state.expander.copy(isOpened = false))
            Wish.Expand -> state.copy(expander = state.expander.copy(isOpened = true))
            is Wish.ShowError -> state.copy(error = state.error.copy(isShowed = true))
            is Wish.HideError -> state.copy(error = state.error.copy(isShowed = false))
        }
    }
}
