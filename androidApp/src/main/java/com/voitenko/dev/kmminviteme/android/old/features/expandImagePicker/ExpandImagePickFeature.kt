package com.voitenko.dev.kmminviteme.android.old.features.expandImagePicker

import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mvi.AffectConventions
import mvi.Feature
import mvi.SyncReducer

class ExpandImagePickFeature(
    initial: State = State()
) : Feature<Nothing, ExpandImagePickFeature.Sync, ExpandImagePickFeature.Side, ExpandImagePickFeature.State>(
    initial = initial,
    syncReducer = SyncReducerImpl(initial),
    affectConventions = AffectConventionsImpl()
) {

    sealed class Sync : Wish.Sync {
        object Expand : Sync()
        object Collapse : Sync()
        object ShowError : Sync()
        object HideError : Sync()
        data class SetImage(val image: Uri) : Sync()
        object Reboot : Sync()
    }

    sealed class Side : Wish.Side {
        object Pick : Side()
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

    class SyncReducerImpl(private val initial: State) : SyncReducer<Sync, State> {
        override fun invoke(wish: Sync, state: State) = when (wish) {
            is Sync.Collapse -> state.copy(expander = state.expander.copy(isOpened = false))
            is Sync.Expand -> state.copy(expander = state.expander.copy(isOpened = true))
            is Sync.ShowError -> state.copy(error = state.error.copy(isShowed = true))
            is Sync.HideError -> state.copy(error = state.error.copy(isShowed = false))
            is Sync.SetImage -> state.copy(image = state.image.copy(image = wish.image))
            is Sync.Reboot -> initial
        }
    }

    class AffectConventionsImpl : AffectConventions<State> {

        override fun invoke(wish: Wish, state: State): Flow<Wish> = flow {
            validate(wish, state) { emit(Sync.HideError) }
        }

        private suspend fun validate(wish: Wish, state: State, action: suspend () -> Unit) =
            takeIf { wish is Sync.SetImage && wish.image != Uri.EMPTY && state.error.isShowed }
                ?.let { action.invoke() }
    }
}
