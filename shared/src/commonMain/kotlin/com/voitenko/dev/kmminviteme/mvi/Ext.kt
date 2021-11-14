package com.voitenko.dev.kmminviteme.mvi

import com.voitenko.dev.kmminviteme.mvi.builder.MviProcessor
import com.voitenko.dev.kmminviteme.mvi.feature.Feature
import kotlinx.coroutines.CoroutineScope

/**
 * @param Model is data class of whole screen
 * @return [MviProcessor.FeatureProcessor]  which responsible for adding [Feature] using method [MviProcessor.FeatureProcessor.feature]
 **/

public inline infix fun <reified Model, State : Feature.State, reified F : Feature<*, *, *>> F.push(
    noinline l: Model.(State) -> Model
): Pair<F, Model.(State) -> Model> = Pair(this, l)