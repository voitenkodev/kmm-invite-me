package com.voitenko.dev.kmminviteme.android

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import kotlinx.coroutines.CoroutineScope

@Composable
fun contentLaunch(block: (Uri) -> Unit) =
    rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { it?.let(block) }

@Composable
fun Once(block: suspend CoroutineScope.() -> Unit) = JustWhen(key1 = Unit, block = block)

@Composable
fun EveryTime(block: () -> Unit) = SideEffect(effect = block)

@Composable
fun JustWhen(key1: Any, block: suspend CoroutineScope.() -> Unit) =
    LaunchedEffect(key1 = key1, block = block)
