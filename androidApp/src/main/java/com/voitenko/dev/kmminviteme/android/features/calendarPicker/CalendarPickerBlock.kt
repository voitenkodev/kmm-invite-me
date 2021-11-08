package com.voitenko.dev.kmminviteme.android.features.calendarPicker

import android.os.Build
import android.widget.CalendarView
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.voitenko.dev.kmminviteme.android.common.BottomSheetBlock
import java.time.LocalDate


@Composable
fun CalendarPickerBock(
    state: CalendarPickerFeature.State,
    content: @Composable () -> Unit,
    onClose: () -> Unit
) = BottomSheetBlock(
    isOpen = state.isOpen,
    bottomSheetContent = { CustomCalendarView({}) },
    content = { content.invoke() },
    onClose = onClose
    /*{vm.want(NewEventViewModel.TAG.BOTTOM_SHEET,BottomSheetFeature.Wish.CloseSheet)}*/
)


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
