package com.voitenko.dev.kmminviteme.android.base

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable

@Composable
fun floatState(value: Boolean, a: Pair<Float, Float>) = animateFloatAsState(
    if (value) a.first else a.second, spring(stiffness = Spring.StiffnessLow)
)
