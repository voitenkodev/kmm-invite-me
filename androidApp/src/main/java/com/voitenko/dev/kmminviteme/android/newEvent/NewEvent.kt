package com.voitenko.dev.kmminviteme.android.newEvent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.voitenko.dev.kmminviteme.android.JustOnce
import com.voitenko.dev.kmminviteme.android.common.base.Title
import com.voitenko.dev.kmminviteme.android.common.base.ToolBar
import com.voitenko.dev.kmminviteme.android.common.base.box.Root
import com.voitenko.dev.kmminviteme.android.contentLaunch
import com.voitenko.dev.kmminviteme.android.features.calendarPicker.CalendarPickerBock
import com.voitenko.dev.kmminviteme.android.features.calendarPicker.CalendarPickerFeature
import com.voitenko.dev.kmminviteme.android.features.expandImagePicker.ExpandImagePickBlock
import com.voitenko.dev.kmminviteme.android.features.expandImagePicker.ExpandImagePickFeature
import com.voitenko.dev.kmminviteme.android.features.expandTextInput.ExpandInputBlock
import com.voitenko.dev.kmminviteme.android.features.expandTextInput.ExpandInputFeature
import com.voitenko.dev.kmminviteme.android.common.theme.AppTheme

@Composable
fun NewEvent(navController: NavController, vm: NewEventVM = viewModel()) {

    val state = vm.processor.state.collectAsState()

    val launcher =
        contentLaunch {
            vm.want(NewEventVM.TAG.IMAGE, ExpandImagePickFeature.Wish.SetImage(it))
        }

    JustOnce {
        vm.news<ExpandImagePickFeature.News.Pick>(NewEventVM.TAG.IMAGE) {
            launcher.launch("image/*")
        }
    }

    CalendarPickerBock(
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
        ButtonOk(modifier = Modifier.padding(top = 8.dp)) {
            vm.want(NewEventVM.TAG.TITLE, ExpandInputFeature.Wish.Expand)
        }
    }
) {
    val (titleInput, descriptionInput) = remember { FocusRequester.createRefs() }

    ExpandInputBlock(
        modifier = Modifier.padding(top = 4.dp),
        state = vm.state.title,
        onValueChange = {
            vm.want(NewEventVM.TAG.TITLE, ExpandInputFeature.Wish.SetText(it))
        }
    )

    ExpandInputBlock(
        modifier = Modifier.padding(top = 4.dp),
        state = vm.state.description,
        onValueChange = {
            vm.want(NewEventVM.TAG.DESCRIPTION, ExpandInputFeature.Wish.SetText(it))
        },
    )

    ExpandInputBlock(
        modifier = Modifier.padding(top = 4.dp),
        state = vm.state.date,
        onClick = {
            vm.want(NewEventVM.TAG.CALENDAR_PICKER, CalendarPickerFeature.Wish.OpenSheet)
        }
    )
    ExpandInputBlock(
        modifier = Modifier.padding(top = 4.dp),
        state = vm.state.location,
        onClick = {
            vm.want(NewEventVM.TAG.CALENDAR_PICKER, CalendarPickerFeature.Wish.OpenSheet)
        }
    )

    ExpandImagePickBlock(
        modifier = Modifier.padding(top = 4.dp),
        state = vm.state.image,
        onClick = {
            vm.want(NewEventVM.TAG.IMAGE, ExpandImagePickFeature.Wish.Pick)
        },
    )
}


@Composable
private fun ButtonOk(modifier: Modifier = Modifier, lambda: () -> Unit) = Button(
    modifier = modifier
        .clip(AppTheme.shapes.medium)
        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
    onClick = lambda,
) {
    Title(
        text = "Got it!",
        color = AppTheme.colors.primary,
        modifier = Modifier.fillMaxWidth()
    )
}
