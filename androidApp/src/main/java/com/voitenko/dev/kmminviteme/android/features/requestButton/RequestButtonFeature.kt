package com.voitenko.dev.kmminviteme.android.features.requestButton

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import mvi.feature.Actor
import mvi.feature.Feature
import mvi.feature.Reducer

class RequestButtonFeature(
    initial: State = State()
) : Feature<RequestButtonFeature.Wish, RequestButtonFeature.State, Nothing>(
    initial = initial,
    actor = ActorImpl(),
    reducer = ReducerImpl(),
) {

    sealed class Wish : Feature.Wish {
        object Expand : Wish()
        object Collapse : Wish()
        data class SetTitle(val text: String) : Wish()
        data class CallRequest(val request: State.Request) : Wish()
    }

    data class State(
        val text: String = "",
        val request: Request = Request.DEFAULT,
        val isOpened: Boolean = false,
    ) : Feature.State {
        enum class Request(val color: Color) {
            SUCCESS(Color.Green), PROGRESS(Color.Yellow), ERROR(Color.Red), DEFAULT(Color.Black)
        }
    }

    class ActorImpl : Actor<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            is Wish.CallRequest -> flow {
                emit(Wish.CallRequest(State.Request.PROGRESS))
                emit(Wish.Collapse)
//                kotlinx.coroutines.delay(500)
//                emit(Wish.CallRequest(State.Request.SUCCESS))
                emit(Wish.Expand)
            }
            else -> flowOf(wish)
        }
    }

    class ReducerImpl : Reducer<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            is Wish.SetTitle -> state.copy(text = wish.text)
            Wish.Expand -> state.copy(isOpened = true)
            Wish.Collapse -> state.copy(isOpened = false)
            is Wish.CallRequest -> state.copy(request = wish.request)
        }
    }
}
