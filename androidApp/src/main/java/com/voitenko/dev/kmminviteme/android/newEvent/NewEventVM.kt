package com.voitenko.dev.kmminviteme.android.newEvent

import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.lifecycle.viewModelScope
import com.voitenko.dev.kmminviteme.android.FeatureViewModel
import com.voitenko.dev.kmminviteme.android.features.calendarPicker.CalendarPickerFeature
import com.voitenko.dev.kmminviteme.android.features.expandImagePicker.ExpandImagePickFeature
import com.voitenko.dev.kmminviteme.android.features.expandTextInput.ExpandInputFeature
import com.voitenko.dev.kmminviteme.mvi.builder.FeatureTag
import com.voitenko.dev.kmminviteme.mvi.builder.MviProcessor
import com.voitenko.dev.kmminviteme.mvi.push

class NewEventVM : FeatureViewModel<NewEventVM.Event, NewEventVM.NewEventState>() {

    enum class TAG : FeatureTag { TITLE, DESCRIPTION, DATE, LOCATION, IMAGE, CALENDAR_PICKER }

    sealed class Event : FeatureViewModel.Event {
        object ClickGotIt : Event()
        data class WriteTitle(val t: String) : Event()
        data class WriteDescription(val t: String) : Event()
        object ClickDate : Event()
        object ClickLocation : Event()
        object ClickImage : Event()
        data class PutImage(val image: Uri) : Event()
    }

    override fun send(event: FeatureViewModel.Event) {
        when (event) {
            is Event.ClickGotIt -> {
                when {
                    state.title.expander.isOpened.not() -> {
                        want(TAG.TITLE, ExpandInputFeature.Wish.Expand)
                    }
                    state.description.expander.isOpened.not() -> {
                        want(TAG.DESCRIPTION, ExpandInputFeature.Wish.Expand)
                    }
                    state.date.expander.isOpened.not() -> {
                        want(TAG.DATE, ExpandInputFeature.Wish.Expand)
                    }
                    state.location.expander.isOpened.not() -> {
                        want(TAG.LOCATION, ExpandInputFeature.Wish.Expand)
                    }
                    state.image.expander.isOpened.not() -> {
                        want(TAG.IMAGE, ExpandImagePickFeature.Wish.Expand)
                    }
                    else -> Unit
                }
            }
            is Event.WriteTitle -> {
                want(TAG.TITLE, ExpandInputFeature.Wish.SetText(event.t))
            }
            is Event.WriteDescription -> {
                want(TAG.DESCRIPTION, ExpandInputFeature.Wish.SetText(event.t))
            }
            is Event.ClickDate -> {
                want(TAG.CALENDAR_PICKER, CalendarPickerFeature.Wish.OpenSheet)
            }
            is Event.ClickLocation -> {
                want(TAG.CALENDAR_PICKER, CalendarPickerFeature.Wish.OpenSheet)
            }
            is Event.ClickImage -> {
                want(TAG.IMAGE, ExpandImagePickFeature.Wish.Pick)
            }
            is Event.PutImage -> {
                want(TAG.IMAGE, ExpandImagePickFeature.Wish.SetImage(event.image))
            }
            else -> Unit
        }
    }

