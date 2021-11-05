package com.voitenko.dev.kmminviteme.android.screens.newevent

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.voitenko.dev.kmminviteme.android.features.bottomSheet.BottomSheetFeature
import com.voitenko.dev.kmminviteme.android.features.expander.ExpanderFeature
import com.voitenko.dev.kmminviteme.android.features.image.ImagePickerFeature
import com.voitenko.dev.kmminviteme.android.features.input.InputFeature
import com.voitenko.dev.kmminviteme.android.features.warning.ErrorFeature
import com.voitenko.dev.kmminviteme.android.mvi.FeatureViewModel
import com.voitenko.dev.kmminviteme.mvi.builder.FeatureTag
import com.voitenko.dev.kmminviteme.mvi.createFeatureBuilder
import com.voitenko.dev.kmminviteme.mvi.share
import kotlinx.coroutines.FlowPreview

class NewEventViewModel : FeatureViewModel<NewEventViewModel.NewEventState>() {

    enum class TAG : FeatureTag {
        BOTTOM_SHEET,
        TITLE_EXPAND, TITLE_INPUT, TITLE_ERROR,
        DESCRIPTION_EXPAND, DESCRIPTION_INPUT, DESCRIPTION_ERROR,
        DATE_EXPAND, DATE_INPUT, DATE_ERROR,
        LOCATION_EXPAND, LOCATION_INPUT, LOCATION_ERROR,
        IMAGE_EXPAND, IMAGE_PICKER, IMAGE_ERROR
    }

    override val processor = viewModelScope.createFeatureBuilder(NewEventState())
        .provide(TAG.BOTTOM_SHEET) {
            BottomSheetFeature(this, it.bottomSheet)
                .share { copy(bottomSheet = it) }
        }.provide(TAG.TITLE_EXPAND) {
            ExpanderFeature(this, it.title.expander)
                .share { copy(title = title.copy(expander = it)) }
        }.provide(TAG.DESCRIPTION_EXPAND) {
            ExpanderFeature(this, it.description.expander)
                .share { copy(description = description.copy(expander = it)) }
        }.provide(TAG.DATE_EXPAND) {
            ExpanderFeature(this, it.date.expander)
                .share { copy(date = date.copy(expander = it)) }
        }.provide(TAG.LOCATION_EXPAND) {
            ExpanderFeature(this, it.location.expander)
                .share { copy(location = location.copy(expander = it)) }
        }.provide(TAG.IMAGE_EXPAND) {
            ExpanderFeature(this, it.image.expander)
                .share { copy(image = image.copy(expander = it)) }
        }.provide(TAG.TITLE_INPUT) {
            InputFeature(this, it.title.input)
                .share { copy(title = title.copy(input = it)) }
        }.provide(TAG.DESCRIPTION_INPUT) {
            InputFeature(this, it.description.input)
                .share { copy(description = description.copy(input = it)) }
        }.provide(TAG.DATE_INPUT) {
            InputFeature(this, it.date.input)
                .share { copy(date = date.copy(input = it)) }
        }.provide(TAG.LOCATION_INPUT) {
            InputFeature(this, it.location.input)
                .share { copy(location = location.copy(input = it)) }
        }.provide(TAG.IMAGE_PICKER) {
            ImagePickerFeature(this, it.image.picker)
                .share { copy(image = image.copy(picker = it)) }
        }.provide(TAG.TITLE_ERROR) {
            ErrorFeature(this, it.title.error)
                .share { copy(title = title.copy(error = it)) }
        }.provide(TAG.DESCRIPTION_ERROR) {
            ErrorFeature(this, it.description.error)
                .share { copy(description = description.copy(error = it)) }
        }.provide(TAG.DATE_ERROR) {
            ErrorFeature(this, it.date.error)
                .share { copy(date = date.copy(error = it)) }
        }.provide(TAG.LOCATION_ERROR) {
            ErrorFeature(this, it.location.error)
                .share { copy(location = location.copy(error = it)) }
        }.provide(TAG.IMAGE_ERROR) {
            ErrorFeature(this, it.image.error)
                .share { copy(image = image.copy(error = it)) }
        }.build()
        .obtain<InputFeature.State>(TAG.TITLE_INPUT) {
            if (it.text.isNotEmpty()) want(TAG.TITLE_ERROR, ErrorFeature.Wish.Hide)
        }.obtain<InputFeature.State>(TAG.DESCRIPTION_INPUT) {
            if (it.text.isNotEmpty()) want(TAG.DESCRIPTION_ERROR, ErrorFeature.Wish.Hide)
        }.obtain<InputFeature.State>(TAG.LOCATION_INPUT) {
            if (it.text.isNotEmpty()) want(TAG.LOCATION_ERROR, ErrorFeature.Wish.Hide)
        }.obtain<InputFeature.State>(TAG.DATE_INPUT) {
            if (it.text.isNotEmpty()) want(TAG.DATE_ERROR, ErrorFeature.Wish.Hide)
        }.obtain<ImagePickerFeature.State>(TAG.IMAGE_PICKER) {
            if (it.image != Uri.EMPTY) want(TAG.IMAGE_ERROR, ErrorFeature.Wish.Hide)
        }

