package com.voitenko.dev.kmminviteme.android.features.requestButton

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import mvi.feature.AsyncReducer
import mvi.feature.Feature
import mvi.feature.SyncReducer

class RequestButtonFeature(
    initial: State = State()
) : Feature<RequestButtonFeature.Async, RequestButtonFeature.Sync, RequestButtonFeature.Side, RequestButtonFeature.State>(
    initial = initial,
    asyncReducer = AsyncReducerImpl(),
    syncReducer = SyncReducerImpl()
) {

    sealed class Async : Wish.Async {
        object CallRequest : Async()
    }

    sealed class Side : Wish.Side {
        object ShowToast : Side()
    }

    sealed class Sync : Wish.Sync {
        data class SetTitle(val text: String) : Sync()
        data class SetColor(val color: Color) : Sync()
        object Expand : Sync()
        object Collapse : Sync()
    }

    data class State(
        val text: String = "",
        val color: Color = Color.Black,
        val buttonState: ButtonState = ButtonState.Expanded,
    ) : Feature.State {
        enum class ButtonState { Expanded, Collapsed }
    }

    class AsyncReducerImpl : AsyncReducer<Async, State, Sync> {
        override fun invoke(wish: Async, state: State) = when (wish) {
            is Async.CallRequest -> flow {
                emit(Sync.Collapse)
                emit(Sync.SetTitle(""))
                emit(Sync.SetColor(Color.LightGray))

                delay(2000)
                emit(Sync.Expand)
                emit(Sync.SetTitle("Success"))
                emit(Sync.SetColor(Color.Green))

                delay(2000)
                emit(Sync.Collapse)
                emit(Sync.SetTitle(""))
                emit(Sync.SetColor(Color.Cyan))

                delay(2000)
                emit(Sync.Expand)
                emit(Sync.SetTitle("Success"))
                emit(Sync.SetColor(Color.Red))
            }
        }
    }

    class SyncReducerImpl : SyncReducer<Sync, State> {
        override fun invoke(wish: Sync, state: State) = when (wish) {
            is Sync.SetTitle -> state.copy(text = wish.text)
            is Sync.Collapse -> state.copy(buttonState = State.ButtonState.Collapsed)
            is Sync.Expand -> state.copy(buttonState = State.ButtonState.Expanded)
            is Sync.SetColor -> state.copy(color = wish.color)
        }
    }
}
