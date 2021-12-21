package com.voitenko.dev.kmminviteme.android.features.expandTextInput

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mvi.AffectConventions
import mvi.Feature
import mvi.SyncReducer

class ExpandInputFeature(
    initial: State = State()
) : Feature<Nothing, ExpandInputFeature.Sync, Nothing, ExpandInputFeature.State>(
    initial = initial,
    syncReducer = SyncReducerImpl(initial),
    affectConventions = AffectConventionsImpl()
) {

    sealed class Sync : Wish.Sync {
        object Expand : Sync()
        object Collapse : Sync()
        data class SetText(val text: String) : Sync()
        object ShowError : Sync()
        object HideError : Sync()
        object Reboot : Sync()
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
            val isFocused: Boolean = false,
            val readOnly: Boolean = false
        )

        data class Expander(
            val isOpened: Boolean = false,
            val number: String = "",
            val notes: String = "",
            val expandHeight: Int? = null,
        )
    }

    class SyncReducerImpl(private val initial: State) : SyncReducer<Sync, State> {
        override fun invoke(wish: Sync, state: State) = when (wish) {
            is Sync.SetText -> state.copy(input = state.input.copy(text = wish.text))
            is Sync.Collapse -> state.copy(expander = state.expander.copy(isOpened = false))
            is Sync.Expand -> state.copy(expander = state.expander.copy(isOpened = true))
            is Sync.HideError -> state.copy(error = state.error.copy(isShowed = false))
            is Sync.ShowError -> state.copy(error = state.error.copy(isShowed = true))
            is Sync.Reboot -> initial
        }
    }

    class AffectConventionsImpl : AffectConventions<State> {

        override fun invoke(wish: Wish, state: State): Flow<Wish> = flow {
            validate(wish, state) { emit(Sync.HideError) }
        }

        private suspend fun validate(wish: Wish, state: State, action: suspend () -> Unit) =
            takeIf { wish is Sync.SetText && wish.text.isNotEmpty() && state.error.isShowed }
                ?.let { action.invoke() }
    }
}