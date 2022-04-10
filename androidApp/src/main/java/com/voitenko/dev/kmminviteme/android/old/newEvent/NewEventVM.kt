package com.voitenko.dev.kmminviteme.android.old.newEvent

import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.voitenko.dev.kmminviteme.android.FeatureViewModel
import com.voitenko.dev.kmminviteme.android.old.features.calendarPicker.CalendarPickerFeature
import com.voitenko.dev.kmminviteme.android.old.features.expandImagePicker.ExpandImagePickFeature
import com.voitenko.dev.kmminviteme.android.old.features.expandTextInput.ExpandInputFeature
import com.voitenko.dev.kmminviteme.android.old.features.requestButton.RequestButtonFeature
import mvi.MviCore

class NewEventVM : FeatureViewModel<NewEventVM.Event, NewEventVM.NewEventState>() {

    enum class TAG : MviCore.FeatureTag {
        TITLE, DESCRIPTION, DATE, LOCATION, IMAGE, CALENDAR_PICKER, REQUEST_BUTTON
    }

    override val processor = MviCore.Builder(root = NewEventState())
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
            tag = TAG.REQUEST_BUTTON,
            feature = { RequestButtonFeature(it.requestButton) },
            updateRoot = { copy(requestButton = it) }
        ).build(viewModelScope)

    sealed class Event : FeatureViewModel.Event {
        object ClickGotIt : Event()
        data class WriteTitle(val t: String) : Event()
        data class WriteDescription(val t: String) : Event()
        object ClickDate : Event()
        object ClickLocation : Event()
        object ClickImage : Event()
        data class PutImage(val image: Uri) : Event()
        object CloseInfo : Event()
    }

    override fun send(event: FeatureViewModel.Event) {
        when (event) {
            is Event.WriteTitle -> {
                processor.want(TAG.TITLE, ExpandInputFeature.Sync.SetText(event.t))
            }
            is Event.WriteDescription -> {
                processor.want(TAG.DESCRIPTION, ExpandInputFeature.Sync.SetText(event.t))
            }
            is Event.ClickDate -> {
                processor.want(TAG.CALENDAR_PICKER, CalendarPickerFeature.Sync.OpenSheet)
            }
            is Event.ClickLocation -> {
                processor.want(TAG.CALENDAR_PICKER, CalendarPickerFeature.Sync.OpenSheet)
            }
            is Event.ClickImage -> {
                processor.want(TAG.IMAGE, ExpandImagePickFeature.Side.Pick)
            }
            is Event.PutImage -> {
                processor.want(TAG.IMAGE, ExpandImagePickFeature.Sync.SetImage(event.image))
            }
            is Event.CloseInfo -> {
                processor.want(TAG.TITLE, ExpandInputFeature.Sync.Reboot)
                processor.want(TAG.DESCRIPTION, ExpandInputFeature.Sync.Reboot)
                processor.want(TAG.DATE, ExpandInputFeature.Sync.Reboot)
                processor.want(TAG.LOCATION, ExpandInputFeature.Sync.Reboot)
                processor.want(TAG.IMAGE, ExpandImagePickFeature.Sync.Reboot)
            }
            is Event.ClickGotIt -> {
                when {
                    state.title.expander.isOpened.not() -> {
                        processor.want(TAG.TITLE, ExpandInputFeature.Sync.Expand)
                        processor.want(
                            TAG.REQUEST_BUTTON,
                            RequestButtonFeature.Sync.SetTitle("Description")
                        )
                    }
                    state.description.expander.isOpened.not() -> {
                        processor.want(TAG.DESCRIPTION, ExpandInputFeature.Sync.Expand)
                        processor.want(
                            TAG.REQUEST_BUTTON,
                            RequestButtonFeature.Sync.SetTitle("Set Date")
                        )
                    }
                    state.date.expander.isOpened.not() -> {
                        processor.want(TAG.DATE, ExpandInputFeature.Sync.Expand)
                        processor.want(
                            TAG.REQUEST_BUTTON,
                            RequestButtonFeature.Sync.SetTitle("Set Location")
                        )
                    }
                    state.location.expander.isOpened.not() -> {
                        processor.want(TAG.LOCATION, ExpandInputFeature.Sync.Expand)
                        processor.want(
                            TAG.REQUEST_BUTTON,
                            RequestButtonFeature.Sync.SetTitle("Add Image")
                        )
                    }
                    state.image.expander.isOpened.not() -> {
                        processor.want(TAG.IMAGE, ExpandImagePickFeature.Sync.Expand)
                        processor.want(
                            TAG.REQUEST_BUTTON,
                            RequestButtonFeature.Sync.SetTitle("Save Event")
                        )
                    }
                    else -> {
                        processor.want(TAG.REQUEST_BUTTON, RequestButtonFeature.Async.CallRequest)
                        if (state.title.input.text.isEmpty()) {
                            processor.want(TAG.TITLE, ExpandInputFeature.Sync.ShowError)
                        }
                        if (state.description.input.text.isEmpty()) {
                            processor.want(TAG.DESCRIPTION, ExpandInputFeature.Sync.ShowError)
                        }
                        if (state.date.input.text.isEmpty()) {
                            processor.want(TAG.DATE, ExpandInputFeature.Sync.ShowError)
                        }
                        if (state.location.input.text.isEmpty()) {
                            processor.want(TAG.LOCATION, ExpandInputFeature.Sync.ShowError)
                        }
                        if (state.image.image.image == Uri.EMPTY) {
                            processor.want(TAG.IMAGE, ExpandImagePickFeature.Sync.ShowError)
                        }
                    }
                }
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
                expandHeight = (56 * 3.2).toInt()
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
            color = Color.Black,
            buttonState = RequestButtonFeature.State.ButtonState.Expanded,
        )
    )
}