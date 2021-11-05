package com.voitenko.dev.kmminviteme.android.features.image

import android.net.Uri
import com.voitenko.dev.kmminviteme.mvi.feature.Actor
import com.voitenko.dev.kmminviteme.mvi.feature.Feature
import com.voitenko.dev.kmminviteme.mvi.feature.NewsPublisher
import com.voitenko.dev.kmminviteme.mvi.feature.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flowOf

class ImagePickerFeature(
    scope: CoroutineScope,
    initial: State = State()
) : Feature<ImagePickerFeature.Wish, ImagePickerFeature.State, ImagePickerFeature.News>(
    scope = scope,
    initial = initial,
    actor = ActorImpl(),
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    sealed class Wish : Feature.Wish {
        data class SetImage(val image: Uri) : Wish()
        object Pick : Wish()
    }

    data class State(
        val image: Uri = Uri.EMPTY,
    ) : Feature.State

    sealed class News : Feature.News {
        object Pick : News()
    }

    class ActorImpl : Actor<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            else -> flowOf(wish)
        }
    }

    class ReducerImpl : Reducer<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            is Wish.SetImage -> state.copy(image = wish.image)
            Wish.Pick -> state
        }
    }

    class NewsPublisherImpl : NewsPublisher<Wish, State, News> {
        override fun invoke(effect: Wish, state: State) = when (effect) {
            Wish.Pick -> News.Pick
            else -> null
        }
    }
}
