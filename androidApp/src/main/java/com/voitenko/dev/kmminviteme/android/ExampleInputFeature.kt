package com.voitenko.dev.kmminviteme.android

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mvi.AffectConventions
import mvi.Feature
import mvi.SyncReducer

class ExampleInputFeature(
    initial: State = State()
) : Feature<Nothing, ExampleInputFeature.Sync, Nothing, ExampleInputFeature.State>(
    initial = initial,
    syncReducer = SyncReducerImpl(),
    affectConventions = DistributorImpl()
) {

    sealed class Sync : Wish.Sync {
        object Expand : Sync()
        object Collapse : Sync()
        data class SetText(val text: String) : Sync()
        object ShowError : Sync()
        object HideError : Sync()
    }

    data class State(
        val expander: Expander = Expander(),
        val input: Input = Input(),
        val error: Error = Error()
    ) : Feature.State {

        data class Error(
            val text: String = "",
            val isShowed: Boolean = false
        )

        data class Input(
            val placeholder: String = "",
            val text: String = "",
        )

        data class Expander(
            val isOpened: Boolean = false,
            val number: String = "",
            val notes: String = "",
            val expandHeight: Int? = null,
        )
    }

    class SyncReducerImpl : SyncReducer<Sync, State> {
        override fun invoke(wish: Sync, state: State) = when (wish) {
            is Sync.SetText -> state.copy(input = state.input.copy(text = wish.text))
            is Sync.Collapse -> state.copy(expander = state.expander.copy(isOpened = false))
            is Sync.Expand -> state.copy(expander = state.expander.copy(isOpened = true))
            is Sync.HideError -> state.copy(error = state.error.copy(isShowed = false))
            is Sync.ShowError -> state.copy(error = state.error.copy(isShowed = true))
        }
    }

    class DistributorImpl : AffectConventions<State> {

        override fun invoke(wish: Wish, state: State): Flow<Wish> = flow {
            validate(wish, state) { emit(Sync.HideError) }
        }

        private suspend fun validate(wish: Wish, state: State, action: suspend () -> Unit) =
            takeIf { wish is Sync.SetText && wish.text.isNotBlank() && state.error.isShowed }
                ?.let { action.invoke() }
    }
}
