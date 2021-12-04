package com.voitenko.dev.kmminviteme.android.features.calendarPicker

import mvi.feature.Feature
import mvi.feature.SyncReducer

class CalendarPickerFeature constructor(
    initial: State = State()
) : Feature<Nothing, CalendarPickerFeature.Sync, Nothing, CalendarPickerFeature.State>(
    initial = initial,
    syncReducer = SyncReducerImpl()
) {

    sealed class Sync : Wish.Sync {
        object OpenSheet : Sync()
        object CloseSheet : Sync()
    }

    data class State(
        val isOpen: Boolean = false
    ) : Feature.State

    class SyncReducerImpl : SyncReducer<Sync, State> {
        override fun invoke(wish: Sync, state: State) = when (wish) {
            is Sync.OpenSheet -> state.copy(isOpen = true)
            is Sync.CloseSheet -> state.copy(isOpen = false)
        }
    }
}
