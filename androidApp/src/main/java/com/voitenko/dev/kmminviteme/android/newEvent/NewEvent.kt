package com.voitenko.dev.kmminviteme.android.newEvent

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.voitenko.dev.kmminviteme.android.JustOnce
import com.voitenko.dev.kmminviteme.android.common.base.ToolBar
import com.voitenko.dev.kmminviteme.android.common.base.box.Root
import com.voitenko.dev.kmminviteme.android.contentLaunch
import com.voitenko.dev.kmminviteme.android.features.calendarPicker.CalendarPickerBlock
import com.voitenko.dev.kmminviteme.android.features.calendarPicker.CalendarPickerFeature
import com.voitenko.dev.kmminviteme.android.features.expandImagePicker.ExpandImagePickBlock
import com.voitenko.dev.kmminviteme.android.features.expandImagePicker.ExpandImagePickFeature
import com.voitenko.dev.kmminviteme.android.features.expandTextInput.ExpandInputBlock
import com.voitenko.dev.kmminviteme.android.features.requestButton.RequestButton

@Composable
fun NewEvent(navController: NavController, vm: NewEventVM = viewModel()) {

    val state = vm.processor.state.collectAsState()

    val launcher = contentLaunch { vm.send(NewEventVM.Event.PutImage(it)) }

    JustOnce {
        vm.news<ExpandImagePickFeature.News.Pick>(NewEventVM.TAG.IMAGE) {
            launcher.launch("image/*")
        }
    }

    CalendarPickerBlock(
        state = state.value.calendarPicker,
        content = { Content(vm = vm) },
        onClose = { vm.want(NewEventVM.TAG.CALENDAR_PICKER, CalendarPickerFeature.Wish.CloseSheet) }
    )
}

@Composable
private fun Content(vm: NewEventVM) = Root(
    header = {
        ToolBar(text = "New Event", isCollapsed = vm.state.title.expander.isOpened)
    },
    footer = {
        RequestButton(
            state = vm.state.requestButton,
            onClick = { vm.send(NewEventVM.Event.ClickGotIt) }
        )
    }
) {
    val (titleInput, descriptionInput) = remember { FocusRequester.createRefs() }

    ExpandInputBlock(
        modifier = Modifier.padding(top = 4.dp),
        state = vm.state.title,
        onValueChange = { vm.send(NewEventVM.Event.WriteTitle(it)) }
    )

    ExpandInputBlock(
        modifier = Modifier
            .weight(1f, false)
            .padding(top = 4.dp),
        state = vm.state.description,
        onValueChange = { vm.send(NewEventVM.Event.WriteDescription(it)) },
    )

    ExpandInputBlock(
        modifier = Modifier.padding(top = 4.dp),
        state = vm.state.date,
        onClick = { vm.send(NewEventVM.Event.ClickDate) }
    )
    ExpandInputBlock(
        modifier = Modifier.padding(top = 4.dp),
        state = vm.state.location,
        onClick = { vm.send(NewEventVM.Event.ClickLocation) }
    )

    ExpandImagePickBlock(
        modifier = Modifier.padding(top = 4.dp),
        state = vm.state.image,
        onClick = { vm.send(NewEventVM.Event.ClickImage) },
    )
}