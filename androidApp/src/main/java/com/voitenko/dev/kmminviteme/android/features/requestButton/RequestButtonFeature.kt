package com.voitenko.dev.kmminviteme.android.features.requestButton

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import mvi.feature.Actor
import mvi.feature.Feature
import mvi.feature.Feature2
import mvi.feature.Reducer

class RequestButtonFeature(
    initial: State = State()
) : Feature2<RequestButtonFeature.Wish, RequestButtonFeature.Effect, RequestButtonFeature.State, Nothing>(
    initial = initial,
    actor = ActorImpl(),
    reducer = ReducerImpl(),
) {

    sealed class Wish : Feature.Wish {
        data class SetTitle(val text: String) : Wish()
        data class SetColor(val color: Color) : Wish()
        object Expand : Wish()
        object Collapse : Wish()
        object CallRequest : Wish()
    }

    sealed class Effect : Feature.Effect {
        data class SetTitle(val text: String) : Effect()
        data class SetColor(val color: Color) : Effect()
        data class CallRequest(val request: State.Request) : Effect()
        object Expand : Effect()
        object Collapse : Effect()
    }

    data class State(
        val text: String = "",
        val color: Color = Color.Black,
        val isOpened: Boolean = false,
        val request: Request = Request.DEFAULT,
    ) : Feature.State {
        enum class Request {
            SUCCESS, PROGRESS, ERROR, DEFAULT
        }
    }

    class ActorImpl : Actor<Wish, State, Effect> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            is Wish.CallRequest -> flow {
                emit(Effect.CallRequest(State.Request.PROGRESS))
                delay(400)
                emit(Effect.CallRequest(State.Request.SUCCESS))
                delay(1000)
                emit(Effect.CallRequest(State.Request.PROGRESS))
                delay(200)
                emit(Effect.CallRequest(State.Request.ERROR))
            }
            is Wish.SetTitle -> flowOf(Effect.SetTitle(wish.text))
            is Wish.Collapse -> flowOf(Effect.Collapse)
            is Wish.Expand -> flowOf(Effect.Expand)
            is Wish.SetColor -> flowOf(Effect.SetColor(wish.color))
        }
    }

    class ReducerImpl : Reducer<Effect, State> {
        override fun invoke(effect: Effect, state: State) = when (effect) {
            is Effect.SetTitle -> state.copy(text = effect.text)
            is Effect.CallRequest -> state.copy(request = effect.request)
            is Effect.Collapse -> state.copy(isOpened = false)
            is Effect.Expand -> state.copy(isOpened = true)
            is Effect.SetColor -> state.copy(color = effect.color)
        }
    }
}
