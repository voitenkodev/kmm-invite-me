package com.voitenko.dev.kmminviteme.android.newEvent

import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.lifecycle.viewModelScope
import com.voitenko.dev.kmminviteme.android.features.calendarPicker.CalendarPickerFeature
import com.voitenko.dev.kmminviteme.android.features.expandImagePicker.ExpandImagePickFeature
import com.voitenko.dev.kmminviteme.android.features.expandTextInput.ExpandInputFeature
import com.voitenko.dev.kmminviteme.android.FeatureViewModel
import com.voitenko.dev.kmminviteme.mvi.builder.FeatureTag
import com.voitenko.dev.kmminviteme.mvi.createFeatureBuilder
import com.voitenko.dev.kmminviteme.mvi.share

class NewEventVM : FeatureViewModel<NewEventVM.NewEventState>() {

    enum class TAG : FeatureTag {
        TITLE,
        DESCRIPTION,
        DATE,
        LOCATION,
        IMAGE,
        CALENDAR_PICKER,
    }

    override val processor = viewModelScope.createFeatureBuilder(NewEventState())
        .provide(TAG.TITLE) {
            ExpandInputFeature(this, it.title).share { copy(title = it) }
        }.provide(TAG.DESCRIPTION) {
            ExpandInputFeature(this, it.description).share { copy(description = it) }
        }.provide(TAG.DATE) {
            ExpandInputFeature(this, it.date).share { copy(date = it) }
        }.provide(TAG.LOCATION) {
            ExpandInputFeature(this, it.location).share { copy(location = it) }
        }.provide(TAG.IMAGE) {
            ExpandImagePickFeature(this, it.image).share { copy(image = it) }
        }.provide(TAG.CALENDAR_PICKER) {
            CalendarPickerFeature(this, it.calendarPicker).share { copy(calendarPicker = it) }
        }.build()
        .obtain<ExpandInputFeature.State>(TAG.TITLE) {
            if (it.input.text.isNotEmpty()) want(TAG.TITLE, ExpandInputFeature.Wish.HideError)
        }.obtain<ExpandInputFeature.State>(TAG.DESCRIPTION) {
            if (it.input.text.isNotEmpty()) want(TAG.DESCRIPTION, ExpandInputFeature.Wish.HideError)
        }.obtain<ExpandInputFeature.State>(TAG.DATE) {
            if (it.input.text.isNotEmpty()) want(TAG.DATE, ExpandInputFeature.Wish.HideError)
        }.obtain<ExpandInputFeature.State>(TAG.LOCATION) {
            if (it.input.text.isNotEmpty()) want(TAG.LOCATION, ExpandInputFeature.Wish.HideError)
        }.obtain<ExpandImagePickFeature.State>(TAG.IMAGE) {
            if (it.image.image != Uri.EMPTY) want(TAG.IMAGE, ExpandImagePickFeature.Wish.HideError)
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