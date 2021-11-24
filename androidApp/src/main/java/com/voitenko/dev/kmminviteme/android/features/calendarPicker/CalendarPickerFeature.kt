package com.voitenko.dev.kmminviteme.android.features.calendarPicker

import mvi.feature.Feature
import mvi.feature.Feature1
import mvi.feature.Reducer

class CalendarPickerFeature constructor(
    initial: State = State()
) : Feature1<CalendarPickerFeature.Wish, CalendarPickerFeature.State, Nothing>(
    initial = initial,
    reducer = ReducerImpl(),
) {

    sealed class Wish : Feature.Wish {
        object OpenSheet : Wish()
        object CloseSheet : Wish()
    }

    data class State(
        val isOpen: Boolean = false
    ) : Feature.State


    class ReducerImpl : Reducer<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            is Wish.OpenSheet -> state.copy(isOpen = true)
            is Wish.CloseSheet -> state.copy(isOpen = false)
        }
    }
}
