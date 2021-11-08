package com.voitenko.dev.kmminviteme.android.features.expandImagePicker

import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.ui.graphics.vector.ImageVector
import com.voitenko.dev.kmminviteme.mvi.feature.Actor
import com.voitenko.dev.kmminviteme.mvi.feature.Feature
import com.voitenko.dev.kmminviteme.mvi.feature.NewsPublisher
import com.voitenko.dev.kmminviteme.mvi.feature.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flowOf

class ExpandImagePickFeature(
    scope: CoroutineScope,
    initial: State = State()
) : Feature<ExpandImagePickFeature.Wish, ExpandImagePickFeature.State, ExpandImagePickFeature.News>(
    scope = scope,
    initial = initial,
    actor = ActorImpl(),
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    sealed class Wish : Feature.Wish {
        object Expand : Wish()
        object Collapse : Wish()
        object ShowError : Wish()
        object HideError : Wish()
        data class SetImage(val image: Uri) : Wish()
        object Pick : Wish()
    }

    sealed class News : Feature.News {
        object Pick : News()
    }

    data class State(
        val expander: Expander = Expander(),
        val image: Image = Image(),
        val error: Error = Error()
    ) : Feature.State {

        data class Error(
            val text: String = "ops, u forgot to put title",
            val isShowed: Boolean = false
        )

        data class Image(
            val image: Uri = Uri.EMPTY,
            val placeHolder: ImageVector = Icons.Default.Image,
        )

        data class Expander(
            val isOpened: Boolean = false,
            val number: String = "1",
            val notes: String = "You need to put title of event",
            val expandHeight: Int? = null,
        )
    }

    class ActorImpl : Actor<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            else -> flowOf(wish)
        }
    }

    class ReducerImpl : Reducer<Wish, State> {
        override fun invoke(wish: Wish, state: State) = when (wish) {
            Wish.Collapse -> state.copy(expander = state.expander.copy(isOpened = false))
            Wish.Expand -> state.copy(expander = state.expander.copy(isOpened = true))
            is Wish.ShowError -> state.copy(error = state.error.copy(isShowed = true))
            is Wish.HideError -> state.copy(error = state.error.copy(isShowed = false))
            is Wish.SetImage -> state.copy(image = state.image.copy(image = wish.image))
            Wish.Pick -> state
        }
    }

    class NewsPublisherImpl :
        NewsPublisher<Wish, State, News> {
        override fun invoke(effect: Wish, state: State) = when (effect) {
            Wish.Pick -> News.Pick
            else -> null
        }
    }
}
