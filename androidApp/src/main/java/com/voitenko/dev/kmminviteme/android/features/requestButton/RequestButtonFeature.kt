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
        object CallRequest : Wish()
    }

    sealed class Effect : Feature.Effect {
        data class SetTitle(val text: String) : Effect()

        object ProgressRequest : Effect()
        object SuccessRequest : Effect()
        object ErrorRequest : Effect()
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

    class ActorImpl : Actor<Wish, State, Effect> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            is Wish.CallRequest -> flow {
                emit(Effect.ProgressRequest)
                delay(1000)
                emit(Effect.SuccessRequest)
            }
            is Wish.SetTitle -> flowOf(Effect.SetTitle(wish.text))
        }
    }

    class ReducerImpl : Reducer<Effect, State> {
        override fun invoke(effect: Effect, state: State) = when (effect) {
            is Effect.SetTitle -> state.copy(text = effect.text)
            Effect.ErrorRequest -> state.copy(request = State.Request.ERROR, isOpened = true)
            Effect.SuccessRequest -> state.copy(request = State.Request.SUCCESS, isOpened = true)
            Effect.ProgressRequest -> state.copy(request = State.Request.SUCCESS, isOpened = false)
        }
    }
}
