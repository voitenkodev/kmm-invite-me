package com.voitenko.dev.kmminviteme.android.screens.newevent

import android.os.Build
import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.voitenko.dev.kmminviteme.android.JustOnce
import com.voitenko.dev.kmminviteme.android.JustWhen
import com.voitenko.dev.kmminviteme.android.contentLaunch
import com.voitenko.dev.kmminviteme.android.theme.AppTheme
import com.voitenko.dev.kmminviteme.android.base.Title
import com.voitenko.dev.kmminviteme.android.base.ToolBar
import com.voitenko.dev.kmminviteme.android.base.box.Root
import com.voitenko.dev.kmminviteme.android.features.bottomSheet.BottomSheet
import com.voitenko.dev.kmminviteme.android.features.bottomSheet.BottomSheetFeature
import com.voitenko.dev.kmminviteme.android.features.expander.ExpanderComponent
import com.voitenko.dev.kmminviteme.android.features.expander.ExpanderFeature
import com.voitenko.dev.kmminviteme.android.features.image.ImagePicker
import com.voitenko.dev.kmminviteme.android.features.image.ImagePickerFeature
import com.voitenko.dev.kmminviteme.android.features.input.Input
import com.voitenko.dev.kmminviteme.android.features.input.InputFeature
import com.voitenko.dev.kmminviteme.android.features.warning.Error
import java.time.LocalDate

@Composable
fun NewEventScreen(navController: NavController, vm: NewEventViewModel = viewModel()) {

    val state = vm.processor.state.collectAsState()

    val launcher =
        contentLaunch {
            vm.want(NewEventViewModel.TAG.IMAGE_PICKER, ImagePickerFeature.Wish.SetImage(it))
        }

    JustOnce {
        vm.news<ImagePickerFeature.News.Pick>(NewEventViewModel.TAG.IMAGE_PICKER) {
            launcher.launch("image/*")
        }
    }

    val focusManager = LocalFocusManager.current
    JustWhen(key1 = state.value.bottomSheet.isOpen) { focusManager.clearFocus() }

    BottomSheet(
        state = state.value.bottomSheet,
        bottomSheetContent = { BottomSheetContent() },
        content = { Content(vm = vm) },
        onClose = {
            vm.want(
                NewEventViewModel.TAG.BOTTOM_SHEET,
                BottomSheetFeature.Wish.CloseSheet
            )
        }
    )
}

@Composable
private fun Content(vm: NewEventViewModel) = Root(
    header = {
        ToolBar(text = "New Event", isCollapsed = vm.state.title.expander.isOpened)
    },
    footer = {
        ButtonOk(modifier = Modifier.padding(top = 8.dp)) { vm.want(ExpanderFeature.Wish.Expand) }
    }
) {
    val (titleInput, descriptionInput) = remember { FocusRequester.createRefs() }

    ExpanderComponent(
        state = vm.state.title.expander,
        content = {
            Input(
                modifier = Modifier
                    .alpha(it)
                    .focusRequester(titleInput),
                state = vm.state.title.input,
                textStyle = AppTheme.typography.input.copy(color = Color.Black),
                keyboardActions = KeyboardActions(onNext = { vm.want(ExpanderFeature.Wish.Expand) }),
                onValueChange = {
                    vm.want(NewEventViewModel.TAG.TITLE_INPUT, InputFeature.Wish.SetText(it))
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )
        }
    )

    Error(state = vm.state.title.error)

    ExpanderComponent(
        modifier = Modifier
            .weight(1f, false)
            .padding(top = 8.dp),
        state = vm.state.description.expander,
        content = {
            Input(
                modifier = Modifier
                    .alpha(it)
                    .focusRequester(descriptionInput),
                state = vm.state.description.input,
                textStyle = AppTheme.typography.input.copy(color = Color.Black),
                onValueChange = {
                    vm.want(NewEventViewModel.TAG.DESCRIPTION_INPUT, InputFeature.Wish.SetText(it))
                },
                keyboardActions = KeyboardActions(onNext = { vm.want(ExpanderFeature.Wish.Expand) }),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
            )
        }
    )

    Error(state = vm.state.description.error)

    ExpanderComponent(
        modifier = Modifier.padding(top = 8.dp),
        state = vm.state.date.expander,
        content = {
            Input(
                modifier = Modifier.alpha(it),
                state = vm.state.date.input,
                textStyle = AppTheme.typography.input.copy(color = Color.Black),
                onClick = {
                    vm.want(NewEventViewModel.TAG.BOTTOM_SHEET, BottomSheetFeature.Wish.OpenSheet)
                }
            )
        }
    )

    Error(state = vm.state.date.error)

    ExpanderComponent(
        modifier = Modifier.padding(top = 8.dp),
        state = vm.state.location.expander,
        content = {
            Input(
                modifier = Modifier.alpha(it),
                state = vm.state.location.input,
                textStyle = AppTheme.typography.input.copy(color = Color.Black),
                onClick = {
                    vm.want(NewEventViewModel.TAG.BOTTOM_SHEET, BottomSheetFeature.Wish.OpenSheet)
                }
            )
        }
    )

    Error(state = vm.state.location.error)

    ExpanderComponent(
        modifier = Modifier.padding(top = 8.dp),
        state = vm.state.image.expander,
        content = {
            ImagePicker(
                alpha = it,
                state = vm.state.image.picker,
                onClick = {
                    vm.want(NewEventViewModel.TAG.IMAGE_PICKER, ImagePickerFeature.Wish.Pick)
                }
            )
        }
    )

    Error(state = vm.state.image.error)
}

@Composable
private fun BottomSheetContent() = Box(
    modifier = Modifier
        .background(Color.Black)
        .fillMaxWidth()
        .height(400.dp)
        .background(Color.Black)
) {
    CustomCalendarView(onDateSelected = {})
}

@Composable
fun CustomCalendarView(onDateSelected: (LocalDate) -> Unit) = AndroidView(
    modifier = Modifier.wrapContentSize(),
    factory = { context -> CalendarView(context) },
    update = { view ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            view.minDate = LocalDate.now()
            view.setOnDateChangeListener { _, year, month, dayOfMonth ->
                onDateSelected(
                    LocalDate.now().withMonth(month + 1)
                        .withYear(year)
                        .withDayOfMonth(dayOfMonth)
                )
            }
        }
    }
)

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