    @FlowPreview
    fun want(f: ExpanderFeature.Wish.Expand) {
        if (processor.state.value.title.expander.isOpened.not()) {
            processor.want(TAG.TITLE_EXPAND, f)
            return
        }
        if (processor.state.value.description.expander.isOpened.not()) {
            processor.want(TAG.DESCRIPTION_EXPAND, f)
            return
        }
        if (processor.state.value.date.expander.isOpened.not()) {
            processor.want(TAG.DATE_EXPAND, f)
            return
        }
        if (processor.state.value.location.expander.isOpened.not()) {
            processor.want(TAG.LOCATION_EXPAND, f)
            return
        }
        if (processor.state.value.image.expander.isOpened.not()) {
            processor.want(TAG.IMAGE_EXPAND, f)
            return
        }

        if (
            processor.state.value.title.expander.isOpened &&
            processor.state.value.description.expander.isOpened &&
            processor.state.value.date.expander.isOpened &&
            processor.state.value.location.expander.isOpened &&
            processor.state.value.image.expander.isOpened
        ) {
            if (processor.state.value.title.input.text.isEmpty())
                processor.want(TAG.TITLE_ERROR, ErrorFeature.Wish.Show)
            if (processor.state.value.description.input.text.isEmpty())
                processor.want(TAG.DESCRIPTION_ERROR, ErrorFeature.Wish.Show)
            if (processor.state.value.date.input.text.isEmpty())
                processor.want(TAG.DATE_ERROR, ErrorFeature.Wish.Show)
            if (processor.state.value.location.input.text.isEmpty())
                processor.want(TAG.LOCATION_ERROR, ErrorFeature.Wish.Show)
            if (processor.state.value.image.picker.image == Uri.EMPTY)
                processor.want(TAG.IMAGE_ERROR, ErrorFeature.Wish.Show)
            return
        }
    }

    data class NewEventState(
        val title: Title = Title(),
        val description: Description = Description(),
        val date: Date = Date(),
        val location: Location = Location(),
        val image: Image = Image(),
        val bottomSheet: BottomSheetFeature.State = BottomSheetFeature.State()
    ) {

        data class Title(
            val expander: ExpanderFeature.State = ExpanderFeature.State(
                isOpened = false,
                number = "1",
                notes = "You need to put title of event",
                expandHeight = (56 * 1.7).toInt(),
            ),
            val input: InputFeature.State = InputFeature.State(
                placeholder = "Title...",
                text = "",
                isFocused = false,
                readOnly = false
            ),
            val error: ErrorFeature.State = ErrorFeature.State(
                text = "ops, u forgot to put title",
                isShowed = false
            )
        )

        data class Description(
            val expander: ExpanderFeature.State = ExpanderFeature.State(
                isOpened = false,
                number = "2",
                notes = "After that add description",
                expandHeight = null,
            ),
            val input: InputFeature.State = InputFeature.State(
                placeholder = "Description...",
                text = "",
                isFocused = false,
                readOnly = false
            ),
            val error: ErrorFeature.State = ErrorFeature.State(
                text = "ops, u forgot to put description",
                isShowed = false
            ),
        )

        data class Date(
            val expander: ExpanderFeature.State = ExpanderFeature.State(
                isOpened = false,
                number = "3",
                notes = "Date and time should be in future",
                expandHeight = 56,
            ),
            val input: InputFeature.State = InputFeature.State(
                placeholder = "18:00,  14 Jun 2002...",
                text = "",
                isFocused = false,
                readOnly = true
            ),
            val error: ErrorFeature.State = ErrorFeature.State(
                text = "ops, u forgot to put date",
                isShowed = false
            ),
        )

        data class Location(
            val expander: ExpanderFeature.State = ExpanderFeature.State(
                isOpened = false,
                number = "4",
                notes = "Pick the location of event",
                expandHeight = 56,

                ),
            val input: InputFeature.State = InputFeature.State(
                placeholder = "4 Shady Rd. Brooklyn, NY 11223...",
                text = "",
                isFocused = false,
                readOnly = true
            ),
            val error: ErrorFeature.State = ErrorFeature.State(
                text = "ops, u forgot to put location",
                isShowed = false
            ),
        )

        data class Image(
            val expander: ExpanderFeature.State = ExpanderFeature.State(
                isOpened = false,
                number = "5",
                notes = "Add Image or Photo of event",
                expandHeight = (56 * 2.5).toInt(),
            ),
            val picker: ImagePickerFeature.State = ImagePickerFeature.State(),
            val error: ErrorFeature.State = ErrorFeature.State(
                text = "ops, u forgot to put image",
                isShowed = false
            ),
        )

        data class ButtonItem(
            val text: String = "",
            val enabled: Boolean = true,
        )
    }
}