    override val processor = MviProcessor.FeatureProcessor(root = NewEventState())
        .feature(TAG.TITLE) {
            ExpandInputFeature(it.title) push { copy(title = it) }
        }.feature(TAG.DESCRIPTION) {
            ExpandInputFeature(it.description) push { copy(description = it) }
        }.feature(TAG.DATE) {
            ExpandInputFeature(it.date) push { copy(date = it) }
        }.feature(TAG.LOCATION) {
            ExpandInputFeature(it.location) push { copy(location = it) }
        }.feature(TAG.IMAGE) {
            ExpandImagePickFeature(it.image) push { copy(image = it) }
        }.feature(TAG.CALENDAR_PICKER) {
            CalendarPickerFeature(it.calendarPicker) push { copy(calendarPicker = it) }
        }.launchIn(viewModelScope)
        .automatically<ExpandInputFeature.State>(TAG.TITLE) {
            if (it.input.text.isNotEmpty())
                want(TAG.TITLE, ExpandInputFeature.Wish.HideError)
        }.automatically<ExpandInputFeature.State>(TAG.DESCRIPTION) {
            if (it.input.text.isNotEmpty())
                want(TAG.DESCRIPTION, ExpandInputFeature.Wish.HideError)
        }.automatically<ExpandInputFeature.State>(TAG.DATE) {
            if (it.input.text.isNotEmpty())
                want(TAG.DATE, ExpandInputFeature.Wish.HideError)
        }.automatically<ExpandInputFeature.State>(TAG.LOCATION) {
            if (it.input.text.isNotEmpty())
                want(TAG.LOCATION, ExpandInputFeature.Wish.HideError)
        }.automatically<ExpandImagePickFeature.State>(TAG.IMAGE) {
            if (it.image.image != Uri.EMPTY)
                want(TAG.IMAGE, ExpandImagePickFeature.Wish.HideError)
        }

    data class NewEventState(
        val title: ExpandInputFeature.State = ExpandInputFeature.State(
            error = ExpandInputFeature.State.Error(
                text = "ops, u forgot to put title",
                isShowed = false
            ),
            input = ExpandInputFeature.State.Input(
                placeholder = "Title...",
                text = "",
                isFocused = false,
                readOnly = false
            ),
            expander = ExpandInputFeature.State.Expander(
                isOpened = false,
                number = "1",
                notes = "You need to put title of event",
                expandHeight = (56 * 1.7).toInt(),
            )
        ),
        val description: ExpandInputFeature.State = ExpandInputFeature.State(
            error = ExpandInputFeature.State.Error(
                text = "ops, u forgot to put description",
                isShowed = false
            ),
            input = ExpandInputFeature.State.Input(
                placeholder = "Description...",
                text = "",
                isFocused = false,
                readOnly = false
            ),
            expander = ExpandInputFeature.State.Expander(
                isOpened = false,
                number = "2",
                notes = "After that add description",
                expandHeight = null,
            )
        ),
        val date: ExpandInputFeature.State = ExpandInputFeature.State(
            error = ExpandInputFeature.State.Error(
                text = "ops, u forgot to put date",
                isShowed = false
            ),
            input = ExpandInputFeature.State.Input(
                placeholder = "18:00,  14 Jun 2002...",
                text = "",
                isFocused = false,
                readOnly = true
            ),
            expander = ExpandInputFeature.State.Expander(
                isOpened = false,
                number = "3",
                notes = "Date and time should be in future",
                expandHeight = 56,
            )
        ),
        val location: ExpandInputFeature.State = ExpandInputFeature.State(
            error = ExpandInputFeature.State.Error(
                text = "ops, u forgot to put location",
                isShowed = false
            ),
            input = ExpandInputFeature.State.Input(
                placeholder = "4 Shady Rd. Brooklyn, NY 11223...",
                text = "",
                isFocused = false,
                readOnly = true
            ),
            expander = ExpandInputFeature.State.Expander(
                isOpened = false,
                number = "4",
                notes = "Pick the location of event",
                expandHeight = 56,
            )
        ),
        val image: ExpandImagePickFeature.State = ExpandImagePickFeature.State(
            error = ExpandImagePickFeature.State.Error(
                text = "ops, u forgot to put image",
                isShowed = false
            ),
            image = ExpandImagePickFeature.State.Image(
                image = Uri.EMPTY,
                placeHolder = Icons.Default.Image,
            ),
            expander = ExpandImagePickFeature.State.Expander(
                isOpened = false,
                number = "5",
                notes = "Add Image or Photo of event",
                expandHeight = (56 * 2.5).toInt(),
            )
        ),
        val calendarPicker: CalendarPickerFeature.State = CalendarPickerFeature.State(
            isOpen = false
        )
    )
}