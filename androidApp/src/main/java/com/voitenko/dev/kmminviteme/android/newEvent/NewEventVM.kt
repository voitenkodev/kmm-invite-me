package com.voitenko.dev.kmminviteme.android.newEvent

import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.lifecycle.viewModelScope
import com.voitenko.dev.kmminviteme.android.FeatureViewModel
import com.voitenko.dev.kmminviteme.android.features.calendarPicker.CalendarPickerFeature
import com.voitenko.dev.kmminviteme.android.features.expandImagePicker.ExpandImagePickFeature
import com.voitenko.dev.kmminviteme.android.features.expandTextInput.ExpandInputFeature
import com.voitenko.dev.kmminviteme.android.features.requestButton.RequestButtonFeature
import mvi.core.MviCore

class NewEventVM : FeatureViewModel<NewEventVM.Event, NewEventVM.NewEventState>() {

    enum class TAG : MviCore.FeatureTag {
        TITLE, DESCRIPTION, DATE, LOCATION, IMAGE, CALENDAR_PICKER, BUTTON_REQUEST
    }

    override val processor = MviCore.featureProcessor(root = NewEventState())
        .feature(
            tag = TAG.TITLE,
            feature = { ExpandInputFeature(it.title) },
            updateRoot = { copy(title = it) }
        ).feature(
            tag = TAG.DESCRIPTION,
            feature = { ExpandInputFeature(it.description) },
            updateRoot = { copy(description = it) }
        ).feature(
            tag = TAG.DATE,
            feature = { ExpandInputFeature(it.date) },
            updateRoot = { copy(date = it) }
        ).feature(
            tag = TAG.LOCATION,
            feature = { ExpandInputFeature(it.location) },
            updateRoot = { copy(location = it) }
        ).feature(
            tag = TAG.IMAGE,
            feature = { ExpandImagePickFeature(it.image) },
            updateRoot = { copy(image = it) }
        ).feature(
            tag = TAG.CALENDAR_PICKER,
            feature = { CalendarPickerFeature(it.calendarPicker) },
            updateRoot = { copy(calendarPicker = it) }
        ).feature(
            tag = TAG.BUTTON_REQUEST,
            feature = { RequestButtonFeature(it.requestButton) },
            updateRoot = { copy(requestButton = it) }
        ).launchIn(viewModelScope)
        .postProcessing<ExpandInputFeature.State>(TAG.TITLE) {
            if (it.input.text.isNotEmpty())
                want(TAG.TITLE, ExpandInputFeature.Wish.HideError)
        }.postProcessing<ExpandInputFeature.State>(TAG.DESCRIPTION) {
            if (it.input.text.isNotEmpty())
                want(TAG.DESCRIPTION, ExpandInputFeature.Wish.HideError)
        }.postProcessing<ExpandInputFeature.State>(TAG.DATE) {
            if (it.input.text.isNotEmpty())
                want(TAG.DATE, ExpandInputFeature.Wish.HideError)
        }.postProcessing<ExpandInputFeature.State>(TAG.LOCATION) {
            if (it.input.text.isNotEmpty())
                want(TAG.LOCATION, ExpandInputFeature.Wish.HideError)
        }.postProcessing<ExpandImagePickFeature.State>(TAG.IMAGE) {
            if (it.image.image != Uri.EMPTY)
                want(TAG.IMAGE, ExpandImagePickFeature.Wish.HideError)
        }

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
                        want(
                            TAG.BUTTON_REQUEST,
                            RequestButtonFeature.Wish.SetTitle("Set Description")
                        )
                    }
                    state.description.expander.isOpened.not() -> {
                        want(TAG.DESCRIPTION, ExpandInputFeature.Wish.Expand)
                        want(TAG.BUTTON_REQUEST, RequestButtonFeature.Wish.SetTitle("Set Date"))
                    }
                    state.date.expander.isOpened.not() -> {
                        want(TAG.DATE, ExpandInputFeature.Wish.Expand)
                        want(TAG.BUTTON_REQUEST, RequestButtonFeature.Wish.SetTitle("Set Location"))
                    }
                    state.location.expander.isOpened.not() -> {
                        want(TAG.LOCATION, ExpandInputFeature.Wish.Expand)
                        want(TAG.BUTTON_REQUEST, RequestButtonFeature.Wish.SetTitle("Add Image"))
                    }
                    state.image.expander.isOpened.not() -> {
                        want(TAG.IMAGE, ExpandImagePickFeature.Wish.Expand)
                        want(TAG.BUTTON_REQUEST, RequestButtonFeature.Wish.SetTitle("Save Event"))
                    }
                    else -> {
                        want(TAG.BUTTON_REQUEST, RequestButtonFeature.Wish.CallRequest)
                        if (state.title.input.text.isEmpty()) {
                            want(TAG.TITLE, ExpandInputFeature.Wish.ShowError)
                        }
                        if (state.description.input.text.isEmpty()) {
                            want(TAG.DESCRIPTION, ExpandInputFeature.Wish.ShowError)
                        }
                        if (state.date.input.text.isEmpty()) {
                            want(TAG.DATE, ExpandInputFeature.Wish.ShowError)
                        }
                        if (state.location.input.text.isEmpty()) {
                            want(TAG.LOCATION, ExpandInputFeature.Wish.ShowError)
                        }
                        if (state.image.image.image == Uri.EMPTY) {
                            want(TAG.IMAGE, ExpandImagePickFeature.Wish.ShowError)
                        }
                    }
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
        ),
        val requestButton: RequestButtonFeature.State = RequestButtonFeature.State(
            text = "Got It",
            isOpened = true
        )
    )
}