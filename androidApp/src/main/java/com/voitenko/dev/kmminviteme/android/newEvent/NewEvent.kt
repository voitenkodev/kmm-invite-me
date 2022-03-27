package com.voitenko.dev.kmminviteme.android.newEvent

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.voitenko.dev.kmminviteme.android.JustOnce
import com.voitenko.dev.kmminviteme.android.common.BackHandler
import com.voitenko.dev.kmminviteme.android.common.base.ToolBar
import com.voitenko.dev.kmminviteme.android.common.base.box.ScrollRoot
import com.voitenko.dev.kmminviteme.android.contentLaunch
import com.voitenko.dev.kmminviteme.android.features.calendarPicker.CalendarPickerBlock
import com.voitenko.dev.kmminviteme.android.features.calendarPicker.CalendarPickerFeature
import com.voitenko.dev.kmminviteme.android.features.expandImagePicker.ExpandImagePickBlock
import com.voitenko.dev.kmminviteme.android.features.expandImagePicker.ExpandImagePickFeature
import com.voitenko.dev.kmminviteme.android.features.expandTextInput.ExpandInputBlock
import com.voitenko.dev.kmminviteme.android.features.requestButton.RequestButton
import com.voitenko.dev.kmminviteme.android.features.requestButton.RequestButtonFeature
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun NewEvent(navController: NavController, vm: NewEventVM = viewModel()) {

    val state = vm.processor.root.collectAsState()

    val launcher = contentLaunch { vm.send(NewEventVM.Event.PutImage(it)) }

    val context = LocalContext.current

    JustOnce {
        vm.processor.side<ExpandImagePickFeature.Side.Pick>(NewEventVM.TAG.IMAGE)
            ?.onEach { launcher.launch("image/*") }
            ?.launchIn(this)

        vm.processor.side<RequestButtonFeature.Side.ShowToast>(NewEventVM.TAG.REQUEST_BUTTON)
            ?.onEach { Toast.makeText(context, "asd", Toast.LENGTH_LONG).show() }
            ?.launchIn(this)
    }

    BackHandler { vm.send(NewEventVM.Event.CloseInfo) }

    CalendarPickerBlock(
        state = state.value.calendarPicker,
        content = { Content(vm = vm) },
        onClose = {
            vm.processor.want(
                NewEventVM.TAG.CALENDAR_PICKER,
                CalendarPickerFeature.Sync.CloseSheet
            )
        }
    )
}

@Composable
private fun Content(vm: NewEventVM) = ScrollRoot(
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

    item {
        ExpandInputBlock(
            modifier = Modifier.padding(top = 4.dp),
            state = vm.state.title,
            onValueChange = { vm.send(NewEventVM.Event.WriteTitle(it)) }
        )
    }
    item {
        ExpandInputBlock(
            modifier = Modifier.padding(top = 4.dp),
            state = vm.state.description,
            onValueChange = { vm.send(NewEventVM.Event.WriteDescription(it)) },
        )
    }
    item {
        ExpandInputBlock(
            modifier = Modifier.padding(top = 4.dp),
            state = vm.state.date,
            onClick = { vm.send(NewEventVM.Event.ClickDate) }
        )
    }
    item {
        ExpandInputBlock(
            modifier = Modifier.padding(top = 4.dp),
            state = vm.state.location,
            onClick = { vm.send(NewEventVM.Event.ClickLocation) }
        )
    }
    item {
        ExpandImagePickBlock(
            modifier = Modifier.padding(top = 4.dp),
            state = vm.state.image,
            onClick = { vm.send(NewEventVM.Event.ClickImage) },
        )
    }
}