package com.voitenko.dev.kmminviteme.android.features.calendarPicker

import com.voitenko.dev.kmminviteme.mvi.feature.Actor
import com.voitenko.dev.kmminviteme.mvi.feature.Feature
import com.voitenko.dev.kmminviteme.mvi.feature.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flowOf

class CalendarPickerFeature constructor(
    scope: CoroutineScope,
    initial: State = State()
) : Feature<CalendarPickerFeature.Wish, CalendarPickerFeature.State, Nothing>(
    scope = scope,
    initial = initial,
    actor = ActorImpl(),
    reducer = ReducerImpl(),
) {

    sealed class Wish : Feature.Wish {
        object OpenSheet : Wish()
        object CloseSheet : Wish()
    }

    data class State(val isOpen: Boolean = false) : Feature.State

    class ActorImpl : Actor<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            else -> flowOf(wish)
        }
    }

    class ReducerImpl : Reducer<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            is Wish.OpenSheet -> state.copy(isOpen = true)
            is Wish.CloseSheet -> state.copy(isOpen = false)
        }
    }
}
